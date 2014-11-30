package edu.nyu.cs.connectfour.player.observer;

import java.awt.Color;

import edu.nyu.cs.connectfour.player.subject.impl.PlayerInfo;

/**
 * @author shenli
 * <p>
 * A class can implement the {@code PlayerInfoObserver} interface when it wants to be informed of player 
 * information changes in {@link edu.nyu.cs.connectfour.player.subject.PlayerInfoSubject} objects. 
 */
public interface PlayerInfoObserver {
	
	/**
	 * This method is called whenever the observed object is changed. An application calls a
	 * {@link edu.nyu.cs.connectfour.player.subject.PlayerInfoSubject} object's {@code notifyGamePlayerInfoObservers }
	 * method to have all the object's observers notified of the player information change. 
	 * <p>
	 * @param playerInfo a {@link edu.nyu.cs.connectfour.player.subject.impl.PlayerInfo} object passed to the 
	 * {@code notifyPlayerInfoObservers} method
	 * @param name a name passed to the {@code notifyPlayerInfoObservers} method
	 * @param color a {@link java.awt.Color} object passed to the {@code notifyPlayerInfoObservers} method
	 */
	public void updatePlayerInfo(PlayerInfo playerInfo, String name, Color color);
	
}
