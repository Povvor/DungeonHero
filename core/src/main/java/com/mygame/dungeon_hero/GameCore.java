package com.mygame.dungeon_hero;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.mygame.dungeon_hero.assetsManagers.TextureManager;
import com.mygame.dungeon_hero.assetsManagers.SoundManager;
import com.mygame.dungeon_hero.assetsManagers.UIManager;
import com.mygame.dungeon_hero.gameScreens.mainMenu.MainMenu;
import com.mygame.dungeon_hero.logic.GameWorld;
import lombok.Getter;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class GameCore extends Game {
    private final @Getter GameWorld gameWorld = new  GameWorld(this);
    private Screen mainMenu;

    @Override
    public void create() {
        TextureManager.initMainAtlases();
        TextureManager.finishAll();
        UIManager.init();
        SoundManager.stopMusic();
        SoundManager.loadAllSfx();
        mainMenu = new MainMenu(this);
        setScreen(mainMenu);
    }

    public void restartGame() {
        setScreen(mainMenu);
        SoundManager.stopMusic();
        SoundManager.loadAndPlayBgMusic("main_menu");
    }

    public void disposeAllAnfQuit() {
        this.dispose();
        Gdx.app.exit();
    }

    @Override
    public void dispose() {
        super.dispose();
        UIManager.dispose();
        SoundManager.dispose();
        TextureManager.dispose();
        mainMenu.dispose();
    }
}
