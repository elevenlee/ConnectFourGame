package edu.nyu.cs.connectfour;

import javax.swing.JFrame;

import edu.nyu.cs.connectfour.container.GetContainerable;
import edu.nyu.cs.connectfour.container.factory.GameContainerFactory;
import edu.nyu.cs.connectfour.utils.SwingConsole;

/**
 * @author shenli
 * <p>
 * The main entry that launch Connect Four game.
 */
public class ConnectFourApp {

    public static void main(String[] args) {
        GetContainerable<JFrame> container = 
                GameContainerFactory.getGameContainer("" + System.getenv().get("LOGNAME"), 800, 800);
        SwingConsole.run(container.getContainer());
    }

}
