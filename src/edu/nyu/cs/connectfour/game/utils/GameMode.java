package edu.nyu.cs.connectfour.game.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import edu.nyu.cs.connectfour.player.subject.impl.PlayerInfo;

/**
 * @author shenli
 * <p>
 * The {@code GameMode} enum represents game mode
 * <p>
 * {@code GameMode} are constant; their values could not be changed after they are created. Because
 * {@code GameMode} objects are immutable they could be shared.
 */
public enum GameMode {
    /**
     * Human vs Computer mode
     */
    HUMAN_VS_COMPUTER("against computer", LabelAndIcon.ME, LabelAndIcon.COMPUTER),
    /**
     * Human vs Human mode
     */
    HUMAN_VS_HUMAN("against human", LabelAndIcon.PLAYERONE, LabelAndIcon.PLAYERTWO);
    
    private final String description;
    private final List<String> labels;
    private final List<Icon> icons;
    
    /**
     * @author shenli
     * <p>
     * Inner enum of {@code GameMode} used to provide player label and icon information to support for game 
     * mode object. This class is not meant to be used directly by application developers.
     * <p>
     * {@code LabelAndIcon} are constant; their values could not be changed after they are created. Because
     * {@code LabelAndIcon} objects are immutable they could be shared. 
     */
    private enum LabelAndIcon {
        ME("Me", "res/user.png"),
        COMPUTER("Computer", "res/computer.png"),
        PLAYERONE("Player One", "res/user.png"),
        PLAYERTWO("Player Two", "res/user.png"),
        DEFAULT("Default", "res/user.png");        
        
        private final String label;
        private final Icon icon;
        
        /**
         * Initializes a newly created {@code LabelAndIcon} object so that it records player label and icon.
         * <p>
         * @param label the player label
         * @param iconPath the player icon path
         */
        private LabelAndIcon(String label, String iconPath) {
            assert label != null;
            assert iconPath != null;
            
            this.label = label;
            this.icon = new ImageIcon(iconPath, "Player Symbol");
        }
        
    }
    
    /**
     * Initializes a newly created {@code GameMode} object so that it records description and user/computer
     * label and icon information.
     * <p>
     * @param description the description
     * @param labelAndIcons the {@code LabelAndIcons} object(s)
     */
    private GameMode(String description, LabelAndIcon...labelAndIcons) {
        int n = PlayerInfo.values().length;
        assert description != null;
        assert labelAndIcons.length == n;
        for (LabelAndIcon lai : labelAndIcons) {
            assert lai != null;
        }
        
        this.description = description;
        this.labels = new ArrayList<>();
        this.icons = new ArrayList<>();
        for (LabelAndIcon lai : labelAndIcons) {
            this.labels.add(lai.label);
            this.icons.add(lai.icon);
        }
        if (labelAndIcons.length < n) {
            for (int i = n - labelAndIcons.length - 1; i < n; i++) {
                this.labels.add(LabelAndIcon.DEFAULT.label);
                this.icons.add(LabelAndIcon.DEFAULT.icon);
            }
        }
    }

    /**
     * Returns an unmodifiable view of the label list. This method allows modules to provide users with 
     * "read-only" access to internal lists. Query operations on the returned list "read through" to the
     * specified list, and attempts to modify the returned list, whether direct or via its iterator, result
     * in an {@code UnsupportedOperationException}.
     * <p>
     * @return an unmodifiable view of the specified list. If no label have been created, returns an empty list
     */
    public List<String> getLabels() {
        return Collections.unmodifiableList(labels);
    }

    /**
     * Returns an unmodifiable view of the icon list. This method allows modules to provide users with
     * "read-only" access to internal lists. Query operations on the returned list "read through" to the 
     * specified list, and attempts to modify the returned list, whether direct or via its iterator, result
     * in an {@code UnsupportedOperationException}.
     * <p>
     * @return an unmodifiable view of the specified list. If no icon have been created, returns an empty list
     */
    public List<Icon> getIcons() {
        return Collections.unmodifiableList(icons);
    }
    
    /**
     * Returns string representation of this {@code GameMode} object. The string representation consists of
     * the description.
     * <p>
     * @return string representation of this {@code GameMode} object
     */
    @Override
    public String toString() {
        return description;
    }
    
}
