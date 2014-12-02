package edu.nyu.cs.connectfour.utils;

/**
 * @author shenli
 * <p>
 * A non-instantiability {@code ParameterChecker} object is used to check parameter illegally.
 */
public class ParameterChecker {
    
    /**
     * Suppress default constructor for non-instantiable
     */
    private ParameterChecker() {
        throw new AssertionError();
    }
    
    /**
     * Check integer whether less than zero. If the integer is less than zero,  {@code IllegalArgumentException} 
     * would be thrown.
     * <p>
     * @param arg the specified integer to be check
     * @param name the variable name
     * @throws IllegalArgumentException if arg parameter less than zero
     */
    public static void rangeCheck(int arg, String name) {
        if (arg < 0) {
            throw new IllegalArgumentException(name + ": " + arg);
        }
    }
    
    /**
     * Check object whether is {@code null}. If the object is {@code null}, {@code NullPointerException} would
     * be thrown.
     * <p>
     * @param <T> specified object type
     * @param arg the specified object to be check
     * @param name the variable name
     * @throws NullPointerException if parameter arg is {@code null}
     */
    public static <T> void nullCheck(T arg, String name) {
        if (arg == null) {
            throw new NullPointerException(name + ": " + arg);
        }
    }
    
    /**
     * Check whether is empty string. If the string is empty, {@code IllegalArgumentException} would be thrown.
     * <p>
     * @param arg the specified string to be check
     * @param name the variable name
     */
    public static void emptyCheck(String arg, String name) {
        if ("".equals(arg)) {
            throw new IllegalArgumentException(name + ": " + arg + " empty!");
        }
    }

}
