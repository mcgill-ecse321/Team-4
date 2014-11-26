package Menu;

import GamePlay.GamePlayManager;
import SystemController.GameFileManager;
import SystemController.GameState;
import SystemController.GameStateManager;

import java.awt.*;
import java.util.Hashtable;

/**
 * Created by danielmacario on 14-10-31.
 */
public class MenuManager extends GameState {

    private Hashtable<MenuState, MenuTemplate> menuStates;
    private LeaderboardMenu leaderboardMenu;
    private MenuState currentMenu;

    public MenuManager(GameStateManager gsm) {
        this.gsm = gsm;
        menuStates = new Hashtable<MenuState, MenuTemplate>();
        menuStates.put(MenuState.MAIN, new MainMenu(this, gsm));
        menuStates.put(MenuState.LOGIN, new LoginMenu(this));
        menuStates.put(MenuState.INGAME, new InGameMenu(this, gsm));
        menuStates.put(MenuState.GAMEOVER, new GameOverMenu(this));
        leaderboardMenu = new LeaderboardMenu(this);
        menuStates.put(MenuState.LEADERBOARD, leaderboardMenu);
        currentMenu = MenuState.LOGIN;
    }

    public void setMenuState(MenuState state) {
        currentMenu = state;
        menuStates.get(currentMenu).init();
    }

    public void saveGame(String fileName) {
        gsm.saveGame(fileName);
    }

    public void setUpLoadedGame(GamePlayManager gamePlayManager) {
        gsm.loadGame(gamePlayManager);
    }

    public void startGameFromSelectedStage(int stageSelected) {
        GamePlayManager newGame = new GamePlayManager(gsm, stageSelected);
        gsm.loadGame(newGame);
    }

    public void associatePlayerUserName(String username) {
        GameFileManager.setUserName(username);
        gsm.setPlayerUserName(username);
    }


    public String getPlayerUserName(){
        return gsm.getPlayerUserName();
    }

    public void setPreviousLeaderboardMenuState(MenuState state) {
        leaderboardMenu.setPreviousMenuState(state);
    }

    @Override
    public void draw(Graphics2D g) {
        menuStates.get(currentMenu).draw(g);
    }

    @Override
    public void keyPressed(int k) {
        menuStates.get(currentMenu).keyPressed(k);
    }

    @Override
    public void keyReleased(int k) {
        menuStates.get(currentMenu).keyReleased(k);
    }

}
