package com.mygame.dungeon_hero.characters;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygame.dungeon_hero.assetManger.Assets;
import com.mygame.dungeon_hero.assetManger.AtlasType;
import com.mygame.dungeon_hero.characters.wepons.DamageType;
import com.mygame.dungeon_hero.characters.wepons.Weapons;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@RequiredArgsConstructor
public enum Enemies {
    GOBLIN("Goblin", 5, 2 ,1, 1, 1, Assets.getRegion(AtlasType.ENEMY,"goblin"), Weapons.DAGGER),
    SKELETON("Skeleton", 10, 2 ,2, 2, 1, Assets.getRegion(AtlasType.ENEMY, "skeleton"), Weapons.CLUB, Perks.BLUDGEONING_WEAKNESS),
    SLIME("Slime",8, 1 ,1, 1, 1, Assets.getRegion(AtlasType.ENEMY, "slime"), Weapons.SPEAR, Perks.SLASH_IMMUNITY),
    GHOST("Ghost",6, 3 ,1, 3, 1, Assets.getRegion(AtlasType.ENEMY, "ghost"), Weapons.SWORD, Perks.SNEAK_ATTACK),
    GOLEM("Golem", 10, 1 ,3, 1, 3, Assets.getRegion(AtlasType.ENEMY, "golem"), Weapons.AXE, Perks.STONE_SKIN),
    DRAGON("Dragon",20, 4 ,3, 3, 3, Assets.getRegion(AtlasType.ENEMY, "dragon"),Weapons.LEGENDARY, Perks.FIRE_BREATH);

    private String name;
    private int health;
    private int damage;
    private int strength;
    private int agility;
    private int endurance;
    private Weapons loot;
    private List<Perks> perks;
    private TextureRegion sprite;
    private DamageType damageType;

    Enemies(String name,int health, int damage, int strength, int agility, int endurance, TextureRegion texture, Weapons loot, Perks... perks) {
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.strength = strength;
        this.agility = agility;
        this.endurance = endurance;
        this.sprite = texture;
        this.loot = loot;
        this.perks = Arrays.asList(perks);
    }

    Enemies(String name, int health, int damage, int strength, int agility, int endurance, DamageType damageType, TextureRegion texture, Perks... perks) {
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.strength = strength;
        this.agility = agility;
        this.endurance = endurance;
        this.sprite = texture;
        this.perks = Arrays.asList(perks);
        this.damageType = damageType;
    }
}
