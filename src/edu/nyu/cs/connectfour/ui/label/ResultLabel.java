package edu.nyu.cs.connectfour.ui.label;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import edu.nyu.cs.connectfour.container.GetContainerable;
import edu.nyu.cs.connectfour.game.observer.GameResultObserver;
import edu.nyu.cs.connectfour.game.subject.GameResultSubject;
import edu.nyu.cs.connectfour.ui.panel.ChessPanel;
import edu.nyu.cs.connectfour.utils.ParameterChecker;

/**
 * @author shenli
 * <p>
 * A {@code ResultLabel} object is used to create an "Result" label.
 * <p>
 * The singleton {@code ResultLabel} object contains a {@link javax.swing.JLabel} component. {@code ResultLabel}
 * object may be obtains by calls on {@link edu.nyu.cs.connectfour.ui.factory.UtilityLabelFactory#getLabel(edu.nyu.cs.connectfour.game.ui.type.UtilityLabelType)} 
 * factory methods. These will return the singleton {@code ResultLabel}.
 * <p>
 * <b>Warning:</b> Swing is not thread safe.
 */
public enum ResultLabel implements GetContainerable<JLabel>, GameResultObserver {
    INSTANCE("res/result.png", "Show result");
    
    private final JLabel label;
    private final String iconPath;
    private final String tooltipText;
    private GameResultSubject gameResult;
    
    /**
     * Creates a result label with the specified label icon path and tool tip text.
     * <p>
     * @param iconPath the icon path of this label
     * @param tooltipText the tool tip text of this label
     */
    private ResultLabel(String iconPath, String tooltipText) {
        assert iconPath != null;
        assert tooltipText != null;
        
        this.iconPath = iconPath;
        this.tooltipText = tooltipText;
        this.label = new JLabel(
                        new ImageIcon(
                                this.iconPath, "Util Symbol"),SwingConstants.LEADING);
        this.label.setPreferredSize(new Dimension(150, 100));
        this.label.setToolTipText(this.tooltipText);
        setGameResultSubject(ChessPanel.INSTANCE.getGameRecord());
    }

    /**
     * Set the {@link edu.nyu.cs.connectfour.game.subject.GameResultSubject} object in order to observe the 
     * game result. NOTE: The method is thread-safe.
     * <p>
     * @param gameResult the specific {@link edu.nyu.cs.connectfour.game.subject.GameResultSubject} object
     */
    public synchronized void setGameResultSubject(GameResultSubject gameResult) {
        ParameterChecker.nullCheck(gameResult, "edu.nyu.cs.connectfour.game.subject.GameResultSubject");
        
        this.gameResult = gameResult;
        this.gameResult.registerGameResultObserver(this);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public JLabel getContainer() {
        return label;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateGameResult(String text) {
        ParameterChecker.nullCheck(text, "Result");
        
        label.setText("<html><center><font size=5>" + text + "</font></center></html>");
    }
    
}
