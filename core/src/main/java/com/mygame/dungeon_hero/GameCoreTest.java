package com.mygame.dungeon_hero;

import com.mygame.dungeon_hero.assetManger.Assets;
import com.mygame.dungeon_hero.characters.Enemies;
import com.mygame.dungeon_hero.characters.GameCharacter;
import com.mygame.dungeon_hero.characters.Hero;
import com.mygame.dungeon_hero.gameScreens.CreditsScreen;
import com.mygame.dungeon_hero.gameScreens.UIManager;
import com.mygame.dungeon_hero.logic.Adventure;
import com.mygame.dungeon_hero.logic.Battle;
import com.mygame.dungeon_hero.logic.GameWorld;
import lombok.Getter;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class GameCoreTest extends GameCore {
    private @Getter GameWorld gameWorld = new GameWorld(this);

    @Override
    public void create() {
        Assets.initMainAtlases();
        Assets.finishAll();
        UIManager.init();
        Hero hero = new Hero(0);
        hero.lvlUp(0);
        hero.lvlUp(0);
        hero.setMaxHealth(3000000);
        hero.setHealth(300000);
        hero.fullHeal();
        hero.setAgility(0);
        GameCharacter enemy = new GameCharacter(Enemies.DRAGON);
        enemy.setHealth(500);
        Adventure adventure = new Adventure(hero, this);
        Runnable runnable = () -> System.out.println("GO");
        Battle battle = new Battle(hero, enemy, this, 5, runnable);
        //setScreen(new CreditsScreen(runnable));
        battle.playIntro();
    }
}
