package com.mygame.dungeon_hero.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygame.dungeon_hero.characters.classes.HeroClass;
import com.mygame.dungeon_hero.characters.wepons.DamageType;
import com.mygame.dungeon_hero.characters.wepons.Weapons;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class GameCharacter {
    private @Getter @Setter String name;
    private @Getter @Setter int health;
    private @Getter @Setter int maxHealth;
    private @Getter @Setter int damage;
    private @Getter @Setter int strength;
    private @Getter @Setter int agility;
    private @Getter @Setter int endurance;
    private @Getter DamageType damageType;
    private @Getter List<Perks> perks = new ArrayList<>();
    private @Getter Weapons loot;
    private @Getter @Setter TextureRegion sprite;
    private @Getter @Setter List<HeroClass> classes;

    public GameCharacter() {
    }

    public GameCharacter(Enemies enemies) {
        this.health = enemies.getHealth();
        this.maxHealth = enemies.getHealth();
        this.damage = enemies.getDamage();
        this.strength = enemies.getStrength();
        this.agility = enemies.getAgility();
        this.endurance = enemies.getEndurance();
        this.perks = enemies.getPerks();
        this.sprite = enemies.getSprite();
        this.name = enemies.getName();
        this.loot = enemies.getLoot();
        damageType = enemies.getDamageType() == null ? DamageType.MONSTER : enemies.getDamageType();
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) {
            health = 0;
        }
    }
}
