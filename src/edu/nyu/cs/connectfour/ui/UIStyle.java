package edu.nyu.cs.connectfour.ui;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * @author shenli
 * <p>
 * The {@code UIStyle} enum used to provide "Look and Feel" component to support game.
 * <p>
 * {@code UIStyle} are constant; their values cannot be changed after they are created. Because {@code UIStyle} 
 * objects are immutable they can be shared.
 */
public enum UIStyle {
    /**
     * The default cross platform look and feel -- the Java Look and Feel (JLF)
     */
    CROSS("cross") {
        
        @Override
        void setLook() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        }
        
    },
    /**
     * The native system look and feel.
     */
    SYSTEM("system") {
        
        @Override
        void setLook() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        
    },
    /**
     * The swing motif look and feel.
     */
    MOTIF("motif") {
        
        @Override
        void setLook() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
        }
        
    };
    
    private final String symbol;
    
    /**
     * Create a {@code UIStyle} object with the specific symbol.
     * <p>
     * @param symbol the symbol of this object
     */
    private UIStyle(String symbol) {
        assert symbol != null;
        
        this.symbol = symbol;
    }
    
    /**
     * Return string representation of this object. The string representation consists of the symbol.
     * <p>
     * @return string representation of this object
     */
    @Override
    public String toString() {
        return symbol;
    }
    
    /**
     * Set the Swing look and feel component.
     * <p>
     * @throws ClassNotFoundException if the {@link javax.swing.LookAndFeel} class could not be found
     * @throws InstantiationException if a new instance of the class couldn't be created
     * @throws IllegalAccessException if the class or initializer isn't accessible
     * @throws UnsupportedLookAndFeelException if {@code lnf.isSupportedLookAndFeel()} is false
     */
    abstract void setLook() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException;
    
}
