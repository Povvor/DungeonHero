package com.mygame.dungeon_hero;

import com.badlogic.gdx.Game;
import com.mygame.dungeon_hero.gameScreens.UIManager;
import com.mygame.dungeon_hero.gameScreens.mainMenu.MainMenu;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    @Override
    public void create() {
        UIManager.initialize();
        setScreen(new MainMenu(this));
    }
}
