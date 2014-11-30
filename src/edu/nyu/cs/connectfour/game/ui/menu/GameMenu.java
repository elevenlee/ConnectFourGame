package edu.nyu.cs.connectfour.game.ui.menu;

import java.awt.event.KeyEvent;

import javax.swing.JMenu;

import edu.nyu.cs.connectfour.container.GetContainerable;
import edu.nyu.cs.connectfour.game.observer.GameStatusObserver;
import edu.nyu.cs.connectfour.game.subject.GameStatusSubject;
import edu.nyu.cs.connectfour.game.subject.impl.GameUtility;
import edu.nyu.cs.connectfour.game.utils.ComputerLevel;
import edu.nyu.cs.connectfour.game.utils.GameMode;
import edu.nyu.cs.connectfour.player.subject.impl.PlayerInfo;

/**
 * @author shenli
 * <p>
 * The {@code GameMenu} enum used to provide menus for singleton {@link edu.nyu.cs.connectfour.game.ui.menu.GameMenuBar} object.
 */
enum GameMenu implements GetContainerable<JMenu>, GameStatusObserver {
    GAME("Game", KeyEvent.VK_G),
    HELP("Help", KeyEvent.VK_H);
    
    private final GameStatusSubject gameStatus;
    private final JMenu menu;
    private final String menuName;
    private final int menuKey;
    
    /**
     * Create a menu with the specific name and key.
     * <p>
     * @param menuName the name of this menu
     * @param menuKey the key of this menu
     */
    private GameMenu(String menuName, int menuKey) {
        assert menuName != null;
        
        this.gameStatus = GameUtility.INSTANCE;
        this.menuName = menuName;
        this.menuKey = menuKey;
        this.menu = new JMenu(this.menuName);
        this.menu.setMnemonic(this.menuKey);
        this.gameStatus.registerGameStatusObserver(this);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public JMenu getContainer() {
        return menu;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateGameModeAndLevel(GameMode mode, ComputerLevel level) {
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateGameTurn(PlayerInfo turn) {
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateGameStatus(boolean playing, boolean gameOver) {
        GameMenuItem.START.getContainer().setEnabled(!playing && !gameOver);
        GameMenuItem.PAUSE.getContainer().setEnabled(playing && !gameOver);
    }
    
    /**
     * Return string representation of this {@code GameMenu} object. The string representation consists of the 
     * menu name.
     * <p>
     * @return string representation of this {@code GameMenu} object
     */
    @Override
    public String toString() {
        return menuName;
    }

}
