package edu.nyu.cs.connectfour.container.impl;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import edu.nyu.cs.connectfour.container.GetContainerable;
import edu.nyu.cs.connectfour.ui.factory.PanelFactory;
import edu.nyu.cs.connectfour.ui.menu.GameMenuBar;
import edu.nyu.cs.connectfour.ui.type.PanelType;

/**
 * @author shenli
 * <p>
 * A {@code GameContainer} object is used to create an "Game" main window.
 * <p>
 * The {@code GameContainer} object contains a {@link javax.swing.JFrame} component. {@code GameContainer} 
 * object may be obtains by calls on FrameFactory.getGame factory methods. These will return the {@code GameContainer} object.
 * <p>
 * <b>Warning:</b> Swing is not thread safe.
 */
public class GameContainer implements GetContainerable<JFrame> {
    private static final String ICON_PATH = "res/symbol.png";
    private static final String TITLE = "Connect Four";
    
    private final JFrame frame;
    private final String id;
    
    /**
     * Creates an "Game" main frame with the specific identifier, width and height.
     * <p>
     * @param id the identify of this game container object
     * @param width the width of this component in pixels
     * @param height the height of this component in pixels
     */
    public GameContainer(String id, int width, int height) {
        this.id = id;
        this.frame = new JFrame();
        this.frame.setTitle(TITLE + " @ " + id);
        this.frame.setIconImage(new ImageIcon(ICON_PATH, "Game Symbol").getImage());
        this.frame.setJMenuBar(GameMenuBar.INSTANCE.getContainer());
        this.frame.setLayout(new BorderLayout());
        this.frame.add(BorderLayout.NORTH, PanelFactory.getPanel(PanelType.PLAYER).getContainer());
        this.frame.add(BorderLayout.SOUTH, PanelFactory.getPanel(PanelType.CONTROL).getContainer());
        this.frame.add(BorderLayout.WEST, PanelFactory.getPanel(PanelType.EMPTY).getContainer());
        this.frame.add(BorderLayout.EAST, PanelFactory.getPanel(PanelType.UTILITY).getContainer());
        this.frame.add(BorderLayout.CENTER, PanelFactory.getPanel(PanelType.CHESS).getContainer());
        
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(width, height);
        this.frame.setResizable(false);
        this.frame.setLocationRelativeTo(null);
    }
    
    /**
     * Return string representation of this game container identifier.
     * @return string representation of this game container identifier
     */
    public String getID() {
        return id;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public JFrame getContainer() {
        return frame;
    }

}
