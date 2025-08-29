package com.mygame.dungeon_hero.characters;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Character {
    private @Getter @Setter int maxHealth;
    private @Getter @Setter int damage;
    private @Getter @Setter int strength;
    private @Getter @Setter int agility;
    private @Getter @Setter int endurance;
    private @Getter List<Perks> perks = new ArrayList<>();

    public Character() {
    }

    public Character(Enemies enemies) {
        this.maxHealth = enemies.getHealth();
        this.damage = enemies.getDamage();
        this.strength = enemies.getStrength();
        this.agility = enemies.getAgility();
        this.endurance = enemies.getEndurance();
        this.perks = enemies.getPerks();
    }
}
