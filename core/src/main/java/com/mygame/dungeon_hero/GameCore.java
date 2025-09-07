package com.mygame.dungeon_hero;

import com.badlogic.gdx.Game;
import com.mygame.dungeon_hero.gameScreens.UIManager;
import com.mygame.dungeon_hero.gameScreens.mainMenu.MainMenu;
import com.mygame.dungeon_hero.logic.GameWorld;
import lombok.Getter;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class GameCore extends Game {
    private @Getter GameWorld gameWorld = new GameWorld(this);

    @Override
    public void create() {
        UIManager.init();
        setScreen(new MainMenu(this));
    }
}
