package edu.nyu.cs.connectfour.game.subject.impl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GameRecordTest {
	private GameRecord gameRecord;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		gameRecord = new GameRecord(6, 7);
	}

	/**
	 * Test method for {@link edu.nyu.cs.connectfour.game.subject.impl.GameRecord#GameRecord(int, int)}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGameRecordWithTwoNegativeValues() {
		@SuppressWarnings("unused")
		GameRecord gr = new GameRecord(-6, -8);
	}
	
	/**
	 * Test method for {@link edu.nyu.cs.connectfour.game.subject.impl.GameRecord#GameRecord(int, int)}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGameRecordWithOneNegativeValue() {
		@SuppressWarnings("unused")
		GameRecord gr = new GameRecord(-6, 8);
	}
	
	/**
	 * Test method for {@link edu.nyu.cs.connectfour.game.subject.impl.GameRecord#GameRecord(int, int)}.
	 */
	@Test
	public void testGameRecordWithPositiveValue() {
		@SuppressWarnings("unused")
		GameRecord gr = new GameRecord(6, 8);
	}

	/**
	 * Test method for {@link edu.nyu.cs.connectfour.game.subject.impl.GameRecord#place(int)}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testPlaceWithNegativeValue() {
		gameRecord.place(-4);
	}
	
	/**
	 * Test method for {@link edu.nyu.cs.connectfour.game.subject.impl.GameRecord#place(int)}.
	 */
	@Test
	public void testPlaceWithZeroValue() {
		gameRecord.place(0);
	}
	
	/**
	 * Test method for {@link edu.nyu.cs.connectfour.game.subject.impl.GameRecord#place(int)}.
	 */
	@Test
	public void testPlaceWithPositiveValue() {
		gameRecord.place(4);
	}

	/**
	 * Test method for {@link edu.nyu.cs.connectfour.game.subject.impl.GameRecord#nextPlay()}.
	 */
	@Test
	public void testNextPlay() {
		gameRecord.nextPlay();
	}
	
	/**
	 * Test method for {@link edu.nyu.cs.connectfour.game.subject.impl.GameRecord#equals(Object)}.
	 */
	@Test
	public void testEqualsWithNullValue() {
		assertFalse(gameRecord.equals(null));
	}
	
	/**
	 * Test method for {@link edu.nyu.cs.connectfour.game.subject.impl.GameRecord#equals(Object)}.
	 */
	@Test
	public void testEqualsWithThisValue() {
		assertTrue(gameRecord.equals(gameRecord));
	}
	
	/**
	 * Test method for {@link edu.nyu.cs.connectfour.game.subject.impl.GameRecord#equals(Object)}.
	 */
	@Test
	public void testEqualsWithOtherObjectValue() {
		assertFalse(gameRecord.equals(new String("Test")));
	}
	
	/**
	 * Test method for {@link edu.nyu.cs.connectfour.game.subject.impl.GameRecord#equals(Object)}.
	 */
	@Test
	public void testEqualsWithGameReocrdObjectValue() {
		assertFalse(gameRecord.equals(new GameRecord(6, 7)));
		assertFalse(gameRecord.equals(new GameRecord(3, 4)));
	}
	
	/**
	 * Test method for {@link edu.nyu.cs.connectfour.game.subject.impl.GameRecord#toString()}.
	 */
	@Test
	public void testToString() {
		StringBuilder sb = new StringBuilder();
		sb.append("6,7,0");
		for (int i = 0; i < 7; i++) {
			sb.append(",5");
		}
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				sb.append(",-1");
			}
		}
		sb.append(",against human,ABNORMAL,Me/-65536");
		assertEquals(sb.toString(), gameRecord.toString());
	}

}
