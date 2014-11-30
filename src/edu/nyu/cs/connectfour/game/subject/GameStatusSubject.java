package edu.nyu.cs.connectfour.game.subject;

import edu.nyu.cs.connectfour.game.observer.GameStatusObserver;

/**
 * @author shenli
 * <p>
 * This class represents an {@code GameStatusSubject} object, or "data" in the model-view paradigm. It can be 
 * sub-classed to represent an object that the application wants to have observed.
 * <p>
 * A {@code GameStatusSubject} object can have one or more {@link edu.nyu.cs.connectfour.game.observer.GameStatusObserver}. 
 * An observer may be any object that implements interface {@link edu.nyu.cs.connectfour.game.observer.GameStatusObserver}. 
 * After a {@code GameStatusSubject} instance changes, an application calling the {@code GameStatusSubject}'s
 * {@code notifyGameStatusObservers} method causes all of its observers to be notified of the change by a call 
 * to their {@code updateGameModeAndLevel}, {@code updateGameTurn}, and {@code updateGameResult} method.
 * <p>
 * The order in which notifications will be delivered is unspecified. The default implementation provided in 
 * the {@code GameStatusSubject} class will notify {@link edu.nyu.cs.connectfour.game.observer.GameStatusObserver}
 * in the order in which they registered interest, but subclasses may change this order, use no guaranteed 
 * order, deliver notifications on separate threads, or may guarantee that their subclass follows this order, 
 * as they choose.
 * <p>
 * Note that this notification mechanism is has nothing to do with threads and is completely separate from 
 * the wait and notify mechanism of class Object.
 * <p>
 * When an {@code GameStatusSubject} object is newly created, its set of observers is empty.
 */
public interface GameStatusSubject {
    
    /**
     * Adds an observer to the set of observers for this object. The order in which notifications will be 
     * delivered to multiple observers is not specified.
     * <p>
     * @param o an observer to be added.
     */
    public void registerGameStatusObserver(GameStatusObserver o);
    
    /**
     * Deletes an observer from the set of observers of this object. Passing null to this method will have no 
     * effect. 
     * <p>
     * @param o the observer to be deleted.
     */
    public void removeGameStatusObserver(GameStatusObserver o);
    
    /**
     * If this object has changed, then notify all of its observers.
     */
    public void notifyGameStatusObservers();
    
}
