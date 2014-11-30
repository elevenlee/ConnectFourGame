package edu.nyu.cs.connectfour.game.observer;

/**
 * @author shenli
 * <p>
 * A class can implement the {@code GameResultObserver} interface when it wants to be informed of game result 
 * changes in {@link edu.nyu.cs.connectfour.game.subject.GameResultSubject} objects. 
 */
public interface GameResultObserver {
    
    /**
     * This method is called whenever the observed object is changed. An application calls a 
     * {@link edu.nyu.cs.connectfour.game.subject.GameResultSubject} object's {@code notifyGameResultObservers} 
     * method to have all the object's observers notified of the game result change. 
     * <p>
     * @param text a text passed to the {@code notifyGameResultObservers} method
     */
    public void updateGameResult(String text);
    
}
