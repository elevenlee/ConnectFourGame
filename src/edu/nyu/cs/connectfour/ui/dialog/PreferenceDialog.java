package edu.nyu.cs.connectfour.ui.dialog;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import edu.nyu.cs.connectfour.container.GetContainerable;
import edu.nyu.cs.connectfour.game.subject.impl.GameUtility;
import edu.nyu.cs.connectfour.game.utils.ComputerLevel;
import edu.nyu.cs.connectfour.game.utils.GameMode;
import edu.nyu.cs.connectfour.player.subject.impl.PlayerInfo;
import edu.nyu.cs.connectfour.ui.label.ResultLabel;
import edu.nyu.cs.connectfour.ui.panel.ChessPanel;

/**
 * @author shenli
 * <p>
 * A {@code PreferenceDialog} object is used to create an "Preference dialog" window.
 * <p>
 * The singleton {@code PreferenceDialog} object contains a {@link javax.swing.JDialog} component. 
 * {@code PreferenceDialog} object may be obtains by calls on {@link edu.nyu.cs.connectfour.ui.factory.DialogFactory#getDialog(edu.nyu.cs.connectfour.game.ui.type.DialogType)} 
 * factory methods. These will return the singleton {@code PreferenceDialog}.
 * <p>
 * <b>Warning:</b> Swing is not thread safe.
 */
public enum PreferenceDialog implements GetContainerable<JDialog> {
    /**
     * The singleton instance presents preference dialog swing.
     */
    INSTANCE("Preference", 480, 420);
    
    private final JDialog preferenceDialog;
    private final List<JPanel> playerPanels;
    private final JPanel playModePanel;
    private final JPanel computerLevelPanel;
    private final List<JTextField> playerNameTexts;
    private final List<JButton> playerColorButtons;
    private final List<JRadioButton> playerFirstButtons;
    private final List<JRadioButton> playModeButtons;
    private final List<JRadioButton> computerLevelButtons;
    private final JButton confirmButton;
    private final JButton cancelButton;
    
