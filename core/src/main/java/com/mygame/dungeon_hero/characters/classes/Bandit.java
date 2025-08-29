package com.mygame.dungeon_hero.characters.classes;

import com.mygame.dungeon_hero.characters.Hero;

import static com.mygame.dungeon_hero.characters.Perks.*;
import static com.mygame.dungeon_hero.characters.wepons.Weapons.*;

public class Bandit implements HeroClass {
    private static final int HEALTH_PER_LVL = 4;
    private int lvl = 0;

    public Hero lvlUp(Hero hero) {
        lvl++;
        hero.setMaxHealth(hero.getMaxHealth() + HEALTH_PER_LVL);
        switch (lvl) {
            case 1:
                if (hero.getWeapon() == null) {
                    hero.setWeapon(DAGGER);
                }
                hero.getPerks().add(SNEAK_ATTACK);
                break;
            case 2:
                hero.setAgility(hero.getAgility() + 1);
                break;
            case 3:
                hero.getPerks().add(POISON);
                break;
        }
        return hero;
    }
}
