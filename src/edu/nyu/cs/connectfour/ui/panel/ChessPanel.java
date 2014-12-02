package edu.nyu.cs.connectfour.ui.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import edu.nyu.cs.connectfour.container.GetContainerable;
import edu.nyu.cs.connectfour.game.observer.GameRecordObserver;
import edu.nyu.cs.connectfour.game.observer.GameStatusObserver;
import edu.nyu.cs.connectfour.game.subject.GameRecordSubject;
import edu.nyu.cs.connectfour.game.subject.GameResultSubject;
import edu.nyu.cs.connectfour.game.subject.GameStatusSubject;
import edu.nyu.cs.connectfour.game.subject.impl.GameRecord;
import edu.nyu.cs.connectfour.game.subject.impl.GameUtility;
import edu.nyu.cs.connectfour.game.utils.ComputerLevel;
import edu.nyu.cs.connectfour.game.utils.GameMode;
import edu.nyu.cs.connectfour.player.subject.impl.PlayerInfo;
import edu.nyu.cs.connectfour.ui.factory.ChessButtonFactory;
import edu.nyu.cs.connectfour.ui.label.ResultLabel;
import edu.nyu.cs.connectfour.utils.ParameterChecker;

/**
 * @author shenli
 * <p>
 * A {@code ChessPanel} object is used to create an "Chess Map" panel.
 * <p>
 * The singleton {@code ChessPanel} object contains a {@link javax.swing.JPanel} component. {@code ChessPanel}
 * object may be obtains by calls on {@link edu.nyu.cs.connectfour.ui.factory.PanelFactory#getPanel(edu.nyu.cs.connectfour.ui.type.PanelType)} 
 * factory methods. These will return the singleton {@code ChessPanel}.
 * <p>
 * <b>Warning:</b> Swing is not thread safe.
 */
public enum ChessPanel implements GetContainerable<JPanel>, GameStatusObserver, GameRecordObserver {
    /**
     * The singleton instance presents chess panel swing.
     */
    INSTANCE(6, 7);

    private final GameStatusSubject gameStatus;
    private final JPanel chessPanel;
    private final List<JButton> chessList;
    private final int rows;
    private final int columns;
    private final boolean[][] flag;
    private GameRecordSubject gameRecord;
    
    /**
     * Creates a "Chess Map" panel with the specified rows and columns.
     * <p>
     * @param rows the number of rows of this component
     * @param columns the number of columns of this component
     */
    private ChessPanel(int rows, int columns) {
        assert rows > 0;
        assert columns > 0;
        
        this.rows = rows;
        this.columns = columns;
        this.chessPanel = new JPanel(new GridLayout(this.rows, this.columns));
        this.chessPanel.setPreferredSize(new Dimension(50 * this.rows, 50 * this.columns));
        this.chessPanel.setBackground(Color.BLUE);
        this.flag = new boolean[this.rows][this.columns];
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                this.flag[i][j] = true;
                JButton b = ChessButtonFactory.getChessButton();
                b.setBackground(Color.WHITE);
                b.setEnabled(false);
                this.chessPanel.add(b);
            }
        }
        this.chessList= ChessButtonFactory.getChessButtons();
        for (JButton b : this.chessList) {
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ((GameRecord) gameRecord).place(
                            chessList.indexOf(
                                    (JButton) e.getSource()) % ChessPanel.this.columns);
                }
            });
        }
        this.gameStatus = GameUtility.INSTANCE;
        this.gameStatus.registerGameStatusObserver(this);
        setGameRecord(new GameRecord(this.rows, this.columns));
    }
    
    /**
     * Returns {@link edu.nyu.cs.connectfour.game.subject.impl.GameRecord} object.
     * <p>
     * @return {@link edu.nyu.cs.connectfour.game.subject.impl.GameRecord} object
     */
    public synchronized GameRecord getGameRecord() {
        return (GameRecord) gameRecord;
    }
    
    /**
     * Resign this object to start a new game.
     */
    public synchronized void resignChessPanel() {
        setGameRecord(new GameRecord(rows, columns));
        ResultLabel.INSTANCE.setGameResultSubject((GameResultSubject) gameRecord);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                flag[i][j] = true;
            }
        }
        for (JButton b : chessList) {
            b.setBackground(Color.WHITE);
            b.setEnabled(false);
        }
    }
    
    /**
     * Set the specific {@link edu.nyu.cs.connectfour.game.subject.impl.GameRecord} object in order to observe 
     * the game record.
     * <p>
     * @param gameRecord the specific {@link edu.nyu.cs.connectfour.game.subject.impl.GameRecord} object
     */
    private synchronized void setGameRecord(GameRecord gameRecord) {
        assert gameRecord != null;
        
        this.gameRecord = gameRecord;
        this.gameRecord.registerGameRecordObserver(this);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel getContainer() {
        return chessPanel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateGameRecord(PlayerInfo pi, int row, int column) {
        ParameterChecker.nullCheck(pi, "player information");
        ParameterChecker.rangeCheck(row, "game row number");
        ParameterChecker.rangeCheck(column, "game column number");
        
        flag[row][column] = false;
        JButton b = chessList.get(row * this.columns + column);
        b.setBackground(pi.getColor());
        b.setEnabled(flag[row][column]);
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
        if (playing) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    chessList.get(i * columns + j).setEnabled(flag[i][j]);
                }
            }
        } else {
            for (JButton b : chessList) {
                b.setEnabled(false);
            }
        }
    }

}
