package com.mygame.dungeon_hero;

import com.mygame.dungeon_hero.characters.Hero;
import com.mygame.dungeon_hero.characters.classes.HeroClass;
import lombok.Getter;

public class GameWorld {
    private @Getter Hero hero;

    public void createHero(int inputIndex) {
        hero = new Hero(inputIndex);
    }

    public void startAdventure() {
        Adventure adventure = new Adventure(hero);
        adventure.startAdventure();
    }

}
