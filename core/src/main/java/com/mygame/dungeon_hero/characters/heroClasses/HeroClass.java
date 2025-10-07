package com.mygame.dungeon_hero.characters.heroClasses;

import com.mygame.dungeon_hero.characters.Hero;

public interface HeroClass {
    Hero lvlUp(Hero hero);

    int getLvl();

    String getLvlUpDescription(Hero hero);
}
