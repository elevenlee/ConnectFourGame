package edu.nyu.cs.connectfour.game.subject.impl;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import edu.nyu.cs.connectfour.game.observer.GameStatusObserver;
import edu.nyu.cs.connectfour.game.utils.ComputerLevel;
import edu.nyu.cs.connectfour.game.utils.GameMode;
import edu.nyu.cs.connectfour.player.subject.impl.PlayerInfo;

public class GameUtilityTest {
    private GameUtility gameUtility;
    
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        gameUtility = GameUtility.INSTANCE;
    }

    /**
     * Test method for {@link edu.nyu.cs.connectfour.game.subject.impl.GameUtility#getGameMode()}.
     */
    @Test
    public void testGetGameMode() {
        assertEquals(GameMode.HUMAN_VS_COMPUTER, gameUtility.getGameMode());
    }

    /**
     * Test method for {@link edu.nyu.cs.connectfour.game.subject.impl.GameUtility#getComputerLevel()}.
     */
    @Test
    public void testGetComputerLevel() {
        assertEquals(ComputerLevel.REGULAR, gameUtility.getComputerLevel());
    }

    /**
     * Test method for {@link edu.nyu.cs.connectfour.game.subject.impl.GameUtility#getOffensive()}.
     */
    @Test
    public void testGetOffensive() {
        assertEquals(PlayerInfo.PLAYER_TWO, gameUtility.getOffensive());
    }

    /**
     * Test method for {@link edu.nyu.cs.connectfour.game.subject.impl.GameUtility#getPlayerTurn()}.
     */
    @Test
    public void testGetPlayerTurn() {
        assertEquals(PlayerInfo.PLAYER_ONE, gameUtility.getPlayerTurn());
    }

    /**
     * Test method for {@link edu.nyu.cs.connectfour.game.subject.impl.GameUtility#isPlaying()}.
     */
    @Test
    public void testIsPlaying() {
        assertFalse(gameUtility.isPlaying());
    }

    /**
     * Test method for {@link edu.nyu.cs.connectfour.game.subject.impl.GameUtility#isGameOver()}.
     */
    @Test
    public void testIsGameOver() {
        assertTrue(gameUtility.isGameOver());
    }

    /**
     * Test method for {@link edu.nyu.cs.connectfour.game.subject.impl.GameUtility#setGameModeAndLevel(edu.nyu.cs.connectfour.game.utils.GameMode, edu.nyu.cs.connectfour.game.utils.ComputerLevel)}.
     */
    @Test(expected = NullPointerException.class)
    public void testSetGameModeAndLevelWithTwoNullValues() {
        gameUtility.setGameModeAndLevel(null, null);
    }
    
    /**
     * Test method for {@link edu.nyu.cs.connectfour.game.subject.impl.GameUtility#setGameModeAndLevel(edu.nyu.cs.connectfour.game.utils.GameMode, edu.nyu.cs.connectfour.game.utils.ComputerLevel)}.
     */
    @Test(expected = NullPointerException.class)
    public void testSetGameModeAndLevelWithModeNullValue() {
        gameUtility.setGameModeAndLevel(null, ComputerLevel.ABNORMAL);
    }
    
    /**
     * Test method for {@link edu.nyu.cs.connectfour.game.subject.impl.GameUtility#setGameModeAndLevel(edu.nyu.cs.connectfour.game.utils.GameMode, edu.nyu.cs.connectfour.game.utils.ComputerLevel)}.
     */
    @Test(expected = NullPointerException.class)
    public void testSetGameModeAndLevelWithLevelNullValue() {
        gameUtility.setGameModeAndLevel(GameMode.HUMAN_VS_HUMAN, null);
    }

    /**
     * Test method for {@link edu.nyu.cs.connectfour.game.subject.impl.GameUtility#setGameModeAndLevel(edu.nyu.cs.connectfour.game.utils.GameMode, edu.nyu.cs.connectfour.game.utils.ComputerLevel)}.
     */
    @Test
    public void testSetGameModeAndLevelWithNoNullValue() {
        for (int i = 0; i < GameMode.values().length; i++) {
            for (int j = 0; j < ComputerLevel.values().length; j++) {
                gameUtility.setGameModeAndLevel(
                        GameMode.values()[i], ComputerLevel.values()[j]);
                assertEquals(GameMode.values()[i], gameUtility.getGameMode());
                assertEquals(ComputerLevel.values()[j], gameUtility.getComputerLevel());
            }
        }
    }
    
    /**
     * Test method for {@link edu.nyu.cs.connectfour.game.subject.impl.GameUtility#setOffensive(edu.nyu.cs.connectfour.player.subject.impl.PlayerInfo)}.
     */
    @Test(expected = NullPointerException.class)
    public void testSetOffensiveWithNullValue() {
        gameUtility.setOffensive(null);
    }

    /**
     * Test method for {@link edu.nyu.cs.connectfour.game.subject.impl.GameUtility#setOffensive(edu.nyu.cs.connectfour.player.subject.impl.PlayerInfo)}.
     */
    @Test
    public void testSetOffensiveWithNoNullValue() {
        for (int i = 0; i < PlayerInfo.values().length; i++) {
            gameUtility.setOffensive(PlayerInfo.values()[i]);
            assertEquals(PlayerInfo.values()[i], gameUtility.getOffensive());
            assertEquals(PlayerInfo.values()[i], gameUtility.getPlayerTurn());
        }
    }
    
    /**
     * Test method for {@link edu.nyu.cs.connectfour.game.subject.impl.GameUtility#setPlayerTurn(edu.nyu.cs.connectfour.player.subject.impl.PlayerInfo)}.
     */
    @Test(expected = NullPointerException.class)
    public void testSetPlayerTurnWithNullValue() {
        gameUtility.setPlayerTurn(null);
    }
    
    /**
     * Test method for {@link edu.nyu.cs.connectfour.game.subject.impl.GameUtility#setPlayerTurn(edu.nyu.cs.connectfour.player.subject.impl.PlayerInfo)}.
     */
    @Test
    public void testSetPlayerTurnWithNoNullValue() {
        for (int i = 0; i < PlayerInfo.values().length; i++) {
            gameUtility.setPlayerTurn(PlayerInfo.values()[i]);
            assertEquals(PlayerInfo.values()[i], gameUtility.getPlayerTurn());
        }
    }

    /**
     * Test method for {@link edu.nyu.cs.connectfour.game.subject.impl.GameUtility#setGameStatus(boolean, boolean)}.
     */
    @Test
    public void testSetGameStatus() {
        boolean[] status = {true, false};
        for (boolean b1 : status) {
            for (boolean b2 : status) {
                gameUtility.setGameStatus(b1, b2);
                assertEquals(b1, gameUtility.isPlaying());
                assertEquals(b2, gameUtility.isGameOver());
            }
        }
    }
    
    /**
     * Test method for {@link edu.nyu.cs.connectfour.game.subject.impl.GameUtility#registerGameStatusObserver(edu.nyu.cs.connectfour.game.observer.GameStatusObserver)}.
     */
    @Test(expected = NullPointerException.class)
    public void testRegisterGameStatusObserverWithNullValue() {
        gameUtility.registerGameStatusObserver(null);
    }
    
    /**
     * Test method for {@link edu.nyu.cs.connectfour.game.subject.impl.GameUtility#removeGameStatusObserver(edu.nyu.cs.connectfour.game.observer.GameStatusObserver)}.
     */
    @Test
    public void testRemoveGameStatusObserverWithNullValue() {
        gameUtility.removeGameStatusObserver(null);
    }
    
    /**
     * Test method for {@link edu.nyu.cs.connectfour.game.subject.impl.GameUtility#removeGameStatusObserver(edu.nyu.cs.connectfour.game.observer.GameStatusObserver)}.
     */
    @Test
    public void testRemoveGameStatusObserverWithExistValue() {
        GameStatusObserver mockGameStatusObserver = EasyMock.createMock(GameStatusObserver.class);
        gameUtility.registerGameStatusObserver(mockGameStatusObserver);
        gameUtility.removeGameStatusObserver(mockGameStatusObserver);
        EasyMock.replay(mockGameStatusObserver);
        gameUtility.setGameStatus(true, false);
        EasyMock.verify(mockGameStatusObserver);
    }
    
    /**
     * Test method for {@link edu.nyu.cs.connectfour.game.subject.impl.GameUtility#notifyGameStatusObservers()}.
     */
    @Test
    public void testNotifyGameStatusObservers() {
        GameStatusObserver mockGameStatusObserver = EasyMock.createMock(GameStatusObserver.class);
        gameUtility.registerGameStatusObserver(mockGameStatusObserver);
        
        mockGameStatusObserver.updateGameModeAndLevel(GameMode.HUMAN_VS_COMPUTER, ComputerLevel.REGULAR);
        mockGameStatusObserver.updateGameTurn(PlayerInfo.PLAYER_ONE);
        mockGameStatusObserver.updateGameStatus(true, false);
        
        mockGameStatusObserver.updateGameModeAndLevel(GameMode.HUMAN_VS_COMPUTER, ComputerLevel.REGULAR);
        mockGameStatusObserver.updateGameTurn(PlayerInfo.PLAYER_TWO);
        mockGameStatusObserver.updateGameStatus(true, false);
        
        mockGameStatusObserver.updateGameModeAndLevel(GameMode.HUMAN_VS_HUMAN, ComputerLevel.ABNORMAL);
        mockGameStatusObserver.updateGameTurn(PlayerInfo.PLAYER_TWO);
        mockGameStatusObserver.updateGameStatus(true, false);
        
        EasyMock.replay(mockGameStatusObserver);
        gameUtility.setGameStatus(true, false);
        gameUtility.setPlayerTurn(PlayerInfo.PLAYER_TWO);
        gameUtility.setGameModeAndLevel(GameMode.HUMAN_VS_HUMAN, ComputerLevel.ABNORMAL);
        EasyMock.verify(mockGameStatusObserver);
    }

    /**
     * Test method for {@link edu.nyu.cs.connectfour.game.subject.impl.GameUtility#toString()}.
     */
    @Test
    public void testToString() {
        gameUtility.setGameModeAndLevel(GameMode.HUMAN_VS_COMPUTER, ComputerLevel.REGULAR);
        gameUtility.setOffensive(PlayerInfo.PLAYER_ONE);
        gameUtility.setGameStatus(false, true);
        assertEquals(
                "against computer/REGULAR/Me/-65536/Me/-65536/false/true",
                gameUtility.toString());
    }

}
