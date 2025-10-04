package com.mygame.dungeon_hero.characters;

import com.badlogic.gdx.math.MathUtils;
import com.mygame.dungeon_hero.ssetsManagers.TextureManager;
import com.mygame.dungeon_hero.characters.heroClasses.Bandit;
import com.mygame.dungeon_hero.characters.heroClasses.Barbarian;
import com.mygame.dungeon_hero.characters.heroClasses.HeroClass;
import com.mygame.dungeon_hero.characters.heroClasses.Warior;
import com.mygame.dungeon_hero.characters.wepons.Weapons;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Hero extends GameCharacter {
    private @Getter @Setter Weapons weapon;
    private @Getter @Setter String lastLvlUpBonus;
    private @Getter int level;


    public Hero(int startClassIndex) {
        this.setStrength(MathUtils.random(1, 3));
        this.setAgility(MathUtils.random(1, 3));
        this.setEndurance(MathUtils.random(1, 3));
        this.setClasses(Arrays.asList(new Bandit(), new Warior(), new Barbarian()));
        getClasses().get(startClassIndex).lvlUp(this);
        this.setMaxHealth(this.getHealth());
        updateHeroSprite();
        setName("Герой");
        level++;
    }

    public String getHeroInfo() {
        String perksStr = this.getPerks().isEmpty()
            ? "Нет"
            : this.getPerks().stream().map(Perks::getDescription).collect(Collectors.joining(", "));
        String weaponString  = weapon.getName();
        String dmgType = getDamageType() != null ? getDamageType().getDescription() : "—";

        return String.format(
            "%s  (Уровень %d)%n" +
                "%s" +
                "ХП: %d / %d   УРН: %d [%s]%n" +
                "СИЛ: %d   ЛВК: %d   ВНЛ: %d%n" +
                "Оружие: %s%n" +
                (perksStr),
            getName(), level, getClassInfo(),
            getHealth(), getMaxHealth(), getDamage(), dmgType,
            getStrength(), getAgility(), getEndurance(),
            weaponString
        );
    }

    public void lvlUp(int classId) {
        getClasses().get(classId).lvlUp(this);
        level++;
        updateHeroSprite();
    }

    private void updateHeroSprite() {
        StringBuilder builder = new StringBuilder();
        for (HeroClass heroClass : getClasses()) {
            builder.append(heroClass.getLvl());
        }
        setSprite(TextureManager.getRegion(TextureManager.AtlasType.HERO, builder.toString()));
    }

    public String getClassInfo() {
        StringBuilder output = new StringBuilder();
        for (HeroClass heroClass : getClasses()) {
            if (heroClass instanceof Bandit && heroClass.getLvl() > 0) {
                output.append(((Bandit) heroClass).getName()).append("  ").append(heroClass.getLvl()).append(" Уровень").append("\n");
            }
            if (heroClass instanceof Warior && heroClass.getLvl() > 0) {
                output.append(((Warior) heroClass).getName()).append("  ").append(heroClass.getLvl()).append(" Уровень").append("\n");
            }
            if (heroClass instanceof Barbarian && heroClass.getLvl() > 0) {
                output.append(((Barbarian) heroClass).getName()).append("  ").append(heroClass.getLvl()).append(" Уровень").append("\n");
            }
        }
        return output.toString();
    }

    public void changeWeapon(Weapons newWeapon) {
        weapon = newWeapon;
        setDamageType(newWeapon.getDamageType());
        setDamage(newWeapon.getDamage());
    }
}
