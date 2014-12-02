package edu.nyu.cs.connectfour.ui.panel;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;

import edu.nyu.cs.connectfour.container.GetContainerable;
import edu.nyu.cs.connectfour.ui.factory.UtilityLabelFactory;
import edu.nyu.cs.connectfour.ui.type.UtilityLabelType;

/**
 * @author shenli
 * <p>
 * An {@code UtilityPanel} object is used to create an "Utility information" panel.
 * <p>
 * The singleton {@code UtilityPanel} object contains a {@link javax.swing.JPanel} component. {@code UtilityPanel}
 * object may be obtains by calls on {@link edu.nyu.cs.connectfour.ui.factory.PanelFactory#getPanel(edu.nyu.cs.connectfour.ui.type.PanelType)} 
 * factory methods. These will return the singleton {@code UtilityPanel}.
 * <p>
 * <b>Warning:</b> Swing is not thread safe.
 */
public enum UtilityPanel implements GetContainerable<JPanel> {
    /**
     * The singleton instance presents utility panel swing.
     */
    INSTANCE(200, 800);

    private final JPanel utilPanel;
    
    /**
     * Creates an "Utility information" panel with the specified width and height.
     * <p>
     * @param width the width of this component in pixels
     * @param height the height of this component in pixels
     */
    private UtilityPanel(int width, int height) {
        assert width > 0;
        assert height > 0;
        
        this.utilPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 80));
        this.utilPanel.setPreferredSize(new Dimension(width, height));
        for (UtilityLabelType ult : UtilityLabelType.values()) {
            utilPanel.add(UtilityLabelFactory.getLabel(ult).getContainer());
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel getContainer() {
        return utilPanel;
    }

}
