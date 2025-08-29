package com.mygame.dungeon_hero.characters;

import com.mygame.dungeon_hero.characters.classes.HeroClass;
import com.mygame.dungeon_hero.characters.wepons.Weapons;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Hero extends Character {
    private @Getter @Setter Weapons weapon;
    private static Random random = new Random();
    private List<HeroClass> classes = new ArrayList<>();


    public Hero(HeroClass heroClass) {
        this.setStrength(random.nextInt(1,4));
        this.setAgility(random.nextInt(1,4));
        this.setEndurance(random.nextInt(1,4));
        heroClass.lvlUp(this);
    }
}
