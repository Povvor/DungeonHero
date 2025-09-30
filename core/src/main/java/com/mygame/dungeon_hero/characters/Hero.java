package com.mygame.dungeon_hero.characters;

import com.badlogic.gdx.math.MathUtils;
import com.mygame.dungeon_hero.assetManger.Assets;
import com.mygame.dungeon_hero.assetManger.AtlasType;
import com.mygame.dungeon_hero.characters.classes.Bandit;
import com.mygame.dungeon_hero.characters.classes.Barbarian;
import com.mygame.dungeon_hero.characters.classes.HeroClass;
import com.mygame.dungeon_hero.characters.classes.Warior;
import com.mygame.dungeon_hero.characters.wepons.Weapons;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

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
        this.setDamage(weapon.getDamage());
        this.setMaxHealth(this.getHealth());
        updateHeroSprite();
        setName("Hero");
        level++;
    }

    public String getHeroInfo() {
        return "Персонаж: " + getName() + "\n" +
            getClassInfo() +
            "Здоровье: " + getHealth() + "\n" +
            "Атака оружием: " + getDamage() + "\n" +
            "Сила: "  + getStrength() + "\n" +
            "Ловкость: " + getAgility() + "\n" +
            "Выносливость " + getEndurance();
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
        setSprite(Assets.getRegion(AtlasType.HERO, builder.toString()));
    }

    public String getClassInfo() {
        StringBuilder output = new StringBuilder();
        for (HeroClass hc : getClasses()) {
            if (hc instanceof Bandit && ((Bandit) hc).getLvl() > 0) {
                output.append(((Bandit) hc).getName()).append("  ").append(((Bandit) hc).getLvl()).append(" Уровень").append("\n");
            }
            if (hc instanceof Warior && ((Warior) hc).getLvl() > 0) {
                output.append(((Warior) hc).getName()).append("  ").append(((Warior) hc).getLvl()).append(" Уровень").append("\n");
            }
            if (hc instanceof Barbarian && ((Barbarian) hc).getLvl() > 0) {
                output.append(((Barbarian) hc).getName()).append("  ").append(((Barbarian) hc).getLvl()).append(" Уровень").append("\n");
            }
        }
        return output.toString();
    }
}
