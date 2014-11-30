package edu.nyu.cs.connectfour.game.observer;

import edu.nyu.cs.connectfour.game.utils.ComputerLevel;
import edu.nyu.cs.connectfour.game.utils.GameMode;
import edu.nyu.cs.connectfour.player.subject.impl.PlayerInfo;

/**
 * @author shenli
 * <p>
 * A class can implement the {@code GameStatusObserver} interface when it wants to be informed of game status 
 * changes in {@link edu.nyu.cs.connectfour.game.subject.GameStatusSubject} objects. 
 */
public interface GameStatusObserver {
	
	/**
	 * This method is called whenever the observed object is changed. An application calls a 
	 * {@link edu.nyu.cs.connectfour.game.subject.GameStatusSubject} object's {@code notifyGameStatusObservers}
	 * method to have all the object's observers notified of the game status change.
	 * <p>
	 * @param mode a {@link edu.nyu.cs.connectfour.game.utils.GameMode} object passed to the 
	 * {@code notifyGameStatusObservers} method
	 * @param level a {@link edu.nyu.cs.connectfour.game.utils.ComputerLevel} object passed to the 
	 * {@code notifyGameStatusObservers} method
	 */
	public void updateGameModeAndLevel(GameMode mode, ComputerLevel level);
	
	/**
	 * This method is called whenever the observed object is changed. An application calls a 
	 * {@link edu.nyu.cs.connectfour.game.subject.GameStatusSubject} object's {@code notifyGameStatusObservers} 
	 * method to have all the object's observers notified of the game status change.
	 * <p>
	 * @param turn a {@link edu.nyu.cs.connectfour.player.subject.impl.PlayerInfo} object passed to the 
	 * {@code notifyGameStatusObservers} method
	 */
	public void updateGameTurn(PlayerInfo turn);
	
	/**
	 * This method is called whenever the observed object is changed. An application calls a 
	 * {@link edu.nyu.cs.connectfour.game.subject.GameStatusSubject} object's {@code notifyGameStatusObservers} 
	 * method to have all the object's observers notified of the game status change.
	 * <p>
	 * @param playing a playing parameter passed to the {@code notifyGameStatusObservers} method
	 * @param gameOver a playing parameter passed to the {@code notifyGameStatusObservers} method
	 */
	public void updateGameStatus(boolean playing, boolean gameOver);
	
}
