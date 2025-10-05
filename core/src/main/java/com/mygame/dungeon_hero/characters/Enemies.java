package com.mygame.dungeon_hero.characters;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygame.dungeon_hero.assetsManagers.TextureManager;
import com.mygame.dungeon_hero.characters.wepons.Weapons;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public enum Enemies {
    GOBLIN("Goblin", 5, 2 ,1, 1, 1, TextureManager.getRegion(TextureManager.AtlasType.ENEMY,"goblin"), Weapons.DAGGER),
    SKELETON("Skeleton", 10, 2 ,2, 2, 1, TextureManager.getRegion(TextureManager.AtlasType.ENEMY, "skeleton"), Weapons.CLUB, Perks.BLUDGE_WEAKNESS),
    SLIME("Slime",8, 1 ,1, 1, 1, TextureManager.getRegion(TextureManager.AtlasType.ENEMY, "slime"), Weapons.SPEAR, Perks.SLASH_IMMUNITY),
    GHOST("Ghost",6, 3 ,1, 3, 1, TextureManager.getRegion(TextureManager.AtlasType.ENEMY, "ghost"), Weapons.SWORD, Perks.SNEAK_ATTACK),
    GOLEM("Golem", 10, 1 ,3, 1, 3, TextureManager.getRegion(TextureManager.AtlasType.ENEMY, "golem"), Weapons.AXE, Perks.STONE_SKIN),
    DRAGON("Dragon",20, 4 ,3, 3, 3, TextureManager.getRegion(TextureManager.AtlasType.ENEMY, "dragon"),Weapons.LEGENDARY, Perks.FIRE_BREATH);

    private String name;
    private int health;
    private int damage;
    private int strength;
    private int agility;
    private int endurance;
    private Weapons loot;
    private List<Perks> perks;
    private TextureRegion sprite;
    private Weapons.DamageType damageType;

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
        this.damageType = Weapons.DamageType.MONSTER;
    }

    public String getInfo() {
        String perksStr = perks.isEmpty()
            ? "Нет"
            : perks.stream().map(Perks::getDescription).collect(Collectors.joining(", "));
        String lootStr = loot != null ? loot.getName() : "—";

        return String.format(
            "%s %n" +
                "ХП: %d   УРН: %d%n" +
                "СИЛ: %d   ЛВК: %d   ВНЛ: %d%n" +
                "Дроп: %s%n" +
                "Особенности: %s",
            name,
            health, damage,
            strength, agility, endurance,
            lootStr,
            perksStr
        );
    }
}
