package edu.nyu.cs.connectfour.utils;

import org.junit.Test;

public class ParameterCheckerTest {

	/**
	 * Test method for {@link edu.nyu.cs.connectfour.utils.ParameterChecker#rangeCheck(int, java.lang.String)}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testRangeCheckWithNegative() {
		ParameterChecker.rangeCheck(-5, "Nagavie value");
	}

	/**
	 * Test method for {@link edu.nyu.cs.connectfour.utils.ParameterChecker#rangeCheck(int, java.lang.String)}.
	 */
	@Test
	public void testRangeCheckWithZero() {
	    ParameterChecker.rangeCheck(0, "Zero value");
	}
	
	/**
	 * Test method for {@link edu.nyu.cs.connectfour.utils.ParameterChecker#rangeCheck(int, java.lang.String)}.
	 */
	@Test
	public void testRangeCheckWithPositive() {
	    ParameterChecker.rangeCheck(10, "Positive value");
	}
	
	/**
	 * Test method for {@link edu.nyu.cs.connectfour.utils.ParameterChecker#nullCheck(java.lang.Object, java.lang.String)}.
	 */
	@Test(expected = NullPointerException.class)
	public void testNullCheckWithNull() {
	    ParameterChecker.nullCheck(null, "Null value");
	}
	
	/**
	 * Test method for {@link edu.nyu.cs.connectfour.utils.ParameterChecker#nullCheck(java.lang.Object, java.lang.String)}.
	 */
	@Test
	public void testNullCheckWithNotNull() {
		ParameterChecker.nullCheck(new Object(), "Not null value");
	}

	/**
	 * Test method for {@link edu.nyu.cs.connectfour.utils.ParameterChecker#emptyCheck(java.lang.String, java.lang.String)}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testEmptyCheckWithEmptyString() {
	    ParameterChecker.emptyCheck("", "Empty value");
	}
	
	/**
	 * Test method for {@link edu.nyu.cs.connectfour.utils.ParameterChecker#emptyCheck(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testEmptyCheckWithNullString() {
	    ParameterChecker.emptyCheck(null, "Null value");
	}
	
	/**
	 * Test method for {@link edu.nyu.cs.connectfour.utils.ParameterChecker#emptyCheck(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testEmptyCheckWithNotEmptyString() {
	    ParameterChecker.emptyCheck("Test", "Not Empty value");
	}

}
