package com.mygame.dungeon_hero.characters.classes;

import com.mygame.dungeon_hero.characters.Hero;
import lombok.Getter;

import static com.mygame.dungeon_hero.characters.Perks.*;
import static com.mygame.dungeon_hero.characters.wepons.Weapons.*;

public class Bandit implements HeroClass {
    private static final int HEALTH_PER_LVL = 4;
    private int lvl = 0;
    private final @Getter String name  = "Разбойник";

    public Hero lvlUp(Hero hero) {
        lvl++;
        hero.setHealth(hero.getHealth() + HEALTH_PER_LVL);
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

    @Override
    public int getLvl() {
        return lvl;
    }
}
