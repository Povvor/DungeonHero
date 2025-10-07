package com.mygame.dungeon_hero.characters.heroClasses;

import com.mygame.dungeon_hero.characters.Hero;
import lombok.Getter;

import static com.mygame.dungeon_hero.characters.Perks.*;
import static com.mygame.dungeon_hero.characters.Weapons.*;

public class Bandit implements HeroClass {
    private static final int HEALTH_PER_LVL = 4;
    private int lvl = 0;
    private final @Getter String name  = "Разбойник";

    public Hero lvlUp(Hero hero) {
        lvl++;
        hero.setMaxHealth(hero.getMaxHealth() + HEALTH_PER_LVL);
        switch (lvl) {
            case 1:
                if (hero.getWeapon() == null) {
                    hero.changeWeapon(DAGGER);
                }
                hero.setMaxHealth(hero.getMaxHealth() + hero.getEndurance());
                hero.fullHeal();
                hero.getPerks().add(SNEAK_ATTACK);
                break;
            case 2:
                hero.setAgility(hero.getAgility() + 1);
                break;
            case 3:
                hero.getPerks().add(POISON);
                break;
            default:
                hero.lvlUp();
        }
        return hero;
    }

    @Override
    public String getLvlUpDescription(Hero hero) {
        return switch (lvl + 1) {
            case 1 -> "Стартовое оружие: Кинжал\n" + "Урон оружием "
                + DAGGER.getDamage() + "\n" + SNEAK_ATTACK.getDescription();
            case 2 -> "Ловкость + 1";
            case 3 -> POISON.getDescription();
            default -> "Максимальный уровень!";
        };
    }

    @Override
    public int getLvl() {
        return lvl;
    }
}
