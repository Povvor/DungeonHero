package com.mygame.dungeon_hero;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygame.dungeon_hero.assetManger.Assets;
import com.mygame.dungeon_hero.characters.Enemies;
import com.mygame.dungeon_hero.characters.GameCharacter;
import com.mygame.dungeon_hero.characters.Hero;
import com.mygame.dungeon_hero.gameScreens.UIManager;
import com.mygame.dungeon_hero.gameScreens.levels.winscreen.WinScreen;
import com.mygame.dungeon_hero.gameScreens.mainMenu.MainMenu;
import com.mygame.dungeon_hero.logic.GameWorld;
import lombok.Getter;
import org.lwjgl.Sys;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class GameCoreTest extends GameCore {
    private @Getter GameWorld gameWorld = new GameWorld(this);

    @Override
    public void create() {
        Assets.initMainAtlases();
        Assets.finishAll();
        UIManager.init();
        Hero hero = new Hero(1);
        setScreen(new WinScreen(new Hero(0), new GameCharacter(Enemies.DRAGON)));
    }
}
