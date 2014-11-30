package edu.nyu.cs.connectfour.container;

import java.awt.Container;

/**
 * @author shenli
 * <p>
 * The interface imposes a container component of each class that implements it.
 * <p>
 * NOTE: The interface could only get the container which is a subtype of {@code java.awt.Container}.
 */
public interface GetContainerable<T extends Container> {
	
	/**
	 * Returns the container element.
	 * <p>
	 * @return the container element.
	 */
	public T getContainer();
	
}
