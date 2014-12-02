package edu.nyu.cs.connectfour.game.subject.impl;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import edu.nyu.cs.connectfour.game.observer.GameStatusObserver;
import edu.nyu.cs.connectfour.game.subject.GameStatusSubject;
import edu.nyu.cs.connectfour.game.utils.ComputerLevel;
import edu.nyu.cs.connectfour.game.utils.GameMode;
import edu.nyu.cs.connectfour.player.subject.impl.PlayerInfo;
import edu.nyu.cs.connectfour.utils.ParameterChecker;

/**
 * @author shenli
 * <p>
 * An {@code GameUtility} object is used to record game information, including game mode, computer level, 
 * offensive player, which player is turn, and game status.
 * <p>
 * The singleton {@code GameUtility} object is thread-safe.
 */
public enum GameUtility implements GameStatusSubject {
    /**
     * The singleton instance contains all the game status information
     */
    INSTANCE;
    
    private static final String SEPARATOR = "/";
    
    private final List<GameStatusObserver> gameStatusObservers = new CopyOnWriteArrayList<>();
    private final Object playingLock = new Object();
    private final Object gameOverLock = new Object();
    private GameMode mode = GameMode.HUMAN_VS_COMPUTER;
    private ComputerLevel level = ComputerLevel.REGULAR;
    private PlayerInfo offensive = PlayerInfo.PLAYER_ONE;
    private PlayerInfo turn = PlayerInfo.PLAYER_ONE;
    private boolean playing = false;
    private boolean gameOver = true;
    
    /**
     * Returns game mode.
     * <p>
     * @return game mode
     */
    public GameMode getGameMode() {
        synchronized(mode) {
            return mode;
        }
    }
    
    /**
     * Returns computer level.
     * <p>
     * @return computer level
     */
    public ComputerLevel getComputerLevel() {
        synchronized(level) {
            return level;
        }
    }
    
    /**
     * Returns offensive player.
     * <p>
     * @return offensive player
     */
    public PlayerInfo getOffensive() {
        synchronized(offensive) {
            return offensive;
        }
    }
    
    /**
     * Returns which player is turn.
     * <p>
     * @return which player is turn
     */
    public PlayerInfo getPlayerTurn() {
        synchronized(turn) {
            return turn;
        }
    }
    
    /**
     * Returns the state of game. True is player is playing, false if it's not.
     * <p>
     * @return true if player is playing, otherwise false
     */
    public boolean isPlaying() {
        synchronized(playingLock) {
            return playing;
        }
    }
    
    /**
     * Returns the state of game. True if game is over, false if it's not.
     * <p>
     * @return true if game is over, otherwise false
     */
    public boolean isGameOver() {
        synchronized(gameOverLock) {
            return gameOver;
        }
    }
    
    /**
     * Sets the game mode and computer level.
     * <p>
     * @param mode the mode used to set the game
     * @param level the computer level used to set the computer
     */
    public void setGameModeAndLevel(GameMode mode, ComputerLevel level) {
        ParameterChecker.nullCheck(mode, "game mode");
        ParameterChecker.nullCheck(level, "computer level");
        
        synchronized(this.mode) {
            this.mode = mode;
        }
        synchronized(this.level) {
            this.level = level;
        }
        notifyGameStatusObservers();
    }
    
    /**
     * Sets the offensive player.
     * <p>
     * @param offensive the player used to set the offensive
     */
    public void setOffensive(PlayerInfo offensive) {
        ParameterChecker.nullCheck(offensive, "offensive player");
        
        synchronized(this.offensive) {
            this.offensive = offensive;
        }
        synchronized(this.turn) {
            this.turn = offensive;
        }
        notifyGameStatusObservers();
    }
    
    /**
     * Sets the player which is turn.
     * <p>
     * @param turn player used to set the turn
     */
    public void setPlayerTurn(PlayerInfo turn) {
        ParameterChecker.nullCheck(turn, "player turn");
        
        synchronized (this.turn) {
            this.turn = turn;
        }
        notifyGameStatusObservers();
    }
    
    /**
     * Sets the game status.
     * <p>
     * @param playing playing flag used to set
     * @param gameOver game over flag used to set
     */
    public void setGameStatus(boolean playing, boolean gameOver) {
        synchronized(playingLock) {
            this.playing = playing;
        }
        synchronized(gameOverLock) {
            this.gameOver = gameOver;
        }
        notifyGameStatusObservers();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void registerGameStatusObserver(GameStatusObserver o) {
        ParameterChecker.nullCheck(o, "edu.nyu.cs.connectfour.game.observer.GameStatusObserver");
        
        gameStatusObservers.add(o);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void removeGameStatusObserver(GameStatusObserver o) {
        int i = gameStatusObservers.indexOf(o);
        if (i >= 0) {
            gameStatusObservers.remove(i);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyGameStatusObservers() {
        for (GameStatusObserver gso : gameStatusObservers) {
            gso.updateGameModeAndLevel(mode, level);
            gso.updateGameTurn(turn);
            gso.updateGameStatus(playing, gameOver);
        }
    }
    
    /**
     * Return string representation of this singleton {@code GameUtility} object. The string representation
     * consists of the game mode, computer level, offensive player, turn player, playing flag and game over 
     * flag separated by '/' character.
     * <p>
     * @return string representation of this singleton {@code GameUtility} object
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        GameMode pm = null;
        synchronized (mode) {
            pm = mode;
        }
        sb.append(pm);
        sb.append(SEPARATOR);
        ComputerLevel cl = null;
        synchronized (level) {
            cl = level;
        }
        sb.append(cl);
        sb.append(SEPARATOR);
        PlayerInfo pi = null;
        synchronized (offensive) {
            pi = offensive;
        }
        sb.append(pi);
        sb.append(SEPARATOR);
        synchronized (turn) {
            pi = turn;
        }
        sb.append(pi);
        sb.append(SEPARATOR);
        boolean flag = false;
        synchronized (playingLock) {
            flag = playing;
        }
        sb.append(flag);
        sb.append(SEPARATOR);
        synchronized (gameOverLock) {
            flag = gameOver;
        }
        sb.append(flag);
        return sb.toString();
    }

}
