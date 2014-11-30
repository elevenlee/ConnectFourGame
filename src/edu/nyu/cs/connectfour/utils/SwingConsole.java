package edu.nyu.cs.connectfour.utils;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * @author shenli
 * <p>
 * A non-instantiability {@code SwingConsole} object used to show game container.
 */
public class SwingConsole {

    /**
     * Suppress default constructor for non-instantiable
     */
    private SwingConsole() {
        throw new AssertionError();
    }
    
    /**
     * Launch the specific game container.
     * <p>
     * @param frame the specific container ready to launch
     */
    public static void run(final JFrame frame) {
        ParameterChecker.nullCheck(frame, "game container");
        
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                frame.setVisible(true);
            }
            
        });
    }
    
}
