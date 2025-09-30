package com.mygame.dungeon_hero.characters.wepons;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygame.dungeon_hero.assetManger.Assets;
import com.mygame.dungeon_hero.assetManger.AtlasType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static com.mygame.dungeon_hero.characters.wepons.DamageType.*;

@Getter
@RequiredArgsConstructor
public enum Weapons {
    SWORD("Меч",3, SLASHING, Assets.getRegion(AtlasType.WEAPON, "sword")),
    CLUB("Дубина", 3, BLUDGEONING, Assets.getRegion(AtlasType.WEAPON, "club")),
    DAGGER("Кинжал",2, PIERCING, Assets.getRegion(AtlasType.WEAPON, "dagger")),
    AXE("Топор",4, SLASHING, Assets.getRegion(AtlasType.WEAPON, "axe")),
    SPEAR("Копье",3, PIERCING, Assets.getRegion(AtlasType.WEAPON, "spear")),
    LEGENDARY("Легендарный меч",10, SLASHING, Assets.getRegion(AtlasType.WEAPON, "legendary"));
    private final String name;
    private final int damage;
    private final DamageType damageType;
    private final TextureRegion sprite;
}
