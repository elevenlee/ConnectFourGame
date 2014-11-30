package edu.nyu.cs.connectfour.game.ui.menu;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.JMenuBar;

import edu.nyu.cs.connectfour.container.GetContainerable;

/**
 * @author shenli
 * <p>
 * A {@code GameMenuBar} object is used to create an "Game" menu bar.
 * <p>
 * The singleton {@code GameMenuBar} object contains a {@link javax.swing.JMenuBar} component. It also
 * maintains a {@link java.util.Map} Collection which contains all menu items and their corresponding menus.
 * <p>
 * <b>Warning:</b> Swing is not thread safe.
 */
public enum GameMenuBar implements GetContainerable<JMenuBar> {
    INSTANCE;
    
    private final JMenuBar menuBar;
    private Map<GameMenu, Set<GameMenuItem>> itemsByCategory;
    
    /**
     * Creates a menu bar and put all menu items along with its menu category in it.
     */
    private GameMenuBar() {
        this.menuBar = new JMenuBar();
        this.itemsByCategory = 
                new EnumMap<GameMenu, Set<GameMenuItem>>(GameMenu.class);
        for (GameMenu gm : GameMenu.values()) {
            itemsByCategory.put(gm, new LinkedHashSet<GameMenuItem>());
        }
        for (GameMenuItem gmi : GameMenuItem.values()) {
            itemsByCategory.get(gmi.getCategory()).add(gmi);
        }
        
        Iterator<Map.Entry<GameMenu, Set<GameMenuItem>>> iter = 
                itemsByCategory.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<GameMenu, Set<GameMenuItem>> entry = iter.next();
            for (GameMenuItem gmi : entry.getValue()) {
                gmi.addMenuItemAction();
                entry.getKey().getContainer().add(gmi.getContainer());
            }
            menuBar.add(entry.getKey().getContainer());
        }
    }

    /**
     * Returns an unmodifiable view of the menu bar map. This method allows modules to provide users with 
     * "read-only" access to internal maps. Query operations on the returned map "read through" to the 
     * specified map, and attempts to modify the returned map, whether direct or via its collection views, 
     * result in an {@code UnsupportedOperationException}.
     * <p>
     * @return an unmodifiable view of the specified menu bar map. If the menu bar is empty, returns an empty map
     */
    public Map<GameMenu, Set<GameMenuItem>> getMenus() {
        return Collections.unmodifiableMap(itemsByCategory);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public JMenuBar getContainer() {
        return menuBar;
    }

}
