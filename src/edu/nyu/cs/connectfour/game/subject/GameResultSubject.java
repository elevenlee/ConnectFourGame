package edu.nyu.cs.connectfour.game.subject;

import edu.nyu.cs.connectfour.game.observer.GameResultObserver;

/**
 * @author shenli
 * <p>
 * This class represents an {@code GameResultSubject} object, or "data" in the model-view paradigm. It can be 
 * sub-classed to represent an object that the application wants to have observed.
 * <p>
 * An {@code GameResultSubject} object can have one or more {@link edu.nyu.cs.connectfour.game.observer.GameResultObserver}. 
 * An observer may be any object that implements interface {@link edu.nyu.cs.connectfour.game.observer.GameResultObserver}. 
 * After an {@code GameResultSubject} instance changes, an application calling the {@code GameResultSubject}'s
 * {@code notifyGameResultObservers} method causes all of its observers to be notified of the change by a call 
 * to their {@code updateGameResult} method.
 * <p>
 * The order in which notifications will be delivered is unspecified. The default implementation provided in 
 * the {@code GameResultSubject} class will notify {@link edu.nyu.cs.connectfour.game.observer.GameResultObserver}
 * in the order in which they registered interest, but subclasses may change this order, use no guaranteed 
 * order, deliver notifications on separate threads, or may guarantee that their subclass follows this order, 
 * as they choose.
 * <p>
 * Note that this notification mechanism is has nothing to do with threads and is completely separate from the 
 * wait and notify mechanism of class Object.
 * <p>
 * When an {@code GameResultSubject} object is newly created, its set of observers is empty.
 */
public interface GameResultSubject {
    
    /**
     * Adds an observer to the set of observers for this object. The order in which notifications will be 
     * delivered to multiple observers is not specified.
     * <p>
     * @param o an observer to be added
     */
    public void registerGameResultObserver(GameResultObserver o);
    
    /**
     * Deletes an observer from the set of observers of this object. Passing null to this method will have no 
     * effect. 
     * <p>
     * @param o the observer to be deleted.
     */
    public void removeGameResultObserver(GameResultObserver o);
    
    /**
     * If this object has changed, then notify all of its observers.
     * <p>
     * @param text the text
     */
    public void notifyGameResultObservers(String text);
    
}
