package edu.nyu.cs.connectfour.game.subject.impl;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import edu.nyu.cs.connectfour.game.ai.ComputerThinking;
import edu.nyu.cs.connectfour.game.observer.GameRecordObserver;
import edu.nyu.cs.connectfour.game.observer.GameResultObserver;
import edu.nyu.cs.connectfour.game.observer.GameStatusObserver;
import edu.nyu.cs.connectfour.game.subject.GameRecordSubject;
import edu.nyu.cs.connectfour.game.subject.GameResultSubject;
import edu.nyu.cs.connectfour.game.subject.GameStatusSubject;
import edu.nyu.cs.connectfour.game.utils.ComputerLevel;
import edu.nyu.cs.connectfour.game.utils.GameMode;
import edu.nyu.cs.connectfour.player.subject.impl.PlayerInfo;
import edu.nyu.cs.connectfour.utils.ParameterChecker;

/**
 * @author shenli
 * <p>
 * The {@code GameRecord} class represents chess map state information
 * <p>
 * {@code GameRecord} objects are not constant; their values could be changed after they are created. The 
 * {@code GameRecord} object is not thread-safe. To use it concurrently, user must surround each method 
 * invocation with external synchronization of the users' choosing.
 */
public class GameRecord implements GameRecordSubject, GameResultSubject, GameStatusObserver {
	private static final int PLAYER_NUMBER = PlayerInfo.values().length;
	
	private final List<GameRecordObserver> playRecordObservers = new CopyOnWriteArrayList<>();
	private final List<GameResultObserver> gameUtilObservers = new CopyOnWriteArrayList<>();
	private final GameStatusSubject gameStatus = GameUtility.INSTANCE;
	
	private GameMode mode = GameUtility.INSTANCE.getGameMode();
    private ComputerLevel level = GameUtility.INSTANCE.getComputerLevel();
    private PlayerInfo turn = GameUtility.INSTANCE.getOffensive();
    
    private final ComputerThinking computerThink;
	private final int rows;
	private final int columns;
	private int chessNumber = 0;
	private int[] nextPlace;
	private int[][] state;
	
	/**
	 * Initializes a newly created {@code GameRecord} object so that it records chess map information.
	 * <p>
	 * @param rows the chess map row
	 * @param columns the chess map column
	 */
	public GameRecord(int rows, int columns) {
		ParameterChecker.rangeCheck(rows, "chess map row");
		ParameterChecker.rangeCheck(columns, "chess map column");
		
		this.rows = rows;
		this.columns = columns;
		this.nextPlace = new int[this.columns];
		this.state = new int[this.rows][this.columns];
		for (int j = 0; j < this.columns; j++) {
			for (int i = 0; i < this.rows; i++) {
				this.state[i][j] = -1;
			}
			this.nextPlace[j] = this.rows - 1;
		}
		this.computerThink = new ComputerThinking(this.rows, this.columns, nextPlace, state);
		this.gameStatus.registerGameStatusObserver(this);
	}
	
	/**
	 * Update chess map state information when player place a chess on the specific column.
	 * <p>
	 * @param column the player placed column
	 */
	public void place(int column) {
		ParameterChecker.rangeCheck(column, "place column");
		
		int i = turn.ordinal() % PLAYER_NUMBER;
		state[nextPlace[column]][column] = i;
		chessNumber++;
		nextPlace[column]--;
		notifyGameRecordObservers(column);
		
		if (computerThink.isWin(nextPlace[column] + 1, column, i)) {
			GameUtility.INSTANCE.setGameStatus(false, true);
			notifyGameResultObservers(turn.getName() + " wins!");
			return;
		} else if (computerThink.isDraw(chessNumber)) {
			GameUtility.INSTANCE.setGameStatus(false, true);
			notifyGameResultObservers("Game Draw!");
			return;
		}
		
		int j = (i + 1) % PLAYER_NUMBER;
		PlayerInfo pi = PlayerInfo.values()[j];
		GameUtility.INSTANCE.setPlayerTurn(pi);
		nextPlay();
	}
	
