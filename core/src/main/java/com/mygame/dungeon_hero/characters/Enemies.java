package com.mygame.dungeon_hero.characters;

import com.badlogic.gdx.graphics.Texture;
import com.mygame.dungeon_hero.assetManger.AssetManager;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Getter
@RequiredArgsConstructor
public enum Enemies {
    GOBLIN("Goblin", 5, 2 ,1, 1, 1, AssetManager.getEnemySprites()[0]),
    SKELETON("Skeleton", 10, 2 ,2, 2, 1, AssetManager.getEnemySprites()[1], Perks.BLUDGEONING_WEAKNESS),
    SLIME("Slime",8, 1 ,1, 1, 1, AssetManager.getEnemySprites()[2], Perks.SLASH_IMMUNITY),
    GHOST("Ghost",6, 3 ,1, 3, 1, AssetManager.getEnemySprites()[3], Perks.SNEAK_ATTACK),
    GOLEM("Golem", 10, 1 ,3, 1, 3, AssetManager.getEnemySprites()[4], Perks.STONE_SKIN),
    DRAGON("Dragon",20, 4 ,3, 3, 3, AssetManager.getEnemySprites()[5], Perks.FIRE_BREATH);

    private String name;
    private int health;
    private int damage;
    private int strength;
    private int agility;
    private int endurance;
    private List<Perks> perks;
    private Texture sprite;

    Enemies(String name,int health, int damage, int strength, int agility, int endurance, Texture texture, Perks... perks) {
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.strength = strength;
        this.agility = agility;
        this.endurance = endurance;
        this.sprite = texture;
        this.perks = Arrays.asList(perks);
    }
}
