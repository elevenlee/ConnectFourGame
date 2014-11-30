package edu.nyu.cs.connectfour.game.ui.panel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import edu.nyu.cs.connectfour.container.GetContainerable;
import edu.nyu.cs.connectfour.game.observer.GameStatusObserver;
import edu.nyu.cs.connectfour.game.subject.GameStatusSubject;
import edu.nyu.cs.connectfour.game.subject.impl.GameUtility;
import edu.nyu.cs.connectfour.game.ui.factory.DialogFactory;
import edu.nyu.cs.connectfour.game.ui.label.ResultLabel;
import edu.nyu.cs.connectfour.game.ui.type.DialogType;
import edu.nyu.cs.connectfour.game.utils.ComputerLevel;
import edu.nyu.cs.connectfour.game.utils.GameMode;
import edu.nyu.cs.connectfour.player.subject.impl.PlayerInfo;

/**
 * @author shenli
 * <p>
 * A {@code ContorlPanel} object is used to create an "Control" panel.
 * <p>
 * The singleton {@code ContorlPanel} object contains a {@link javax.swing.JPanel} component. {@code ContorlPanel}
 * object may be obtains by calls on {@link edu.nyu.cs.connectfour.game.ui.factory.PanelFactory#getPanel(edu.nyu.cs.connectfour.game.ui.type.PanelType)} 
 * factory methods. These will return the singleton {@code ContorlPanel}.
 * <p>
 * <b>Warning:</b> Swing is not thread safe.
 */
public enum ControlPanel implements GetContainerable<JPanel>, GameStatusObserver {
    INSTANCE(800, 120);

    private final GameStatusSubject gameStatus;
    private final JPanel controlPanel;
    
    /**
     * Inner enum class of {@code ControlPanel} used to provide control button type to support for control 
     * panel actions. This class is not meant to be used directly by application developers.
     * <p>
     * The enum class used to provide control button for this singleton object, as well as implementing 
     * {@link java.awt.event.ActionListener} interface for each button.
     */
    private enum ControlButton implements GetContainerable<JButton> {
        START("res/start.png", "Start Game") {
            
            @Override
            void addButtonAction() {
                this.getContainer().addActionListener(new ActionListener() {
                    
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        GameUtility.INSTANCE.setGameStatus(true, false);
                        ChessPanel.INSTANCE.getGameRecord().nextPlay();
                    }
                });
            }
        },
        PAUSE("res/pause.png", "Pause Game") {
            
            @Override
            void addButtonAction() {
                this.getContainer().addActionListener(new ActionListener() {
                    
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        GameUtility.INSTANCE.setGameStatus(false, false);
                    }
                });
            }
        },
        RESIGN("res/resign.png", "Resign") {
            
            @Override
            void addButtonAction() {
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
        PREFERENCE("res/preference.png", "Preference") {
            
            @Override
            void addButtonAction() {
                this.getContainer().addActionListener(new ActionListener() {
                    
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        DialogFactory.getDialog(DialogType.PREFERENCE)
                                .getContainer().setVisible(true);
                    }
                });
            }
        },
        VIEW_HELP("res/viewhelp.png", "View Help") {
            
            @Override
            void addButtonAction() {
                this.getContainer().addActionListener(new ActionListener() {
                    
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        DialogFactory.getDialog(DialogType.HELP)
                                .getContainer().setVisible(true);
                    }
                });
            }
        };

        private final JButton button;
        private final String iconPath;
        private final String tooltipText;
        
        /**
         * Creates a control button with the specified button icon path and tool tip text.
         * <p>
         * @param iconPath the icon path of this button
         * @param tooltipText the tool tip text of this button
         */
        private ControlButton(String iconPath, String tooltipText) {
            assert iconPath != null;
            assert tooltipText != null;
            
            this.iconPath = iconPath;
            this.tooltipText = tooltipText;
            this.button = new JButton(new ImageIcon(this.iconPath, "Button Symbol"));
            this.button.setToolTipText(this.tooltipText);
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public JButton getContainer() {
            return button;
        }
        
        /**
         * Add specific action for each control button.
         */
        abstract void addButtonAction();

    }
    
    /**
     * Creates a "Control" panel with the specified width and height.
     * <p>
     * @param width the width of this component in pixels
     * @param height the height of this component in pixels
     */
    private ControlPanel(int width, int height) {
        assert width > 0;
        assert height > 0;
        
        this.gameStatus = GameUtility.INSTANCE;
        this.controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 20));
        this.controlPanel.setPreferredSize(new Dimension(width, height));
        for (ControlButton cb : ControlButton.values()) {
            cb.addButtonAction();
            this.controlPanel.add(cb.button);
        }
        this.gameStatus.registerGameStatusObserver(this);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel getContainer() {
        return controlPanel;
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
        ControlButton.START.button.setEnabled(!playing && !gameOver);
        ControlButton.PAUSE.button.setEnabled(playing && !gameOver);
    }

}
