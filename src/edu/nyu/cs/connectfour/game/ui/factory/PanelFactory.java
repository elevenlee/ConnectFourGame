package edu.nyu.cs.connectfour.game.ui.factory;

import javax.swing.JPanel;

import edu.nyu.cs.connectfour.container.GetContainerable;
import edu.nyu.cs.connectfour.game.ui.panel.ChessPanel;
import edu.nyu.cs.connectfour.game.ui.panel.ControlPanel;
import edu.nyu.cs.connectfour.game.ui.panel.EmptyPanel;
import edu.nyu.cs.connectfour.game.ui.panel.PlayerPanel;
import edu.nyu.cs.connectfour.game.ui.panel.UtilityPanel;
import edu.nyu.cs.connectfour.game.ui.type.PanelType;
import edu.nyu.cs.connectfour.utils.ParameterChecker;

/**
 * @author shenli
 * <p>
 * Factory object that can vend panel based on the type of panel.
 * <p>
 * NOTE: the factory object is thread-safe.
 */
public class PanelFactory {

    /**
     * Suppress default constructor for non-instantiable
     */
    private PanelFactory() {
        throw new AssertionError();
    }
    
    /**
     * Returns a panel based on the panel type.
     * <p>
     * @param type the type of panel
     * @return a suitable panel
     * @throws IllegalArgumentException if panel type does not exist
     */
    public static GetContainerable<JPanel> getPanel(PanelType type) {
        ParameterChecker.nullCheck(type, "panel type");
        
        switch (type) {
            case CHESS:     return ChessPanel.INSTANCE;
            case PLAYER:    return PlayerPanel.INSTANCE;
            case CONTROL:   return ControlPanel.INSTANCE;
            case UTILITY:   return UtilityPanel.INSTANCE;
            case EMPTY:     return EmptyPanel.INSTANCE;
        }
        throw new IllegalArgumentException("No such panel type: " + type);
    }
    
}
