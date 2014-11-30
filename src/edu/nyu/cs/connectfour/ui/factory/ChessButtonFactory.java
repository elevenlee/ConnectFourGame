package edu.nyu.cs.connectfour.ui.factory;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JButton;

import edu.nyu.cs.connectfour.ui.chess.ChessButton;

/**
 * @author shenli
 * <p>
 * Factory object that can vend {@link javax.swing.JButton} for the game.
 * <p>
 * NOTE: The factory object is thread-safe.
 */
public class ChessButtonFactory {
    private static final List<JButton> buttons = new CopyOnWriteArrayList<>();
    
    /**
     * Suppress default constructor for non-instantiable
     */
    private ChessButtonFactory() {
        throw new AssertionError();
    }
    
    /**
     * Create a {@link javax.swing.JButton} object for the game.
     * <p>
     * @return a suitable {@link javax.swing.JButton}
     */
    public static JButton getChessButton() {
        JButton cb = new ChessButton();
        buttons.add(cb);
        return cb;
    }
    
    /**
     * Returns an unmodifiable view of the {@link javax.swing.JButton} list. This method allows modules to 
     * provide users with "read-only" access to internal lists. Query operations on the returned list "read 
     * through" to the specified list, and attempts to modify the returned list, whether direct or via its 
     * iterator, result in an {@code UnsupportedOperationException}.
     * <p>
     * @return an unmodifiable view of the specified list. If no {@link javax.swing.JButton} have been created, 
     * returns an empty list
     */
    public static List<JButton> getChessButtons() {
        return Collections.unmodifiableList(buttons);
    }
    
}
