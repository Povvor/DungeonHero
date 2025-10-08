package com.mygame.dungeon_hero.logic;

import com.mygame.dungeon_hero.GameCore;
import com.mygame.dungeon_hero.characters.Hero;
import lombok.Getter;

public class GameWorld {
    private @Getter Hero hero;
    private final @Getter GameCore game;
    private @Getter Adventure adventure;

    public GameWorld(GameCore game) {
        this.game = game;
    }

    public void firstLvlUp(int classIndex) {
        hero.initFirstClass(classIndex);
    }

    public void createHero() {
        hero = new Hero();
    }

    public void startAdventure() {
        adventure = new Adventure(hero, game);
        adventure.startAdventure();
    }

}
