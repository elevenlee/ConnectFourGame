package edu.nyu.cs.connectfour.game.ui.label;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import edu.nyu.cs.connectfour.container.GetContainerable;

/**
 * @author shenli
 * <p>
 * A {@code TimerLabel} object is used to create an "Timer" label.
 * <p>
 * The singleton {@code TimerLabel} object contains a {@link javax.swing.JLabel} component. {@code TimerLabel}
 * object may be obtains by calls on {@link edu.nyu.cs.connectfour.game.ui.factory.UtilityLabelFactory#getLabel(edu.nyu.cs.connectfour.game.ui.type.UtilityLabelType)} 
 * factory methods. These will return the singleton {@code TimerLabel}.
 * <p>
 * <b>Warning:</b> Swing is not thread safe.
 */
public enum TimerLabel implements GetContainerable<JLabel> {
    INSTANCE("res/clock.png", "Timer");
    
    private final JLabel label;
    private final String iconPath;
    private final String tooltipText;
    
    /**
     * Creates an timer label with the specified label icon path and tool tip text.
     * <p>
     * @param iconPath the icon path of this label
     * @param tooltipText the tool tip text of this label
     */
    private TimerLabel(String iconPath, String tooltipText) {
        assert iconPath != null;
        assert tooltipText != null;
        
        this.iconPath = iconPath;
        this.tooltipText = tooltipText;
        this.label = new JLabel(
                new ImageIcon(
                        this.iconPath, "Util Symbol"),SwingConstants.LEADING);
        this.label.setPreferredSize(new Dimension(150, 100));
        this.label.setToolTipText(this.tooltipText);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JLabel getContainer() {
        return label;
    }
    
}
