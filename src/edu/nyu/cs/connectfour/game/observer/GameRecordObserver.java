package edu.nyu.cs.connectfour.game.observer;

import edu.nyu.cs.connectfour.player.subject.impl.PlayerInfo;

/**
 * @author shenli
 * <p>
 * A class can implement the {@code GameRecordObserver} interface when it wants to be informed of game record
 * changes in {@link edu.nyu.cs.connectfour.game.subject.GameRecordSubject} objects. 
 */
public interface GameRecordObserver {
    
    /**
     * This method is called whenever the observed object is changed. An application calls a
     * {@link edu.nyu.cs.connectfour.game.subject.GameRecordSubject} object's {@code notifyGameRecordObservers} 
     * method to have all the object's observers notified of the game record change.
     * <p>
     * @param playerInfo a {@link edu.nyu.cs.connectfour.player.subject.impl.PlayerInfo} object passed to the 
     * {@code notifyGameRecordObservers} method
     * @param row a row number passed to the {@code notifyGameRecordObservers} method
     * @param column a column number passed to the {@code notifyGameRecordObservers} method
     */
    public void updateGameRecord(PlayerInfo playerInfo, int row, int column);
    
}