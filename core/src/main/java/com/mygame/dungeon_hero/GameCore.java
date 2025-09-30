package com.mygame.dungeon_hero;

import com.badlogic.gdx.Game;
import com.mygame.dungeon_hero.assetManger.Assets;
import com.mygame.dungeon_hero.assetManger.SoundManager;
import com.mygame.dungeon_hero.gameScreens.UIManager;
import com.mygame.dungeon_hero.gameScreens.mainMenu.MainMenu;
import com.mygame.dungeon_hero.logic.GameWorld;
import lombok.Getter;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class GameCore extends Game {
    private @Getter GameWorld gameWorld = new GameWorld(this);

    @Override
    public void create() {
        Assets.initMainAtlases();
        UIManager.init();
        SoundManager.stopMusic();
        setScreen(new MainMenu(this));

    }

    public void restartGame() {
        setScreen(new MainMenu(this));
        SoundManager.stopMusic();
    }
}
