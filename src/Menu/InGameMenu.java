package Menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

public class InGameMenu extends MenuTemplate{

    private String[] options = {	"Resume Gameplay","Save Game", "Start New Game", "Quit Game", "Exit to Main Menu", "View Leaderboard" };
    private int currentChoice = 0;
    private Color titleColor;
    private Font titleFont;
    private Font font;
    
    
public InGameMenu(MenuManager menuManager) {
	this.menuManager = menuManager;
    titleColor = new Color(230, 200, 0);
    titleFont = new Font("Century Gothic", Font.PLAIN, 28);
    font = new Font("Arial", Font.PLAIN, 12);
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Graphics2D g) {
		 g.setColor(titleColor);
	        g.setFont(titleFont);
	        g.setPaint(new Color(255,255,255));
	        g.drawString("", 80, 70);

	        //draw menu options
	        g.setFont(font);
	        for(int i = 0; i < options.length; i++) {
	            if (i == currentChoice) {
	                g.setColor(Color.WHITE);
	            } else {
	                g.setColor(Color.RED);
	            }
	            // pass horizontal distance, then vertical distance
	            g.drawString(options[i], 95, 120 + i * 15);
	        }


	}

	@Override
	public void keyPressed(int k) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub

	}

	public void select() {
		//while thread is paused/game is paused 
		//not sure yet^
		  if (currentChoice == 0) {
			  //resume game play 
	        }
	        if (currentChoice == 1) {
	            //save game
	        	menuManager.setMenuState(MenuState.SAVEGAME);
	        }
	        if (currentChoice == 2) {
	            //start new game
	        	menuManager.setMenuState(MenuState.LOADGAME);
	        }
	        if (currentChoice == 3) {
	            //quit game
	        	menuManager.setMenuState(MenuState.GAMEOVER);
	        }
	        if (currentChoice == 4) {
	            //exit to main menu
	        	menuManager.setMenuState(MenuState.MAIN);
	        }
	        if (currentChoice == 5) {
	            //view leaderboard
	        	menuManager.setMenuState(MenuState.LEADERBOARD);
	        }
	        
	}
	

}