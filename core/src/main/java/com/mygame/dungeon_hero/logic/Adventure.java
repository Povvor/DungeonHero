package com.mygame.dungeon_hero.logic;

import com.mygame.dungeon_hero.GameCore;
import com.mygame.dungeon_hero.characters.GameCharacter;
import com.mygame.dungeon_hero.characters.Enemies;
import com.mygame.dungeon_hero.characters.Hero;
import com.mygame.dungeon_hero.gameScreens.levels.WinScreen;

import java.util.concurrent.ThreadLocalRandom;

public class Adventure {
    private final Enemies[] ENEMIES = Enemies.values();
    private Hero hero;
    private GameCore game;
    private int battleCount = 1;


    public Adventure(Hero hero, GameCore game) {
        this.hero = hero;
        this.game = game;
    }

    public void startAdventure() {
        nextBattle();
    }

    private void checkStatusAfterBattle(GameCharacter enemy) {
        if (hero.getHealth() <= 0) {
            game.setScreen(new WinScreen(hero, enemy));
        } else {
            game.setScreen(new WinScreen(hero, enemy));
        }
    }

    private void nextBattle() {
        int random = ThreadLocalRandom.current().nextInt(0, ENEMIES.length);
        Enemies randomEnemy  = ENEMIES[random];
        GameCharacter enemy = new GameCharacter(randomEnemy);
        Battle battle = new Battle(hero, enemy, game, battleCount, () -> checkStatusAfterBattle(enemy));
        battle.playIntro();
    }
}
