package com.mygame.dungeon_hero.characters.heroClasses;

import com.mygame.dungeon_hero.characters.Hero;
import lombok.Getter;

import static com.mygame.dungeon_hero.characters.Perks.*;
import static com.mygame.dungeon_hero.characters.Weapons.CLUB;

public class Barbarian implements HeroClass {
    private static final int HEALTH_PER_LVL = 6;
    private int lvl = 0;
    private final @Getter String name  = "Варвар";

    @Override
    public Hero lvlUp(Hero hero) {
        lvl++;
        hero.setMaxHealth(hero.getMaxHealth() + HEALTH_PER_LVL);
        switch (lvl) {
            case 1:
                hero.setMaxHealth(hero.getMaxHealth() + hero.getEndurance());
                hero.fullHeal();
                if (hero.getWeapon() == null) {
                    hero.changeWeapon(CLUB);
                }
                hero.getPerks().add(FURY);
                break;
            case 2:
                hero.getPerks().add(STONE_SKIN);
                break;
            case 3:
                hero.setEndurance(hero.getEndurance() + 1);
                break;
            default:
                hero.lvlUp();
        }
        return hero;
    }

    @Override
    public String getLvlUpDescription(Hero hero) {
        return switch (lvl + 1) {
            case 1 -> "Стартовое оружие: Дубина\n" + "Урон оружием "
                + CLUB.getDamage() + "\n" + FURY.getDescription();
            case 2 -> STONE_SKIN.getDescription();
            case 3 -> "Выносливость + 1";
            default -> "Максимальный уровень!";
        };
    }

    @Override
    public int getLvl() {
        return lvl;
    }
}
