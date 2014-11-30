package edu.nyu.cs.connectfour.game.ui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import edu.nyu.cs.connectfour.container.GetContainerable;
import edu.nyu.cs.connectfour.game.subject.impl.GameUtility;
import edu.nyu.cs.connectfour.game.ui.factory.DialogFactory;
import edu.nyu.cs.connectfour.game.ui.label.ResultLabel;
import edu.nyu.cs.connectfour.game.ui.panel.ChessPanel;
import edu.nyu.cs.connectfour.game.ui.type.DialogType;

/**
 * @author shenli
 * <p>
 * The {@code GameMenuItem} enum used to provide menu items for this object, as well as implementing 
 * {@link java.awt.event.ActionListener} interface for each menu item.
 */
enum GameMenuItem implements GetContainerable<JMenuItem> {
    START(GameMenu.GAME, "Start", KeyEvent.VK_S) {
        
        @Override
        void addMenuItemAction() {
            this.getContainer().addActionListener(new ActionListener() {
                
                @Override
                public void actionPerformed(ActionEvent e) {
                    GameUtility.INSTANCE.setGameStatus(true, false);
                    ChessPanel.INSTANCE.getGameRecord().nextPlay();
                }
            });
        }
    },
    PAUSE(GameMenu.GAME, "Pause", KeyEvent.VK_U) {
        
        @Override
        void addMenuItemAction() {
            this.getContainer().addActionListener(new ActionListener() {
                
                @Override
                public void actionPerformed(ActionEvent e) {
                    GameUtility.INSTANCE.setGameStatus(false, false);
                }
            });
        }
    },
    RESIGN(GameMenu.GAME, "Resign", KeyEvent.VK_R) {
        
        @Override
        void addMenuItemAction() {
            this.getContainer().addActionListener(new ActionListener() {
                
                @Override
                public void actionPerformed(ActionEvent e) {
                    int result = JOptionPane.showConfirmDialog(
                            null,
                            "Are you sure to resign game?",
                            "resign",
                            JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        GameUtility.INSTANCE.setGameStatus(false, false);
                        GameUtility.INSTANCE.setPlayerTurn(
                                GameUtility.INSTANCE.getOffensive());
                        ChessPanel.INSTANCE.resignChessPanel();
                        ResultLabel.INSTANCE.getContainer().setText("");
                    }
                }
            });
        }
    },
    PREFERENCE(GameMenu.GAME, "Preference", KeyEvent.VK_P) {
        
        @Override
        void addMenuItemAction() {
            this.getContainer().addActionListener(new ActionListener() {
                
                @Override
                public void actionPerformed(ActionEvent e) {
                    DialogFactory.getDialog(DialogType.PREFERENCE)
                            .getContainer().setVisible(true);
                }
            });
        }
    },
    QUIT(GameMenu.GAME, "Quit", KeyEvent.VK_Q) {
        
        @Override
        void addMenuItemAction() {
            this.getContainer().addActionListener(new ActionListener() {
                
                @Override
                public void actionPerformed(ActionEvent e) {
                    int result = JOptionPane.showConfirmDialog(
                            null,
                            "Are you sure to quit game?",
                            "quit",
                            JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        System.exit(0);
                    }
                }
            });
        }
    },
    VIEW_HELP(GameMenu.HELP, "View Help", KeyEvent.VK_V) {
        
        @Override
        void addMenuItemAction() {
            this.getContainer().addActionListener(new ActionListener() {
                
                @Override
                public void actionPerformed(ActionEvent e) {
                    DialogFactory.getDialog(DialogType.HELP)
                            .getContainer().setVisible(true);
                }
            });
        }
    },
    ABOUT(GameMenu.HELP, "About", KeyEvent.VK_A) {
        
        @Override
        void addMenuItemAction() {
            this.getContainer().addActionListener(new ActionListener() {
                
                @Override
                public void actionPerformed(ActionEvent e) {
                    DialogFactory.getDialog(DialogType.ABOUT)
                            .getContainer().setVisible(true);
                }
            });
        }
    };
    
    private final JMenuItem menuItem;
    private final GameMenu category;
    private final String itemName;
    private final int itemKey;
    
    /**
     * Create a menu item with the specific menu category, name, and key.
     * <p>
     * @param category the specific menu category
     * @param itemName the name of this menu item
     * @param itemKey the key of this menu item
     */
    private GameMenuItem(GameMenu category, String itemName, int itemKey) {
        assert category != null;
        assert itemName != null;
        
        this.category = category;
        this.itemName = itemName;
        this.itemKey = itemKey;
        this.menuItem = new JMenuItem(this.itemName, this.itemKey);
    }
    
    /**
     * Returns menu category.
     * <p>
     * @return menu category
     */
    GameMenu getCategory() {
        return category;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JMenuItem getContainer() {
        return menuItem;
    }

    /**
     * Return string representation of this {@code GameMenuItem} object. The string representation consists 
     * of the item name.
     * <p>
     * @return string representation of this {@code GameMenuItem} object
     */
    @Override
    public String toString() {
        return itemName;
    }
    
    /**
     * Add specific action for each menu item.
     */
    abstract void addMenuItemAction();
    
}
