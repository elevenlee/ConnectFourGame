package edu.nyu.cs.connectfour.game.utils;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ComputerLevelTest {
    private List<ComputerLevel> computerLevelList;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        computerLevelList = Arrays.asList(ComputerLevel.values());
    }

    /**
     * Test method for {@link edu.nyu.cs.connectfour.game.utils.ComputerLevel#getDegree()}.
     */
    @Test
    public void testGetDegree() {
        for (int i = 0; i < computerLevelList.size(); i++) {
            assertEquals(i, computerLevelList.get(i).getDegree());
        }
    }

}
