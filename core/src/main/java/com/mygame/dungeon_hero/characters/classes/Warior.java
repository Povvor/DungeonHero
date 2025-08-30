package com.mygame.dungeon_hero.characters.classes;

import com.mygame.dungeon_hero.characters.Hero;
import lombok.Getter;

import static com.mygame.dungeon_hero.characters.Perks.*;
import static com.mygame.dungeon_hero.characters.wepons.Weapons.SWORD;

public class Warior implements HeroClass {
    private static final int HEALTH_PER_LVL = 6;
    private @Getter int lvl = 0;
    private final @Getter String name  = "Воин";

    @Override
    public Hero lvlUp(Hero hero) {
        lvl++;
        hero.setHealth(hero.getHealth() + HEALTH_PER_LVL);
        switch (lvl) {
            case 1:
                if (hero.getWeapon() == null) {
                    hero.setWeapon(SWORD);
                }
                hero.getPerks().add(RUSH_ACTION);
                break;
            case 2:
                hero.getPerks().add(SHIELD);
                break;
            case 3:
                hero.setStrength(hero.getStrength() + 1);
                break;
        }
        return hero;
    }
}
