package edu.nyu.cs.connectfour.ui;

import javax.swing.UIManager;

/**
 * @author shenli
 * <p>
 * The {@code UIStyle} enum used to provide "Look and Feel" component to support game.
 * <p>
 * {@code UIStyle} are constant; their values cannot be changed after they are created. Because {@code UIStyle} 
 * objects are immutable they can be shared.
 */
public enum UIStyle {
    CROSS("cross") {
        
        @Override
        void setLook() {
            try {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }   
        }
        
    },
    SYSTEM("system") {
        
        @Override
        void setLook() {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
    },
    MOTIF("motif") {
        
        @Override
        void setLook() {
            try {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
            } catch (Exception e) {
                e.printStackTrace();
            }
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
     * Set the Swing look and feel component
     */
    abstract void setLook();
    
}
