package edu.nyu.cs.connectfour.game.subject.impl;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import edu.nyu.cs.connectfour.game.observer.GameRecordObserver;
import edu.nyu.cs.connectfour.game.observer.GameResultObserver;
import edu.nyu.cs.connectfour.player.subject.impl.PlayerInfo;

public class GameRecordTest {
    private GameRecord gameRecord;
    private GameRecordObserver mockGameRecordObserver;
    private GameResultObserver mockGameResultObserver;
    
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        gameRecord = new GameRecord(6, 7);
        mockGameRecordObserver = EasyMock.createMock(GameRecordObserver.class);
        mockGameResultObserver = EasyMock.createMock(GameResultObserver.class);
    }

    /**
     * Test method for {@link edu.nyu.cs.connectfour.game.subject.impl.GameRecord#GameRecord(int, int)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGameRecordWithTwoNegativeValues() {
        new GameRecord(-6, -8);
    }
    
    /**
     * Test method for {@link edu.nyu.cs.connectfour.game.subject.impl.GameRecord#GameRecord(int, int)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGameRecordWithOneNegativeValue() {
        new GameRecord(-6, 8);
    }
    
    /**
     * Test method for {@link edu.nyu.cs.connectfour.game.subject.impl.GameRecord#GameRecord(int, int)}.
     */
    @Test
    public void testGameRecordWithPositiveValue() {
        new GameRecord(6, 8);
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
     * Test method for {@link edu.nyu.cs.connectfour.game.subject.impl.GameRecord#registerGameRecordObserver(edu.nyu.cs.connectfour.game.observer.GameRecordObserver)}.
     */
    @Test(expected = NullPointerException.class)
    public void testRegisterGameRecordObserverWithNullValue() {
        gameRecord.registerGameRecordObserver(null);
    }
    
    /**
     * Test method for {@link edu.nyu.cs.connectfour.game.subject.impl.GameRecord#registerGameRecordObserver(edu.nyu.cs.connectfour.game.observer.GameRecordObserver)}.
     */
    @Test
    public void testRegisterGameRecordObserverWithNoNullValue() {
        gameRecord.registerGameRecordObserver(mockGameRecordObserver);
        mockGameRecordObserver.updateGameRecord(PlayerInfo.PLAYER_ONE, 5, 3);
        EasyMock.replay(mockGameRecordObserver);
        gameRecord.place(3);
        EasyMock.verify(mockGameRecordObserver);
    }
    
    /**
     * Test method for {@link edu.nyu.cs.connectfour.game.subject.impl.GameRecord#removeGameRecordObserver(edu.nyu.cs.connectfour.game.observer.GameRecordObserver)}.
     */
    @Test
    public void testRemoveGameRecordObserverWithNullValue() {
        gameRecord.removeGameRecordObserver(null);
    }
    
    /**
     * Test method for {@link edu.nyu.cs.connectfour.game.subject.impl.GameRecord#removeGameRecordObserver(edu.nyu.cs.connectfour.game.observer.GameRecordObserver)}.
     */
    @Test
    public void testRemoveGameRecordObserverWithNotExistValue() {
        GameRecordObserver mockGameRecordObserverDeleted = EasyMock.createMock(GameRecordObserver.class);
        gameRecord.registerGameRecordObserver(mockGameRecordObserver);
        gameRecord.removeGameRecordObserver(mockGameRecordObserverDeleted);
        mockGameRecordObserver.updateGameRecord(PlayerInfo.PLAYER_ONE, 5, 3);
        EasyMock.replay(mockGameRecordObserver);
        gameRecord.place(3);
        EasyMock.verify(mockGameRecordObserver);
    }
    
    /**
     * Test method for {@link edu.nyu.cs.connectfour.game.subject.impl.GameRecord#removeGameRecordObserver(edu.nyu.cs.connectfour.game.observer.GameRecordObserver)}.
     */
    @Test
    public void testRemoveGameRecordObserverWithExistValue() {
        gameRecord.registerGameRecordObserver(mockGameRecordObserver);
        gameRecord.removeGameRecordObserver(mockGameRecordObserver);
        EasyMock.replay(mockGameRecordObserver);
        gameRecord.place(3);
        EasyMock.verify(mockGameRecordObserver);
    }
    
    /**
     * Test method for {@link edu.nyu.cs.connectfour.game.subject.impl.GameRecord#notifyGameRecordObservers(int)}.
     */
    @Test
    public void testNotifyGameRecordObservers() {
        gameRecord.registerGameRecordObserver(mockGameRecordObserver);
        mockGameRecordObserver.updateGameRecord(PlayerInfo.PLAYER_ONE, 5, 3);
        EasyMock.replay(mockGameRecordObserver);
        gameRecord.place(3);
        EasyMock.verify(mockGameRecordObserver);
    }
    
    /**
     * Test method for {@link edu.nyu.cs.connectfour.game.subject.impl.GameRecord#registerGameResultObserver(edu.nyu.cs.connectfour.game.observer.GameResultObserver)}.
     */
    @Test(expected = NullPointerException.class)
    public void testRegisterGameResultObserverWithNullValue() {
        gameRecord.registerGameResultObserver(null);
    }
    
    /**
     * Test method for {@link edu.nyu.cs.connectfour.game.subject.impl.GameRecord#removeGameResultObserver(edu.nyu.cs.connectfour.game.observer.GameResultObserver)}.
     */
    @Test
    public void testRemoveGameResultObserverWithNullValue() {
        gameRecord.removeGameResultObserver(null);
    }
    
    /**
     * Test method for {@link edu.nyu.cs.connectfour.game.subject.impl.GameRecord#removeGameResultObserver(edu.nyu.cs.connectfour.game.observer.GameResultObserver)}.
     */
    @Test
    public void testRemoveGameResultObserverWithNoNullValue() {
        gameRecord.registerGameResultObserver(mockGameResultObserver);
        gameRecord.removeGameResultObserver(mockGameResultObserver);
        EasyMock.replay(mockGameResultObserver);
        gameRecord.place(5);
        EasyMock.verify(mockGameResultObserver);
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
