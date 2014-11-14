package Menu;

import Database.DatabaseController;
import SystemController.GameStateManager;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by Shabab Ahmed on 13/11/2014.
 */
public class LoadGame extends MenuTemplate {

    private String[] options = {"Load", "Back to Main Menu"};
    private int currentChoice = 0;
    private Color titleColor;
    private Font titleFont;
    private Font font;

    public LoadGame (MenuManager menuManager, GameStateManager gsm) {
        this.menuManager = menuManager;
        this.gsm = gsm;
        titleColor = new Color(230, 200, 0);
        titleFont = new Font("Gill Sans Ultra Bold", Font.PLAIN, 28);
        font = new Font("Gill Sans Ultra Bold", Font.PLAIN, 12);
    }

    @Override
    public void init() {

    }

    @Override
    public void draw(Graphics2D g) {

        //draw the title
        g.setColor(titleColor);
        g.setFont(titleFont);
        g.setPaint(new Color(255,255,255));
        g.drawString("BOMBERMAN", 80, 70);
        g.drawString("Load Game", 80, 100);

        //draw menu options
        g.setFont(font);
        for(int i = 0; i < options.length; i++) {
            if (i == currentChoice) {
                g.setColor(Color.RED);
            } else {
                g.setColor(Color.YELLOW);
            }
            // pass horizontal distance, then vertical distance
            g.drawString(options[i], 95, 140 + i * 15);
        }

    }

    private void select() {
        if (currentChoice == 0) {
            //Load Game
        }
        if (currentChoice == 1) {
            menuManager.setMenuState(MenuState.MAIN);
        }
    }

    @Override
    public void keyPressed(int k) {
        if (k == KeyEvent.VK_ENTER) {
            select();
        }
        if (k == KeyEvent.VK_UP) {
            currentChoice--;
            if (currentChoice == -1) currentChoice  = options.length - 1;
        }
        if (k == KeyEvent.VK_DOWN) {
            currentChoice++;
            if (currentChoice == options.length) currentChoice = 0;
        }
    }

    @Override
    public void keyReleased(int k) {

    }


}