	/**
	 * Update chess map state information after the next player place a chess on the map.
	 * <p>
	 * @throws IllegalArgumentException - if no specific play mode
	 */
	public void nextPlay() {
		switch (mode) {
			case HUMAN_VS_COMPUTER:
				if (turn == PlayerInfo.PLAYER_TWO) {
					place(computerThink.bestValue(level.getDegree(), turn.ordinal()));
				}
				return;
			case HUMAN_VS_HUMAN:
				return;
		}
		throw new IllegalArgumentException("No such play mode!");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void registerGameRecordObserver(GameRecordObserver o) {
		ParameterChecker.nullCheck(o, "edu.nyu.cs.connectfour.game.observer.GameRecordObserver");
		
		playRecordObservers.add(o);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeGameRecordObserver(GameRecordObserver o) {
		int i = playRecordObservers.indexOf(o);
		if (i >= 0) {
			playRecordObservers.remove(i);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void notifyGameRecordObservers(int column) {
		ParameterChecker.rangeCheck(column, "place column");
		
		for (GameRecordObserver pro : playRecordObservers) {
			pro.updateGameRecord(turn, nextPlace[column] + 1, column);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void registerGameResultObserver(GameResultObserver o) {
		ParameterChecker.nullCheck(o, "edu.nyu.cs.connectfour.game.observer.GameResultObserver");
		
		gameUtilObservers.add(o);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeGameResultObserver(GameResultObserver o) {
		int i = gameUtilObservers.indexOf(o);
		if (i >= 0) {
			gameUtilObservers.remove(i);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void notifyGameResultObservers(String text) {
		ParameterChecker.nullCheck(text, "result");
		
		for (GameResultObserver guo : gameUtilObservers) {
			guo.updateGameResult(text);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateGameModeAndLevel(GameMode mode, ComputerLevel level) {
		ParameterChecker.nullCheck(mode, "game mode");
		ParameterChecker.nullCheck(level, "computer level");
		
		this.mode = mode;
		this.level = level;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateGameTurn(PlayerInfo turn) {
		ParameterChecker.nullCheck(turn, "player turn");
		
		this.turn= turn;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateGameStatus(boolean playing, boolean gameOver) {
		
	}
	
	/**
	 * Compares the specified object with this {@code GameRecord} object for equality. Returns true if and 
	 * only if the specified object is also a {@code GameRecord} object, both objects have the same row, 
	 * column, chess number, game mode, computer level, player turn and reference to the same next place 
	 * array and chess map state array.
	 * <p>
	 * This implementation first checks if the specified object is this {@code GameRecord} object. If so, it 
	 * returns true; if not, it checks if the specified object is a {@code GameRecord} object. If not, it 
	 * returns false; if so, it iterates over both {@code GameRecord} objects, comparing corresponding fields. 
	 * If any comparison returns false, this method returns false. Otherwise it returns true when the iterations
	 * complete.
	 * <p>
	 * @param o the object to be compared for equality with this {@code GameRecord} object
	 * @return true if the specified object is equal to this {@code GameRecord} object
	 */
	@Override
	public boolean equals(Object o) {
		if (o == this) {
		    return true;
		}
		if (! (o instanceof GameRecord)) {
		    return false;
		}
		GameRecord gr = (GameRecord) o;
		return rows == gr.rows
				&& columns == gr.columns
				&& chessNumber == gr.chessNumber
				&& nextPlace == gr.nextPlace
				&& state == gr.state
				&& mode == gr.mode
				&& level == gr.level
				&& turn == gr.turn;
	}
	
	/**
	 * Returns the hash code value for this {@code GameRecord} object.
	 * <p>
	 * @return the hash code value for this {@code GameRecord} object
	 */
	@Override
	public int hashCode() {
	    final int prime = 31;
		int hashCode = 17;
		hashCode = hashCode * prime + rows;
		hashCode = hashCode * prime + columns;
		hashCode = hashCode * prime + chessNumber;
		hashCode = hashCode * prime + nextPlace.hashCode();
		hashCode = hashCode * prime + state.hashCode();
		hashCode = hashCode * prime + mode.hashCode();
		hashCode = hashCode * prime + level.hashCode();
		hashCode = hashCode * prime + turn.hashCode();
		return hashCode;
	}
	
	/**
	 * Returns string representation of this {@code GameRecord} object. The string representation consists of 
	 * row, column number, chess number, next chess place array, chess map state array, game mode, computer 
	 * level, and player turn fields of the {@code GameRecord} object. Each fields are separated by the characters 
	 * "," (comma).
	 * <p>
	 * @return a string representation of this {@code GameRecord} object
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(rows);
		sb.append("," + columns);
		sb.append("," + chessNumber);
		for (int next : nextPlace) {
			sb.append("," + next);
		}
		for (int row[] : state) {
			for (int column : row) {
				sb.append("," + column);
			}
		}
		sb.append("," + mode);
		sb.append("," + level);
		sb.append("," + turn);
		return sb.toString();
	}

}
