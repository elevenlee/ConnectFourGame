package edu.nyu.cs.connectfour.container.factory;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.swing.JFrame;

import edu.nyu.cs.connectfour.container.GetContainerable;
import edu.nyu.cs.connectfour.container.impl.GameContainer;
import edu.nyu.cs.connectfour.utils.ParameterChecker;

/**
 * @author shenli
 * <p>
 * Factory object that can vend main component for the game.
 * <p>
 * NOTE: The factory object is thread-safe.
 */
public class GameContainerFactory {
    private static Set<String> idSet = new CopyOnWriteArraySet<>();
    private static List<GetContainerable<JFrame>> containers = new CopyOnWriteArrayList<>();
    
    /**
     * Suppress default constructor for non-instantiable
     */
    private GameContainerFactory() {
        throw new AssertionError();
    }
    
    /**
     * Create a main component for the game based on the specific id, component width and height.
     * <p>
     * @param id the identifier of the component
     * @param width the width of this component in pixels
     * @param height the height of this component in pixels
     * @return a suitable game component
     */
    public static GetContainerable<JFrame> getGameContainer(String id, int width, int height) {
        ParameterChecker.nullCheck(id, "game container id");
        ParameterChecker.emptyCheck(id, "game container id");
        ParameterChecker.rangeCheck(width, "game container width");
        ParameterChecker.rangeCheck(height, "game container height");
        if (!idSet.add(id)) {
            throw new IllegalArgumentException("GameContainer id: " + id + " already taken!");
        }
        
        GetContainerable<JFrame> container = new GameContainer(id, width, height);
        containers.add(container);
        return container;
    }
    
    /**
     * Returns an unmodifiable view of the {@link edu.nyu.cs.connectfour.container.GetContainerable} list. 
     * This method allows modules to provide users with "read-only" access to internal lists. Query operations 
     * on the returned list "read through" to the specified list, and attempts to modify the returned list, 
     * whether direct or via its iterator, result in an {@code UnsupportedOperationException}.
     * <p>
     * @return an unmodifiable view of the specified list. If no {@link edu.nyu.cs.connectfour.container.GetContainerable}
     * have been created, returns an empty list
     */
    public static List<GetContainerable<JFrame>> getGames() {
        return Collections.unmodifiableList(containers);
    }
    
}
