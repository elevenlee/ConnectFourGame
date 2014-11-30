package edu.nyu.cs.connectfour.player.subject.impl;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import edu.nyu.cs.connectfour.player.observer.PlayerInfoObserver;

public class PlayerInfoTest {
    private List<PlayerInfo> playerList;
    private List<String> nameList;
    private List<Color> colorList;
    
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        playerList = Arrays.asList(PlayerInfo.values());
        nameList = Arrays.asList("Me", "Computer");
        colorList = Arrays.asList(Color.RED, Color.YELLOW);
    }

    /**
     * Test method for {@link edu.nyu.cs.connectfour.player.subject.impl.PlayerInfo#getName()}.
     */
    @Test
    public void testGetName() {
        for (int i = 0; i < playerList.size(); i++) {
            assertEquals(nameList.get(i), playerList.get(i).getName());
        }
    }

    /**
     * Test method for {@link edu.nyu.cs.connectfour.player.subject.impl.PlayerInfo#getColor()}.
     */
    @Test
    public void testGetColor() {
        for (int i = 0; i < playerList.size(); i++) {
            assertEquals(colorList.get(i), playerList.get(i).getColor());
        }
    }

    /**
     * Test method for {@link edu.nyu.cs.connectfour.player.subject.impl.PlayerInfo#setAll(java.lang.String, java.awt.Color)}.
     */
    @Test(expected = NullPointerException.class)
    public void testSetAllWithTwoNullValues() {
        for (PlayerInfo pi : playerList) {
            pi.setAll(null, null);
        }
    }
    
    /**
     * Test method for {@link edu.nyu.cs.connectfour.player.subject.impl.PlayerInfo#setAll(java.lang.String, java.awt.Color)}.
     */
    @Test(expected = NullPointerException.class)
    public void testSetAllWithNameNullValue() {
        for (PlayerInfo pi : playerList) {
            pi.setAll(null, Color.GREEN);
        }
    }
    
    /**
     * Test method for {@link edu.nyu.cs.connectfour.player.subject.impl.PlayerInfo#setAll(java.lang.String, java.awt.Color)}.
     */
    @Test(expected = NullPointerException.class)
    public void testSetAllWithColorNullValue() {
        for (PlayerInfo pi : playerList) {
            pi.setAll("Sheldon", null);
        }
    }
    
    /**
     * Test method for {@link edu.nyu.cs.connectfour.player.subject.impl.PlayerInfo#setAll(java.lang.String, java.awt.Color)}.
     */
    @Test
    public void testSetAllWithNoNullValue() {
        List<String> expectedNames =
                Arrays.asList("Sheldon", "Michael Johnson", "Leonard", "Ho0987", "><*&^");
        List<Color> expectedColors = 
                Arrays.asList(Color.GREEN,
                        Color.getColor("New", Color.BLACK),
                        Color.getColor("gray"),
                        Color.getHSBColor(12, 45, 219),
                        Color.getColor("Test", 5));
        for (int i = 0; i < playerList.size(); i++) {
            playerList.get(i).setAll(expectedNames.get(i), expectedColors.get(i));
            assertEquals(expectedNames.get(i), playerList.get(i).getName());
            assertEquals(expectedColors.get(i), playerList.get(i).getColor());
        }
    }
    
    /**
     * Test method for {@link edu.nyu.cs.connectfour.player.subject.impl.PlayerInfo#registerPlayerInfoObserver(edu.nyu.cs.connectfour.player.observer.PlayerInfoObserver)}.
     */
    @Test(expected = NullPointerException.class)
    public void testRegisterPlayerInfoObserverWithNullValue() {
        for (PlayerInfo pi : playerList) {
            pi.registerPlayerInfoObserver(null);
        }
    }
    
    /**
     * Test method for {@link edu.nyu.cs.connectfour.player.subject.impl.PlayerInfo#registerPlayerInfoObserver(edu.nyu.cs.connectfour.player.observer.PlayerInfoObserver)}.
     */
    @Test
    public void testRegisterPlayerInfoObserverWithNoNullValue() {
        for (PlayerInfo pi : playerList) {
            PlayerInfoObserver mockPlayerInfoObserver = EasyMock.createMock(PlayerInfoObserver.class);
            pi.registerPlayerInfoObserver(mockPlayerInfoObserver);
            mockPlayerInfoObserver.updatePlayerInfo(pi, "Lee", Color.BLUE);
            EasyMock.replay(mockPlayerInfoObserver);
            pi.setAll("Lee", Color.BLUE);
            EasyMock.verify(mockPlayerInfoObserver);
            pi.removePlayerInfoObserver(mockPlayerInfoObserver);
        }
    }
    
    /**
     * Test method for {@link edu.nyu.cs.connectfour.player.subject.impl.PlayerInfo#removePlayerInfoObserver(edu.nyu.cs.connectfour.player.observer.PlayerInfoObserver)}.
     */
    @Test
    public void testRemovePlayerInfoObserverWithNullValue() {
        for (PlayerInfo pi : playerList) {
            pi.removePlayerInfoObserver(null);
        }
    }
    
    /**
     * Test method for {@link edu.nyu.cs.connectfour.player.subject.impl.PlayerInfo#removePlayerInfoObserver(edu.nyu.cs.connectfour.player.observer.PlayerInfoObserver)}.
     */
    @Test
    public void testRemovePlayerInfoObserverWithNotExistValue() {
        for (PlayerInfo pi : playerList) {
            PlayerInfoObserver mockPlayerInfoObserver = EasyMock.createMock(PlayerInfoObserver.class);
            PlayerInfoObserver mockPlayerInfoObserverDeleted = EasyMock.createMock(PlayerInfoObserver.class);
            pi.registerPlayerInfoObserver(mockPlayerInfoObserver);
            pi.removePlayerInfoObserver(mockPlayerInfoObserverDeleted);
            mockPlayerInfoObserver.updatePlayerInfo(pi, "Lee", Color.GREEN);
            EasyMock.replay(mockPlayerInfoObserver);
            pi.setAll("Lee", Color.GREEN);
            EasyMock.verify(mockPlayerInfoObserver);
            pi.removePlayerInfoObserver(mockPlayerInfoObserver);
        }
    }
    
    /**
     * Test method for {@link edu.nyu.cs.connectfour.player.subject.impl.PlayerInfo#removePlayerInfoObserver(edu.nyu.cs.connectfour.player.observer.PlayerInfoObserver)}.
     */
    @Test
    public void testRemovePlayerInfoObserverWithExistValue() {
        for (PlayerInfo pi : playerList) {
            PlayerInfoObserver mockPlayerInfoObserver = EasyMock.createMock(PlayerInfoObserver.class);
            pi.registerPlayerInfoObserver(mockPlayerInfoObserver);
            pi.removePlayerInfoObserver(mockPlayerInfoObserver);
            EasyMock.replay(mockPlayerInfoObserver);
            pi.setAll("Lee", Color.GREEN);
            EasyMock.verify(mockPlayerInfoObserver);
        }
    }
    
    /**
     * Test method for {@link edu.nyu.cs.connectfour.player.subject.impl.PlayerInfo#notifyPlayerInfoObservers()}.
     */
    @Test
    public void testNotifyPlayerInfoObservers() {
        for (PlayerInfo pi : playerList) {
            PlayerInfoObserver mockPlayerInfoObserver = EasyMock.createMock(PlayerInfoObserver.class);
            pi.registerPlayerInfoObserver(mockPlayerInfoObserver);
            mockPlayerInfoObserver.updatePlayerInfo(pi, "Lee", Color.ORANGE);
            EasyMock.replay(mockPlayerInfoObserver);
            pi.setAll("Lee", Color.ORANGE);
            EasyMock.verify(mockPlayerInfoObserver);
            pi.removePlayerInfoObserver(mockPlayerInfoObserver);
        }
    }

    /**
     * Test method for {@link edu.nyu.cs.connectfour.player.subject.impl.PlayerInfo#toString()}.
     */
    @Test
    public void testToString() {
        List<String> expected = Arrays.asList("Me/-65536", "Computer/-256");
        for (int i = 0; i < PlayerInfo.values().length; i++) {
            playerList.get(i).setAll(nameList.get(i), colorList.get(i));
            assertEquals(expected.get(i), playerList.get(i).toString());
        }
    }

}
