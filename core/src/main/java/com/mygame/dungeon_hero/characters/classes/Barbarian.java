package com.mygame.dungeon_hero.characters.classes;

import com.mygame.dungeon_hero.characters.Hero;
import lombok.Getter;

import static com.mygame.dungeon_hero.characters.Perks.*;
import static com.mygame.dungeon_hero.characters.wepons.Weapons.CLUB;

public class Barbarian implements HeroClass {
    private static final int HEALTH_PER_LVL = 6;
    private @Getter int lvl = 0;
    private final @Getter String name  = "Варвар";

    @Override
    public Hero lvlUp(Hero hero) {
        lvl++;
        hero.setHealth(hero.getHealth() + HEALTH_PER_LVL);
        switch (lvl) {
            case 1:
                if (hero.getWeapon() == null) {
                    hero.setWeapon(CLUB);
                }
                hero.getPerks().add(FURY);
                break;
            case 2:
                hero.getPerks().add(STONE_SKIN);
                break;
            case 3:
                hero.setEndurance(hero.getEndurance() + 1);
                break;
        }
        return hero;
    }
}
