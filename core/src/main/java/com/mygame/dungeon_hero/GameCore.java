package com.mygame.dungeon_hero;

import com.badlogic.gdx.Game;
import com.mygame.dungeon_hero.gameScreens.UIManager;
import com.mygame.dungeon_hero.gameScreens.mainMenu.MainMenu;
import lombok.Getter;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class GameCore extends Game {
    private @Getter GameWorld gameWorld = new GameWorld();

    @Override
    public void create() {
        UIManager.initialize();
        setScreen(new MainMenu(this));
    }
}
