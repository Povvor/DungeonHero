package com.mygame.dungeon_hero.logic;

import com.mygame.dungeon_hero.GameCore;
import com.mygame.dungeon_hero.characters.Enemies;
import com.mygame.dungeon_hero.characters.Hero;
import com.mygame.dungeon_hero.gameScreens.levels.BattleIntro;
import lombok.Getter;

public class GameWorld {
    private @Getter Hero hero;
    private @Getter GameCore game;
    private @Getter Adventure adventure;

    public GameWorld(GameCore game) {
        this.game = game;
    }

    public void createHero(int inputIndex) {
        hero = new Hero(inputIndex);
    }

    public void startAdventure() {
        adventure = new Adventure(hero, game);
        adventure.startAdventure();
    }

}
