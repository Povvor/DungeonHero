package com.mygame.dungeon_hero.characters.wepons;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DamageType {
    SLASHING("Рубящий урон"),
    BLUDGEONING("Дробящий урон"),
    PIERCING("Колющий урон"),
    MONSTER("Обычный урон от монстров");
    private final String description;
}
