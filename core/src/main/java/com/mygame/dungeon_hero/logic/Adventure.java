package com.mygame.dungeon_hero.logic;

import com.badlogic.gdx.math.MathUtils;
import com.mygame.dungeon_hero.GameCore;
import com.mygame.dungeon_hero.characters.GameCharacter;
import com.mygame.dungeon_hero.characters.Enemies;
import com.mygame.dungeon_hero.characters.Hero;
import com.mygame.dungeon_hero.gameScreens.CreditsScreen;

public class Adventure {
    private final Enemies[] ENEMIES = Enemies.values();
    private final Hero hero;
    private final GameCore game;
    private int battleCount = 0;

    public Adventure(Hero hero, GameCore game) {
        this.hero = hero;
        this.game = game;
    }

    public void startAdventure() {
        nextBattle();
    }

    private void nextBattle() {
        if (battleCount >= 5) {
            adventureComplete();
            return;
        }
        battleCount++;
        int random = MathUtils.random(0, ENEMIES.length - 1);
        Enemies randomEnemy  = ENEMIES[random];
        GameCharacter enemy = new GameCharacter(randomEnemy);
        hero.fullHeal();
        Battle battle = new Battle(hero, enemy, game, battleCount, this::nextBattle);
        battle.playIntro();
    }

    private void adventureComplete() {
        game.setScreen(new CreditsScreen(game::restartGame));

    }
}
