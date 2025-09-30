package com.mygame.dungeon_hero.characters.classes;

import com.mygame.dungeon_hero.characters.Hero;
import lombok.Getter;

import static com.mygame.dungeon_hero.characters.Perks.*;
import static com.mygame.dungeon_hero.characters.wepons.Weapons.DAGGER;
import static com.mygame.dungeon_hero.characters.wepons.Weapons.SWORD;

public class Warior implements HeroClass {
    private static final int HEALTH_PER_LVL = 6;
    private int lvl = 0;
    private final @Getter String name  = "Воин";

    @Override
    public Hero lvlUp(Hero hero) {
        lvl++;
        hero.setMaxHealth(hero.getMaxHealth() + HEALTH_PER_LVL);
        hero.fullHeal();
        switch (lvl) {
            case 1:
                if (hero.getWeapon() == null) {
                    hero.setWeapon(SWORD);
                }
                hero.setMaxHealth(hero.getMaxHealth() + hero.getEndurance());
                hero.fullHeal();
                hero.getPerks().add(RUSH_ACTION);
                hero.setLastLvlUpBonus(RUSH_ACTION.getDescription());
                break;
            case 2:
                hero.getPerks().add(SHIELD);
                hero.setLastLvlUpBonus(SHIELD.getDescription());
                break;
            case 3:
                hero.setStrength(hero.getStrength() + 1);
                hero.setLastLvlUpBonus("Сила + 1");
                break;
        }
        return hero;
    }

    @Override
    public String getLvlUpDescription(Hero hero) {
        String result = "";
        switch (lvl + 1) {
            case 1:
                result = "Стартовое оружие: Меч\n" + "Урон оружием "
                    + SWORD.getDamage() + "\n" + RUSH_ACTION.getDescription();
                break;
            case 2:
                result = SHIELD.getDescription();
                break;
            case 3:
                result = "Сила + 1";
                break;
        }
        return result;
    }

    @Override
    public int getLvl() {
        return lvl;
    }
}
