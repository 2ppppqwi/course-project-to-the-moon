package java.drivers;

import java.adaptors.DogGameController;
import java.adaptors.DogGamePresenter;
import java.entities.Dog;
import java.entities.Sprite;
import java.usecases.DogMover;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.MouseListener;
import java.io.IOException;

/**
 * This class represents a dog game instance, making all the JFrames necessary to run it.
 * @author Andy Wang
 * @since 9 October 2021
 */
public class DogGame {
    private JFrame mainFrame;

    /**
     * This is the main method. Run this to run the game.
     * @param args Unused.
     */
    public static void main(String[] args) throws IOException {
        DogGame dg = new DogGame();
        dg.start();
    }

    /**
     * Initialize a new dog game and all its frames.
     */
    public DogGame() {
        int WIDTH = 300;
        int HEIGHT = 500;

        mainFrame = new JFrame();
        /* width of the frame */
        mainFrame.setSize(WIDTH, HEIGHT);
        mainFrame.setResizable(false);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // TODO: if we decide to implement saving, change the default operation to something else
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setFocusable(true);
        mainFrame.requestFocus();

        JPanel presenter = new DogGamePresenter(WIDTH, HEIGHT);
        MouseListener controller = new DogGameController();

        presenter.addController(controller);
        mainFrame.add(presenter);
    }

    /**
     * Starts the dog game.
     */
    public void start() {
        mainFrame.setVisible(true);
    }
}
