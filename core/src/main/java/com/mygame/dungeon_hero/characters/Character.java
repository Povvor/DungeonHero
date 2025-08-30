package com.mygame.dungeon_hero.characters;

import com.mygame.dungeon_hero.characters.wepons.DamageType;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Character {
    private @Getter @Setter String name;
    private @Getter @Setter int health;
    private @Getter @Setter int damage;
    private @Getter @Setter int strength;
    private @Getter @Setter int agility;
    private @Getter @Setter int endurance;
    private @Getter DamageType damageType;
    private @Getter List<Perks> perks = new ArrayList<>();

    public Character() {
    }

    public Character(Enemies enemies) {
        this.health = enemies.getHealth();
        this.damage = enemies.getDamage();
        this.strength = enemies.getStrength();
        this.agility = enemies.getAgility();
        this.endurance = enemies.getEndurance();
        this.perks = enemies.getPerks();
        damageType = DamageType.MONSTER;
    }

    public void takeDamage(int damage) {
        health -= damage;
        System.out.println(name + " Takes damage: " + damage);
        if (health < 0) {
            health = 0;
        }
    }
}
