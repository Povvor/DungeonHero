package com.mygame.dungeon_hero.characters.wepons;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static com.mygame.dungeon_hero.characters.wepons.DamageType.*;

@Getter
@RequiredArgsConstructor
public enum Weapons {
    SWORD(3, SLASHING),
    CLUB(3, BLUDGEONING),
    DAGGER(2, PIERCING),
    AXE(4, SLASHING),
    SPEAR(3, PIERCING),
    LEGENDARY(10, SLASHING),;
    private final int damage;
    private final DamageType damageType;
}
