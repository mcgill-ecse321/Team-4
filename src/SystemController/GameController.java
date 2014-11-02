/**
 * Created by danielmacario on 14-10-29.
 */
package SystemController;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;

/**
 * GameController manages the single thread of the application.
 * It also trickles user inputs to the corresponding game state the user
 * is in. It also sets up the top-most visual layer of the application.
 */
public class GameController extends JPanel implements Runnable, KeyListener {

    public static final int WIDTH = 480;
    public static final int HEIGHT = 416;
    //TODO: decide wether we should rescale the screen to make it bigger
    public static final int SCALE = 2;

    // game thread
    private Thread thread;
    private boolean running;
    private int FPS = 30;
    private long targetTime = 1000 / FPS;

    //image
    private BufferedImage image;
    private Graphics2D g;

    // game state manager instance
    private GameStateManager gsm;

    public GameController() {
        super();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        setBackground(Color.BLACK);
        requestFocus();
    }


    public void addNotify() {
        super.addNotify();
        if (thread == null) {
            thread = new Thread(this);
            addKeyListener(this);
            thread.start();
        }
    }


    public void run() {
        long start;
        long elapse;
        long wait;

        init();
        //game loop
        while (running) {

            g.setPaint(new Color(10, 176,0));
            g.fillRect(0, 0, image.getWidth(), image.getHeight() );
            start = System.nanoTime();

            update();
            draw();
            drawToScreen();

            elapse  = System.nanoTime() - start;

            wait = Math.abs(targetTime - elapse / 1000000);

            try {
                Thread.sleep(wait);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    private void init() {
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = image.createGraphics();
        g.setPaint(new Color(0,0,200));
        g.fillRect(0, 0, image.getWidth(), image.getHeight() );
        running = true;
        gsm = new GameStateManager();
    }

    private void update() {
        gsm.update();
    }

    private void draw() {
        gsm.draw(g);
    }

    private void drawToScreen() {
        Graphics g2 = getGraphics();
        g2.drawImage(image, 0, 0, WIDTH, HEIGHT, null);
        g2.dispose();
    }

    public void keyTyped(KeyEvent key) {

    }

    public void keyPressed(KeyEvent key) {
        gsm.keyPressed(key.getKeyCode());
    }

    public void keyReleased(KeyEvent key) {
        gsm.keyReleased(key.getKeyCode());
    }



}
