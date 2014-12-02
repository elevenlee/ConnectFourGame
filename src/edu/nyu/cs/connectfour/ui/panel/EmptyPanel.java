package edu.nyu.cs.connectfour.ui.panel;

import java.awt.Dimension;

import javax.swing.JPanel;

import edu.nyu.cs.connectfour.container.GetContainerable;

/**
 * @author shenli
 * <p>
 * An {@code EmptyPanel} object is used to create an "Empty" panel.
 * <p>
 * The singleton {@code EmptyPanel} object contains a {@link javax.swing.JPanel} component. {@code EmptyPanel}
 * object may be obtains by calls on {@link edu.nyu.cs.connectfour.ui.factory.PanelFactory#getPanel(edu.nyu.cs.connectfour.game.ui.type.PanelType)} 
 * factory methods. These will return the singleton {@code EmptyPanel}.
 * <p>
 * <b>Warning:</b> Swing is not thread safe.
 */
public enum EmptyPanel implements GetContainerable<JPanel> {
    /**
     * The singleton instance presents empty panel swing.
     */
    INSTANCE(20, 800);

    private final JPanel emptyPanel;
    
    /**
     * Creates an "Empty" panel with the specified width and height.
     * <p>
     * @param width the width of this component in pixels
     * @param height the height of this component in pixels
     */
    private EmptyPanel(int width, int height) {
        assert width > 0;
        assert height > 0;
        
        emptyPanel = new JPanel();
        emptyPanel.setPreferredSize(new Dimension(width, height));
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel getContainer() {
        return emptyPanel;
    }

}
