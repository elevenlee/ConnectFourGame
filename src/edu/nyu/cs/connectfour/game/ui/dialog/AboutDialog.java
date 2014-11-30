package edu.nyu.cs.connectfour.game.ui.dialog;

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
 * An {@code AboutDialog} object is used to create an "About dialog" window.
 * <p>
 * The singleton {@code AboutDialog} object contains a {@link javax.swing.JDialog} component. {@code AboutDialog}
 * object may be obtains by calls on {@link edu.nyu.cs.connectfour.game.ui.factory.DialogFactory#getDialog(edu.nyu.cs.connectfour.game.ui.type.DialogType)} 
 * factory methods. These will return the singleton {@code AboutDialog}.
 * <p>
 * <b>Warning:</b> Swing is not thread safe.
 */
public enum AboutDialog implements GetContainerable<JDialog> {
    INSTANCE("About", 500, 360);
    
    private static final String ICON_PATH = "res/connectfour.png";
    
    private final JDialog aboutDialog;
    private final Icon aboutIcon;
    private final JLabel aboutLabel;
    private final JButton closeButton;
    
    /**
     * Creates an "About dialog" with the specified title, width and height.
     * <p>
     * @param title the display in the About dialog's title bar
     * @param width the width of this component in pixels
     * @param height the height of this component in pixels
     */
    private AboutDialog(String title, int width, int height) {
        assert title != null;
        assert width > 0;
        assert height > 0;
        
        aboutDialog = new JDialog();
        aboutDialog.setTitle(title);
        aboutDialog.setModal(true);
        aboutDialog.setLayout(new FlowLayout());
        
        aboutIcon = new ImageIcon(ICON_PATH, "ConnectFour Symbol");
        aboutLabel = new JLabel(getAboutText());
        closeButton = new JButton("Close");
        closeButton.setPreferredSize(new Dimension(80, 30));
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aboutDialog.dispose();
            }
        });
        
        aboutDialog.add(new JLabel(aboutIcon));
        aboutDialog.add(aboutLabel);
        aboutDialog.add(closeButton);
        aboutDialog.getRootPane().setDefaultButton(closeButton);
        aboutDialog.setSize(width, height);
        aboutDialog.setResizable(false);
        aboutDialog.setLocationRelativeTo(null);    // About dialog center showing
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public JDialog getContainer() {
        return aboutDialog;
    }
    
    /**
     * Returns string representation of About dialog contexts.
     * <p>
     * @return string representation of About dialog contexts
     */
    private static String getAboutText() {
        StringBuilder sb = new StringBuilder();
        sb.append("<html><body><hr><br><font size=4><center>");
        sb.append("<b>Connect Four</b><br>");
        sb.append("<center>Version 1.0</center><br>");
        sb.append("<i>Copyright © 1997-2012 Free Software Foundation, Inc.</i><br><br>");
        sb.append("Developed for Free Software Foundation, Inc. by Eleven Lee.<br><br>");
        sb.append("</center></font></body></html>");
        return sb.toString();
    }
    
}
