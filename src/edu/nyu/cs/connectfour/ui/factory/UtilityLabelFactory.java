package edu.nyu.cs.connectfour.ui.factory;

import javax.swing.JLabel;

import edu.nyu.cs.connectfour.container.GetContainerable;
import edu.nyu.cs.connectfour.ui.label.ResultLabel;
import edu.nyu.cs.connectfour.ui.label.TimerLabel;
import edu.nyu.cs.connectfour.ui.type.UtilityLabelType;
import edu.nyu.cs.connectfour.utils.ParameterChecker;

/**
 * @author shenli
 * <p>
 * Factory object that can vend utility label based on the type of utility label.
 * <p>
 * NOTE: The factory object is thread-safe.
 */
public class UtilityLabelFactory {
    
    /**
     * Suppress default constructor for non-instantiable
     */
    private UtilityLabelFactory() {
        throw new AssertionError();
    }
    
    /**
     * Returns an utility label based on the utility label type.
     * <p>
     * @param type the type of utility label
     * @return a suitable utility label
     * @throws IllegalArgumentException if utility label type does not exist
     */
    public static GetContainerable<JLabel> getLabel(UtilityLabelType type) {
        ParameterChecker.nullCheck(type, "utility label type");
        
        switch (type) {
            case RESULT: return ResultLabel.INSTANCE;
            case TIMER:  return TimerLabel.INSTANCE;
        }
        throw new IllegalArgumentException("No such utility label type: " + type);
    }
    
}
