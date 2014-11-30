package edu.nyu.cs.connectfour.game.ui.factory;

import javax.swing.JDialog;

import edu.nyu.cs.connectfour.container.GetContainerable;
import edu.nyu.cs.connectfour.game.ui.dialog.AboutDialog;
import edu.nyu.cs.connectfour.game.ui.dialog.HelpDialog;
import edu.nyu.cs.connectfour.game.ui.dialog.PreferenceDialog;
import edu.nyu.cs.connectfour.game.ui.type.DialogType;

/**
 * @author shenli
 * <p>
 * Factory object that can vend dialog based on the type of dialog.
 * <p>
 * NOTE: The factory object is thread-safe.
 */
public class DialogFactory {
    
    /**
     * Suppress default constructor for non-instantiable
     */
    private DialogFactory() {
        throw new AssertionError();
    }
    
    /**
     * Returns a dialog based on the dialog type.
     * <p>
     * @param type the type of dialog
     * @return a suitable dialog
     * @throws IllegalArgumentException if dialog type does not exist
     */
    public static GetContainerable<JDialog> getDialog(DialogType type) {
        assert type != null;
        
        switch (type) {
            case PREFERENCE: return PreferenceDialog.INSTANCE;
            case HELP:       return HelpDialog.INSTANCE;
            case ABOUT:      return AboutDialog.INSTANCE;
        }
        throw new IllegalArgumentException("No such dialog type " + type);
    }
    
}
