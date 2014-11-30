package edu.nyu.cs.connectfour.ui.chess;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import javax.swing.JButton;

/**
 * @author shenli
 * <p>
 * An implementation of a "push" chess button.
 * <p>
 * Chess Buttons can be configured, and to some degree controlled, by Actions. Using an Action with a button 
 * has many benefits beyond directly configuring a button. {@code ChessButton} object may be obtains by calls 
 * on {@link edu.nyu.cs.connectfour.ui.factory.ChessButtonFactory#getChessButton()} factory methods. 
 * These will create a chess button.
 * <p>
 * <b>Warning:</b> Swing is not thread safe.
 */
public class ChessButton extends JButton {
    private static final long serialVersionUID = -5229743049810920053L;
    
    private Shape shape;
    
    /**
     * Creates a button with no set text or icon.
     */
    public ChessButton() {
        super();
        Dimension size = this.getPreferredSize();
        size.width = size.height = Math.max(size.width, size.height);
        this.setPreferredSize(size);
        this.setContentAreaFilled(false);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            g.setColor(Color.LIGHT_GRAY);
        } else {
            g.setColor(this.getBackground());
        }
        g.fillOval(2, 2, this.getSize().width - 4, this.getSize().height - 4);
        super.paintComponents(g);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean contains(int x, int y) {
        if ((shape == null)
                || (!shape.getBounds().equals(this.getBounds()))) {
            shape = new Ellipse2D.Float(0, 0, this.getWidth(), this.getHeight());
        }
        return shape.contains(x, y);
    }
    
}
