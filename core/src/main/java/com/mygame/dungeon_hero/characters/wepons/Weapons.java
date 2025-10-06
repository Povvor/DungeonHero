package com.mygame.dungeon_hero.characters.wepons;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygame.dungeon_hero.assetsManagers.TextureManager;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


import static com.mygame.dungeon_hero.characters.wepons.Weapons.DamageType.*;

@Getter
@RequiredArgsConstructor
public enum Weapons {
    SWORD("Меч", 3, SLASHING, TextureManager.getRegion(TextureManager.AtlasType.WEAPON, "sword")),
    CLUB("Дубина", 3, BLUDGE, TextureManager.getRegion(TextureManager.AtlasType.WEAPON, "club")),
    DAGGER("Кинжал", 2, PIERCING, TextureManager.getRegion(TextureManager.AtlasType.WEAPON, "dagger")),
    AXE("Топор", 4, SLASHING, TextureManager.getRegion(TextureManager.AtlasType.WEAPON, "axe")),
    SPEAR("Копье", 3, PIERCING, TextureManager.getRegion(TextureManager.AtlasType.WEAPON, "spear")),
    LEGENDARY("Легендарный меч", 10, SLASHING, TextureManager.getRegion(TextureManager.AtlasType.WEAPON, "legendary"));
    private final String name;
    private final int damage;
    private final DamageType damageType;
    private final TextureRegion sprite;

    @Getter
    @RequiredArgsConstructor
    public enum DamageType {
        SLASHING("Рубящий урон"),
        BLUDGE("Дробящий урон"),
        PIERCING("Колющий урон"),
        MONSTER("Обычный урон от монстров");
        private final String description;
    }

    public String getInfo() {
        return String.format(
            "%s %n" +
                "Урон: %d%n" +
                "Тип урона: %s",
            name, damage, damageType.getDescription()
        );
    }
}
