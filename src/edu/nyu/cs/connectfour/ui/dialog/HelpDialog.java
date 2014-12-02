package edu.nyu.cs.connectfour.ui.dialog;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

import edu.nyu.cs.connectfour.container.GetContainerable;

/**
 * @author shenli
 * <p>
 * A {@code HelpDialog} object is used to create an "Help dialog" window.
 * <p>
 * The singleton {@code HelpDialog} object contains a {@link javax.swing.JDialog} component. {@code HelpDialog}
 * object may be obtains by calls on {@link edu.nyu.cs.connectfour.ui.factory.DialogFactory#getDialog(edu.nyu.cs.connectfour.ui.type.DialogType)} 
 * factory methods. These will return the singleton {@code HelpDialog}.
 * <p>
 * <b>Warning:</b> Swing is not thread safe.
 */
public enum HelpDialog implements GetContainerable<JDialog> {
    /**
     * The singleton instance presents help dialog swing.
     */
    INSTANCE("Help", 510, 450);
    
    private static final String ICON_PATH = "res/connectfour.png";
    
    private final JDialog helpDialog;
    private final Icon helpIcon;
    private final JLabel helpLabel;
    private final JButton closeButton;
    
    /**
     * Creates an "Help dialog" with the specified title, width and height.
     * <p>
     * @param title the display in the Help dialog's title bar
     * @param width the width of this component in pixels
     * @param height the height of this component in pixels
     */
    private HelpDialog(String title, int width, int height) {
        assert title != null;
        assert width > 0;
        assert height > 0;
        
        helpDialog = new JDialog();
        helpDialog.setTitle(title);
        helpDialog.setModal(true);
        helpDialog.setLayout(new FlowLayout());
        
        helpIcon = new ImageIcon(ICON_PATH, "ConnectFour Symbol");
        helpLabel = new JLabel(getHelpText());
        closeButton = new JButton("Close");
        closeButton.setPreferredSize(new Dimension(80, 30));
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                helpDialog.dispose();
            }
        });
        
        helpDialog.add(new JLabel(helpIcon));
        helpDialog.add(helpLabel);
        helpDialog.add(closeButton);
        helpDialog.getRootPane().setDefaultButton(closeButton);
        helpDialog.setSize(width, height);
        helpDialog.setResizable(false);
        helpDialog.setLocationRelativeTo(null);     // Help dialog center showing
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public JDialog getContainer() {
        return helpDialog;
    }
    
    /**
     * Returns string representation of Help dialog contexts.
     * <p>
     * @return string representation of Help dialog contexts
     */
    private static String getHelpText() {
        StringBuilder sb = new StringBuilder();
        sb.append("<html><hr><br><body><font size=4>");
        sb.append("Connect Four (also known as Captain's Mistress) is a two<br>");
        sb.append("player game in which the players first choose a color and<br>");
        sb.append("then take turns dropping colored discs from the top into<br>");
        sb.append("a seven-column, six-row vertically-suspended grid.<br><br>");
        sb.append("The pieces fall straight down, occupying the next available<br>");
        sb.append("space within the column. The object of the game isto connect<br>");
        sb.append("four of one's own discs of the same color next to each other<br>");
        sb.append("vertically, horizontally, or diagonally before your opponent.<br><br>");
        sb.append("<b>See more information on Wiki ");
        sb.append("<a href=http://en.wikipedia.org/wiki/Connect_Four>Connect Four</a></b>");
        sb.append("<br><br>");
        sb.append("</font></body></html>");
        return sb.toString();
    }
    
}