    /**
     * Creates an "Preference dialog" with the specified title, width and height.
     * <p>
     * @param title the display in the Preference dialog's title bar
     * @param width the width of this component in pixels
     * @param height the height of this component in pixels
     */
    private PreferenceDialog(String title, int width, int height) {
        assert title != null;
        assert width > 0;
        assert height > 0;
        
        preferenceDialog = new JDialog();
        preferenceDialog.setTitle(title);
        preferenceDialog.setModal(true);
        preferenceDialog.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 15));
        
        playerPanels = new ArrayList<JPanel>();
        playModePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        computerLevelPanel = new JPanel(new GridLayout(ComputerLevel.values().length, 1));
        playerNameTexts = new ArrayList<JTextField>();
        playerColorButtons = new ArrayList<JButton>();
        playerFirstButtons = new ArrayList<JRadioButton>();
        playModeButtons = new ArrayList<JRadioButton>();
        computerLevelButtons = new ArrayList<JRadioButton>();
        confirmButton = new JButton("Confirm");
        cancelButton = new JButton("Cancel");
        
        ButtonGroup bgFirst = new ButtonGroup();
        for (PlayerInfo pi : PlayerInfo.values()) {
            JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
            JLabel nl = new JLabel("Name", SwingConstants.CENTER);
            JLabel cl = new JLabel("Color", SwingConstants.CENTER);
            nl.setPreferredSize(new Dimension(70, 40));
            JTextField ntf = new JTextField(pi.getName());
            ntf.setPreferredSize(new Dimension(120, 40));
            cl.setPreferredSize(new Dimension(70, 40));
            JButton cb = new JButton();
            cb.setPreferredSize(new Dimension(40, 40));
            cb.setBackground(pi.getColor());
            JRadioButton fb = new JRadioButton("Offensive", true);
            
            bgFirst.add(fb);
            p.add(nl);
            p.add(ntf);
            p.add(cl);
            p.add(cb);
            p.add(fb);
            p.setBorder(
                    new TitledBorder(
                            GameUtility.INSTANCE.getGameMode().getLabels().get(pi.ordinal())));
            preferenceDialog.add(p);
            
            playerPanels.add(p);
            playerNameTexts.add(ntf);
            playerColorButtons.add(cb);
            playerFirstButtons.add(fb);
            cb.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int i = playerColorButtons.indexOf((JButton) e.getSource());
                    PlayerInfo player = PlayerInfo.values()[i];
                    JColorChooser colorChooser = new JColorChooser(player.getColor());
                    Color newColor = 
                            JColorChooser.showDialog(colorChooser,
                                    player.getName() + " Color", player.getColor());
                    if (newColor != null) {
                        ((JButton) e.getSource()).setBackground(newColor);
                    }
                }
            });
        }
        
        ButtonGroup bgMode = new ButtonGroup();
        for (GameMode pm : GameMode.values()) {
            JRadioButton mrb = new JRadioButton(pm.toString(), true);
            mrb.setPreferredSize(new Dimension(160, 30));
            bgMode.add(mrb);
            playModePanel.add(mrb);
            playModeButtons.add(mrb);
            mrb.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int i = playModeButtons.indexOf((JRadioButton) e.getSource());
                    for (int pos = 0; pos < playerPanels.size(); pos++) {
                        playerPanels.get(pos)
                                .setBorder(new TitledBorder(
                                        GameMode.values()[i].getLabels().get(pos)));
                    }
                }
            });
        }
        playModePanel.setBorder(new TitledBorder("Play Mode"));
        
        ButtonGroup bgLevel = new ButtonGroup();
        for (ComputerLevel cl : ComputerLevel.values()) {
            JRadioButton crb = new JRadioButton(cl.toString(), false);
            bgLevel.add(crb);
            computerLevelPanel.add(crb);
            computerLevelButtons.add(crb);
        }
        computerLevelButtons.get(2).setSelected(true);
        computerLevelPanel.setBorder(new TitledBorder("Level"));
        
        confirmButton.setPreferredSize(new Dimension(100, 30));
        cancelButton.setPreferredSize(new Dimension(100, 30));
        
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameMode pm = null;
                for (int i = 0; i < playModeButtons.size(); i++) {
                    if (playModeButtons.get(i).isSelected()) {
                        pm = GameMode.values()[i];
                        break;
                    }
                }
                ComputerLevel cl = null;
                for (int i = 0; i < computerLevelButtons.size(); i++) {
                    if (computerLevelButtons.get(i).isSelected()) {
                        cl = ComputerLevel.values()[i];
                        break;
                    }
                }
                GameUtility.INSTANCE.setGameModeAndLevel(pm, cl);
                PlayerInfo offensive = null;
                for (int i = 0; i < playerFirstButtons.size(); i++) {
                    if (playerFirstButtons.get(i).isSelected()) {
                        offensive = PlayerInfo.values()[i];
                        break;
                    }
                }
                GameUtility.INSTANCE.setOffensive(offensive);
                for (PlayerInfo pi : PlayerInfo.values()) {
                    int i = pi.ordinal();
                    pi.setAll(playerNameTexts.get(i).getText(),
                            playerColorButtons.get(i).getBackground());
                }
                GameUtility.INSTANCE.setGameStatus(false, false);
                ChessPanel.INSTANCE.resignChessPanel();
                ResultLabel.INSTANCE.getContainer().setText("");
                preferenceDialog.dispose();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameMode pm = GameUtility.INSTANCE.getGameMode();
                playModeButtons.get(pm.ordinal()).setSelected(true);
                PlayerInfo offensive = GameUtility.INSTANCE.getOffensive();
                playerFirstButtons.get(offensive.ordinal()).setSelected(true);
                for (PlayerInfo pi : PlayerInfo.values()) {
                    int i = pi.ordinal();
                    playerPanels.get(i).setBorder(new TitledBorder(pm.getLabels().get(i)));
                    playerNameTexts.get(i).setText(pi.getName());
                    playerColorButtons.get(i).setBackground(pi.getColor());
                }
                preferenceDialog.dispose();
            }
        });
        preferenceDialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                GameMode pm = GameUtility.INSTANCE.getGameMode();
                playModeButtons.get(pm.ordinal()).setSelected(true);
                PlayerInfo offensive = GameUtility.INSTANCE.getOffensive();
                playerFirstButtons.get(offensive.ordinal()).setSelected(true);
                for (PlayerInfo pi : PlayerInfo.values()) {
                    int i = pi.ordinal();
                    playerPanels.get(i).setBorder(new TitledBorder(pm.getLabels().get(i)));
                    playerNameTexts.get(i).setText(pi.getName());
                    playerColorButtons.get(i).setBackground(pi.getColor());
                }
                preferenceDialog.dispose();
            }
        });
        
        preferenceDialog.add(playModePanel);
        preferenceDialog.add(computerLevelPanel);
        preferenceDialog.add(confirmButton);
        preferenceDialog.add(cancelButton);
        
        preferenceDialog.getRootPane().setDefaultButton(confirmButton);
        preferenceDialog.setSize(width, height);
        preferenceDialog.setResizable(false);
        preferenceDialog.setLocationRelativeTo(null);   // Set dialog center showing
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public JDialog getContainer() {
        return preferenceDialog;
    }
    
}
