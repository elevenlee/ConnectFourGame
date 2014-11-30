package edu.nyu.cs.connectfour.ui.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import edu.nyu.cs.connectfour.container.GetContainerable;
import edu.nyu.cs.connectfour.game.observer.GameStatusObserver;
import edu.nyu.cs.connectfour.game.subject.GameStatusSubject;
import edu.nyu.cs.connectfour.game.subject.impl.GameUtility;
import edu.nyu.cs.connectfour.game.utils.ComputerLevel;
import edu.nyu.cs.connectfour.game.utils.GameMode;
import edu.nyu.cs.connectfour.player.observer.PlayerInfoObserver;
import edu.nyu.cs.connectfour.player.subject.PlayerInfoSubject;
import edu.nyu.cs.connectfour.player.subject.impl.PlayerInfo;
import edu.nyu.cs.connectfour.utils.ParameterChecker;

/**
 * @author shenli
 * <p>
 * An {@code PlayerPanel} object is used to create an "Player information" panel.
 * <p>
 * The singleton {@code PlayerPanel} object contains a {@link javax.swing.JPanel} component. {@code PlayerPanel}
 * object may be obtains by calls on {@link edu.nyu.cs.connectfour.ui.factory.PanelFactory#getPanel(edu.nyu.cs.connectfour.game.ui.type.PanelType)} 
 * factory methods. These will return the singleton {@code PlayerPanel}.
 * <p>
 * <b>Warning:</b> Swing is not thread safe.
 */
public enum PlayerPanel implements GetContainerable<JPanel>, PlayerInfoObserver, GameStatusObserver {
    INSTANCE(800, 80);

    private static final String VS_ICON_PATH = "res/vs.png";
    
    private final List<PlayerInfoSubject> playerInfoSubject;
    private final GameStatusSubject gameStatus;
    private final JPanel playerPanel;
    private final List<JPanel> infoPanels;
    private final List<JRadioButton> playingButtons;
    private final List<JLabel> iconLabels;
    private final List<JLabel> nameLabels;
    private final List<JButton> colorButtons;
    
    /**
     * Creates an "Player information" panel with the specified width and height.
     * <p>
     * @param width the width of this component in pixels
     * @param height the height of this component in pixels
     */
    private PlayerPanel(int width, int height) {
        assert width > 0;
        assert height > 0;
        
        this.playerInfoSubject = new ArrayList<>();
        this.gameStatus = GameUtility.INSTANCE;
        this.playerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 0));
        this.infoPanels = new ArrayList<>();
        this.playingButtons = new ArrayList<>();
        this.iconLabels = new ArrayList<>();
        this.nameLabels = new ArrayList<>();
        this.colorButtons = new ArrayList<>();
        ButtonGroup bg = new ButtonGroup();
        
        this.playerPanel.setPreferredSize(new Dimension(width, height));
        for (PlayerInfo pi : PlayerInfo.values()) {
            JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
            JRadioButton pb = new JRadioButton();
            JLabel il = new JLabel(GameMode.HUMAN_VS_COMPUTER.getIcons().get(pi.ordinal()));
            JLabel nl = new JLabel(pi.getName());
            JButton cb = new JButton();
            
            pb.setPreferredSize(new Dimension(20, 20));
            pb.setSelected(true);
            pb.setEnabled(false);
            cb.setPreferredSize(new Dimension(30, 30));
            cb.setBackground(pi.getColor());
            cb.setEnabled(false);
            
            bg.add(pb);
            p.add(pb);
            p.add(il);
            p.add(nl);
            p.add(cb);
            playerPanel.add(p);
            
            infoPanels.add(p);
            playingButtons.add(pb);
            iconLabels.add(il);
            nameLabels.add(nl);
            colorButtons.add(cb);
            
            if (pi.ordinal() != PlayerInfo.values().length - 1) {
                playerPanel.add(new JLabel(new ImageIcon(VS_ICON_PATH, "VS Symbol")));
            }
            playerInfoSubject.add(pi);
            pi.registerPlayerInfoObserver(this);
        }
        this.gameStatus.registerGameStatusObserver(this);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel getContainer() {
        return playerPanel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updatePlayerInfo(PlayerInfo pi, String name, Color color) {
        ParameterChecker.nullCheck(pi, "player information");
        ParameterChecker.nullCheck(name, "player name");
        ParameterChecker.nullCheck(color, "player color");
        
        int i = pi.ordinal();
        nameLabels.get(i).setText(name);
        colorButtons.get(i).setBackground(color);
        playingButtons.get(GameUtility.INSTANCE.getOffensive().ordinal()).setSelected(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateGameModeAndLevel(GameMode mode, ComputerLevel level) {
        ParameterChecker.nullCheck(mode, "game mode");
        ParameterChecker.nullCheck(level, "computer level");
        
        for (int i = 0; i < iconLabels.size(); i++) {
            iconLabels.get(i).setIcon(mode.getIcons().get(i));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateGameTurn(PlayerInfo turn) {
        ParameterChecker.nullCheck(turn, "player turn");
        
        playingButtons.get(turn.ordinal()).setSelected(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateGameStatus(boolean playing, boolean gameOver) {
        
    }
    
}
