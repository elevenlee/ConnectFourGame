package edu.nyu.cs.connectfour.player.subject;

import edu.nyu.cs.connectfour.player.observer.PlayerInfoObserver;

/**
 * @author shenli
 * <p>
 * This class represents a {@code PlayerInfoSubject} object, or "data" in the model-view paradigm. It can be 
 * sub-classed to represent an object that the application wants to have observed.
 * <p>
 * A {@code PlayerInfoSubject} object can have one or more {@link edu.nyu.cs.connectfour.player.observer.PlayerInfoObserver}. 
 * An observer may be any object that implements interface {@link edu.nyu.cs.connectfour.player.observer.PlayerInfoObserver}. 
 * After a {@code PlayerInfoSubject} instance changes, an application calling the {@code PlayerInfoSubject}'s
 * {@code notifyPlayerInfoObservers} method causes all of its observers to be notified of the change by a call 
 * to their {@code updatePlayerInfo} method.
 * <p>
 * The order in which notifications will be delivered is unspecified. The default implementation provided in 
 * the {@code PlayerInfoSubject} class will notify {@link edu.nyu.cs.connectfour.player.observer.PlayerInfoObserver}
 * in the order in which they registered interest, but subclasses may change this order, use no guaranteed 
 * order, deliver notifications on separate threads, or may guarantee that their subclass follows this order, 
 * as they choose.
 * <p>
 * Note that this notification mechanism is has nothing to do with threads and is completely separate from the 
 * wait and notify mechanism of class Object.
 * <p>
 * When an {@code PlayerInfoSubject} object is newly created, its set of observers is empty.
 */
public interface PlayerInfoSubject {
    
    /**
     * Adds an observer to the set of observers for this object. The order in which notifications will be 
     * delivered to multiple observers is not specified.
     * <p>
     * @param o an observer to be added.
     */
    public void registerPlayerInfoObserver(PlayerInfoObserver o);
    
    /**
     * Deletes an observer from the set of observers of this object. Passing null to this method will have no 
     * effect. 
     * <p>
     * @param o the observer to be deleted.
     */
    public void removePlayerInfoObserver(PlayerInfoObserver o);
    
    /**
     * If this object has changed, then notify all of its observers. 
     */
    public void notifyPlayerInfoObservers();
    
}
