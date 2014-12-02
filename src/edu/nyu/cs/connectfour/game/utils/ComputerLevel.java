package edu.nyu.cs.connectfour.game.utils;

/**
 * @author shenli
 * <p>
 * The {@code ComputerLevel} enum represents computer level.
 * <p>
 * {@code ComputerLevel} are constant; their values could not be changed after they are created. Because
 * {@code ComputerLevel} objects are immutable they could be shared.
 */
public enum ComputerLevel {
    /**
     * Beginner level
     */
    BEGINNER(0), 
    /**
     * Amateur level
     */
    AMATEUR(1), 
    /**
     * Regular level
     */
    REGULAR(2), 
    /**
     * Profession level
     */
    PROFESSION(3), 
    /**
     * Abnormal level
     */
    ABNORMAL(4);
    
    private final int degree;
    
    /**
     * Initializes a newly created {@code ComputerLevel} object so that it records level degree.
     * <p>
     * @param degree the computer level degree
     */
    private ComputerLevel(int degree) {
        assert degree >= 0;
        
        this.degree = degree;
    }

    /**
     * Returns compute level degree
     * <p>
     * @return computer level degree
     */
    public int getDegree() {
        return degree;
    }
    
}
