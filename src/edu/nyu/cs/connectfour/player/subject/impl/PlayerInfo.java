package edu.nyu.cs.connectfour.player.subject.impl;

import java.awt.Color;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import edu.nyu.cs.connectfour.player.observer.PlayerInfoObserver;
import edu.nyu.cs.connectfour.player.subject.PlayerInfoSubject;
import edu.nyu.cs.connectfour.utils.ParameterChecker;

/**
 * @author shenli
 * <p>
 * The {@code PlayerInfo} enum represents player information.
 * <p>
 * {@code PlayerInfo} objects are not constant; their values could be changed after they are created.
 * NOTE: the {@code PlayerInfo} object is thread-safe.
 */
public enum PlayerInfo implements PlayerInfoSubject {
    PLAYER_ONE("Me", Color.RED),
    PLAYER_TWO("Computer", Color.YELLOW);

    private static final String SEPARATOR = "/";
    
    private final List<PlayerInfoObserver> observers;
    private String name;
    private Color color;
    
    /**
     * Initializes a newly created {@code PlayerInfo} object so that it records player name and color
     * representation.
     * <p>
     * @param name the name
     * @param color the color
     */
    private PlayerInfo(String name, Color color) {
        assert name != null;
        assert color != null;
        
        this.observers = new CopyOnWriteArrayList<>();
        this.name = name;
        this.color = color;
    }
    
    /**
     * Returns player name
     * <p>
     * @return player name
     */
    public String getName() {
        synchronized(name) {
            return name;
        }
    }
    
    /**
     * Returns player color representation
     * <p>
     * @return player color representation
     */
    public Color getColor() {
        synchronized(color) {
            return color;
        }
    }
    
    /**
     * Sets player's name and color representation.
     * <p>
     * @param name the name used to set the player
     * @param color the color used to set the player
     */
    public void setAll(String name, Color color) {
        ParameterChecker.nullCheck(name, "player name");
        ParameterChecker.nullCheck(color, "player color");
        
        synchronized(name) {
            this.name = name;
        }
        synchronized(color) {
            this.color = color;
        }
        notifyPlayerInfoObservers();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void registerPlayerInfoObserver(PlayerInfoObserver o) {
        ParameterChecker.nullCheck(o, "edu.nyu.cs.connectfour.player.observer.PlayerInfoObserver");
        
        observers.add(o);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removePlayerInfoObserver(PlayerInfoObserver o) {
        int index = observers.indexOf(o);
        if (index >= 0) {
            observers.remove(index);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyPlayerInfoObservers() {
        for (PlayerInfoObserver pio : observers) {
            pio.updatePlayerInfo(this, name, color);
        }
    }
    
    /**
     * Returns string representation of this {@code PlayerInfo} object. The string representation consists of
     * the player name and color separated by '/' character. And {@code Color} object is represented by its
     * RGB value.
     * <p>
     * @return string representation of this {@code PlayerInfo} object
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String copy = null;
        synchronized(name) {
            copy = name;
        }
        sb.append(copy);
        sb.append(SEPARATOR);
        synchronized(color) {
            copy = String.valueOf(color.getRGB());
        }
        sb.append(copy);
        return sb.toString();
    }

}
