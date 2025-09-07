package com.mygame.dungeon_hero.assetManger;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AtlasType {
    ENEMY("assets/textures/enemies.atlas"),
    WEAPON("assets/textures/weapons.atlas"),
    MISC("assets/textures/misc.atlas"),
    HERO("assets/textures/hero.atlas"),;

    private String path;

    AtlasType(String path) {
        this.path = path;
    }
}
