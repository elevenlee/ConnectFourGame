package edu.nyu.cs.connectfour.game.ai;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ComputerThinkingTest {
	private ComputerThinking computerThink;
	private int row = 6;
	private int column = 7;
	private int[] next = new int[column];
	private int[][] state = new int[row][column];
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		for (int j = 0; j < column; j++) {
			for (int i = 0; i < row; i++) {
				state[i][j] = -1;
			}
			next[j] = row - 1;
		}
		computerThink = new ComputerThinking(row, column, next, state);
	}

	/**
	 * Test method for {@link edu.nyu.cs.connectfour.game.ai.ComputerThinking#ComputerThinking(int, int, int[], int[][])}.
	 */
	@Test
	public void testComputerThinkingWithAllIllegalValues() {
		@SuppressWarnings("unused")
		ComputerThinking ct = new ComputerThinking(-3, -5, null, null);
	}
	
	/**
	 * Test method for {@link edu.nyu.cs.connectfour.game.ai.ComputerThinking#ComputerThinking(int, int, int[], int[][])}.
	 */
	@Test
	public void testComputerThinkingWithTwoNegativeValues() {
		@SuppressWarnings("unused")
		ComputerThinking ct = new ComputerThinking(-3, -5, new int[4], new int[5][9]);
	}
	
	/**
	 * Test method for {@link edu.nyu.cs.connectfour.game.ai.ComputerThinking#ComputerThinking(int, int, int[], int[][])}.
	 */
	@Test
	public void testComputerThinkingWithOneNegativeValue() {
		@SuppressWarnings("unused")
		ComputerThinking ct = new ComputerThinking(-3, 5, new int[4], new int[5][9]);
	}
	
	/**
	 * Test method for {@link edu.nyu.cs.connectfour.game.ai.ComputerThinking#ComputerThinking(int, int, int[], int[][])}.
	 */
	@Test
	public void testComputerThinkingWithTwoNullValues() {
		@SuppressWarnings("unused")
		ComputerThinking ct = new ComputerThinking(5, 9, null, null);
	}
	
	/**
	 * Test method for {@link edu.nyu.cs.connectfour.game.ai.ComputerThinking#ComputerThinking(int, int, int[], int[][])}.
	 */
	@Test
	public void testComputerThinkingWithOneNullValue() {
		@SuppressWarnings("unused")
		ComputerThinking ct = new ComputerThinking(5, 9, new int[9], null);
	}
	
	/**
	 * Test method for {@link edu.nyu.cs.connectfour.game.ai.ComputerThinking#ComputerThinking(int, int, int[], int[][])}.
	 */
	@Test
	public void testComputerThinkingWithAllLegalValues() {
		@SuppressWarnings("unused")
		ComputerThinking ct = new ComputerThinking(5, 9, new int[9], new int[5][1]);
	}

	/**
	 * Test method for {@link edu.nyu.cs.connectfour.game.ai.ComputerThinking#isWin(int, int, int)}.
	 */
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testIsWinWithThreeNegativeValues() {
		ComputerThinking ct = new ComputerThinking(5, 9, new int[9], new int[5][1]);
		ct.isWin(-3, -4, -1);
	}
	
	/**
	 * Test method for {@link edu.nyu.cs.connectfour.game.ai.ComputerThinking#isWin(int, int, int)}.
	 */
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testIsWinWithTwoNegativeValues() {
		ComputerThinking ct = new ComputerThinking(5, 9, new int[9], new int[5][1]);
		ct.isWin(-3, -4, 2);
	}

	/**
	 * Test method for {@link edu.nyu.cs.connectfour.game.ai.ComputerThinking#isWin(int, int, int)}.
	 */
	@Test
	public void testIsWinWithOneNegativeValue() {
		ComputerThinking ct = new ComputerThinking(5, 9, new int[9], new int[5][1]);
		ct.isWin(-3, 4, 0);
	}
	
	/**
	 * Test method for {@link edu.nyu.cs.connectfour.game.ai.ComputerThinking#isWin(int, int, int)}.
	 */
	@Test
	public void testIsWinWithAllLegalValue() {
		ComputerThinking ct = new ComputerThinking(6, 7, new int[7], new int[6][7]);
		assertTrue(ct.isWin(2, 3, 0));
	}
	
	/**
	 * Test method for {@link edu.nyu.cs.connectfour.game.ai.ComputerThinking#isDraw(int)}.
	 */
	@Test
	public void testIsDrawWithNegativeValue() {
		ComputerThinking ct = new ComputerThinking(6, 7, new int[6], new int[6][7]);
		ct.isDraw(-9);
	}
	
	/**
	 * Test method for {@link edu.nyu.cs.connectfour.game.ai.ComputerThinking#isDraw(int)}.
	 */
	@Test
	public void testIsDrawWithZeroValue() {
		ComputerThinking ct = new ComputerThinking(6, 7, new int[6], new int[6][7]);
		assertFalse(ct.isDraw(0));
	}
	
	/**
	 * Test method for {@link edu.nyu.cs.connectfour.game.ai.ComputerThinking#isDraw(int)}.
	 */
	@Test
	public void testIsDrawWithPositiveValue() {
		ComputerThinking ct = new ComputerThinking(6, 7, new int[6], new int[6][7]);
		assertFalse(ct.isDraw(32));
		assertTrue(ct.isDraw(42));
	}

	/**
	 * Test method for {@link edu.nyu.cs.connectfour.game.ai.ComputerThinking#bestValue(int, int)}.
	 */
	@Test
	public void testBestValue() {
		assertEquals(3, computerThink.bestValue(2, 0));
		assertEquals(3, computerThink.bestValue(3, 0));
		assertEquals(3, computerThink.bestValue(4, 0));
	}
	
	/**
	 * Test method for {@link edu.nyu.cs.connectfour.game.ai.ComputerThinking#equals(Object)}.
	 */
	@Test
	public void testEqualsWithNullValue() {
		assertFalse(computerThink.equals(null));
	}
	
	/**
	 * Test method for {@link edu.nyu.cs.connectfour.game.ai.ComputerThinking#equals(Object)}.
	 */
	@Test
	public void testEqualsWithThisValue() {
		assertTrue(computerThink.equals(computerThink));
	}
	
	/**
	 * Test method for {@link edu.nyu.cs.connectfour.game.ai.ComputerThinking#equals(Object)}.
	 */
	@Test
	public void testEqualsWithOtherObjectValue() {
		assertFalse(computerThink.equals(new String("Test")));
	}
	
	/**
	 * Test method for {@link edu.nyu.cs.connectfour.game.ai.ComputerThinking#equals(Object)}.
	 */
	@Test
	public void testEqualsWithComputerThinkingObjectValue() {
		ComputerThinking ct = new ComputerThinking(row, column, next, state);
		assertTrue(computerThink.equals(ct));
		
		int[] next2 = new int[column];
		int[][] state2 = new int[row][column];
		for (int j = 0; j < column; j++) {
			for (int i = 0; i < row; i++) {
				state2[i][j] = -1;
			}
			next2[j] = row - 1;
		}
		ComputerThinking ct2 = new ComputerThinking(row, column, next2, state2);
		assertFalse(computerThink.equals(ct2));
		ComputerThinking ct3 = new ComputerThinking(8, column, next, state);
		assertFalse(computerThink.equals(ct3));
		ComputerThinking ct4 = new ComputerThinking(row, 2, next, state);
		assertFalse(computerThink.equals(ct4));
		ComputerThinking ct5 = new ComputerThinking(row, column, next, state2);
		assertFalse(computerThink.equals(ct5));
	}
	
	/**
	 * Test method for {@link edu.nyu.cs.connectfour.game.ai.ComputerThinking#toString()}.
	 */
	@Test
	public void testToString() {
		StringBuilder sb = new StringBuilder();
		sb.append(row);
		sb.append("," + column);
		for (int n : next) {
			sb.append("," + n);
		}
		for (int r[] : state) {
			for (int c : r) {
				sb.append("," + c);
			}
		}
		sb.append(",2");
		assertEquals(sb.toString(), computerThink.toString());
	}
	
}
