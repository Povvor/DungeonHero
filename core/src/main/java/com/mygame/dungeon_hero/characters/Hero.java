package com.mygame.dungeon_hero.characters;

import com.mygame.dungeon_hero.assetManger.AssetManager;
import com.mygame.dungeon_hero.characters.classes.Bandit;
import com.mygame.dungeon_hero.characters.classes.Barbarian;
import com.mygame.dungeon_hero.characters.classes.HeroClass;
import com.mygame.dungeon_hero.characters.classes.Warior;
import com.mygame.dungeon_hero.characters.wepons.Weapons;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Hero extends Character {
    private @Getter @Setter Weapons weapon;
    private static Random random = new Random();
    private @Getter List<HeroClass> classes;
    private @Getter @Setter int maxHealth;
    private @Getter int level;


    public Hero(int input) {
        this.setStrength(random.nextInt(1,4));
        this.setAgility(random.nextInt(1,4));
        this.setEndurance(random.nextInt(1,4));
        classes = Arrays.asList(new Bandit(), new Warior(), new Barbarian());
        classes.get(input).lvlUp(this);
        this.setDamage(weapon.getDamage());
        this.maxHealth = this.getHealth();
        updateHeroSprite();
        setName("Hero");
        level++;
    }

    public String getHeroInfo() {
        return "Персонаж: " + getName() + "\n" +
            getClassInfo() +
            "Здоровье: " + getHealth() + "\n" +
            "Атака оружием: " + getDamage() + "\n" +
            "Сила: "  + getStrength() + "\n" +
            "Ловкость: " + getAgility() + "\n" +
            "Выносливость " + getEndurance();
    }

    private void updateHeroSprite() {
        StringBuilder sc = new StringBuilder();
        for (HeroClass heroClass : classes) {
            if (heroClass instanceof Bandit) {
                sc.append(((Bandit) heroClass).getLvl());
            }
            if (heroClass instanceof Warior) {
                sc.append(((Warior) heroClass).getLvl());
            }
            if(heroClass instanceof Barbarian) {
                sc.append(((Barbarian) heroClass).getLvl());
            }
        }
        setSprite(AssetManager.findHeroSprite(sc.toString()));
    }

    public String getClassInfo() {
        StringBuilder output = new StringBuilder();
        for (HeroClass hc : classes) {
            if (hc instanceof Bandit && ((Bandit) hc).getLvl() > 0) {
                output.append(((Bandit) hc).getName()).append("  ").append(((Bandit) hc).getLvl()).append(" Уровень").append("\n");
            }
            if (hc instanceof Warior && ((Warior) hc).getLvl() > 0) {
                output.append(((Warior) hc).getName()).append("  ").append(((Warior) hc).getLvl()).append(" Уровень").append("\n");
            }
            if (hc instanceof Barbarian && ((Barbarian) hc).getLvl() > 0) {
                output.append(((Barbarian) hc).getName()).append("  ").append(((Barbarian) hc).getLvl()).append(" Уровень").append("\n");
            }
        }
        return output.toString();
    }
}
