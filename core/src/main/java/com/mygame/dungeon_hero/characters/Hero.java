package com.mygame.dungeon_hero.characters;

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


    public Hero(HeroClass heroClass) {
        this.setStrength(random.nextInt(1,4));
        this.setAgility(random.nextInt(1,4));
        this.setEndurance(random.nextInt(1,4));
        heroClass.lvlUp(this);
        this.maxHealth = this.getHealth();
        this.setDamage(weapon.getDamage());
        classes = Arrays.asList(new Bandit(), new Barbarian(), new Warior());
        setName("Hero");
        level++;
    }
}
