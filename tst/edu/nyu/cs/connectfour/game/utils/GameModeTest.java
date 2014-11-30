package edu.nyu.cs.connectfour.game.utils;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class GameModeTest {
	private List<GameMode> modeList;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		modeList = Arrays.asList(GameMode.values());
	}

	/**
	 * Test method for {@link edu.nyu.cs.connectfour.game.utils.GameMode#getLabels()}.
	 */
	@Test
	public void testGetLabels() {
		List<String> labels = Arrays.asList("Me", "Computer", "Player One", "Player Two");
		for (int i = 0; i < modeList.size(); i++) {
			List<String> labelList = modeList.get(i).getLabels();
			for (int j = 0; j < labelList.size(); j++) {
				assertEquals(labels.get(i * 2 + j), labelList.get(j));
			}
		}
	}

	/**
	 * Test method for {@link edu.nyu.cs.connectfour.game.utils.GameMode#toString()}.
	 */
	@Test
	public void testToString() {
		List<String> descriptions = Arrays.asList("against computer", "against human");
		for (int i = 0; i < modeList.size(); i++) {
			assertEquals(descriptions.get(i), modeList.get(i).toString());
		}
	}

}
