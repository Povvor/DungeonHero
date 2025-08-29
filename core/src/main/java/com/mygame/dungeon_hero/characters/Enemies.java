package com.mygame.dungeon_hero.characters;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Getter
@RequiredArgsConstructor
public enum Enemies {
    GOBLIN(5, 2 ,1, 1, 1),
    SKELETON(10, 2 ,2, 2, 1, Perks.BLUDGEONING_WEAKNESS),
    SLIME(8, 1 ,1, 1, 1, Perks.SLASH_IMMUNITY),
    GHOST(6, 3 ,1, 3, 1, Perks.SNEAK_ATTACK),
    GOLEM(10, 1 ,3, 1, 3, Perks.STONE_SKIN),
    DRAGON(20, 4 ,3, 3, 3, Perks.FIRE_BREATH);

    private int health;
    private int damage;
    private int strength;
    private int agility;
    private int endurance;
    private List<Perks> perks;

    Enemies(int health, int damage, int strength, int agility, int endurance, Perks... perks) {
        this.health = health;
        this.damage = damage;
        this.strength = strength;
        this.agility = agility;
        this.endurance = endurance;
        this.perks = Arrays.asList(perks);
    }
}
