package com.mygame.dungeon_hero.characters.classes;

import com.mygame.dungeon_hero.characters.Hero;

public interface HeroClass {
    Hero lvlUp (Hero hero);
    int getLvl();
}
