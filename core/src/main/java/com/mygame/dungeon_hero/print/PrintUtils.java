package com.mygame.dungeon_hero.print;

import com.mygame.dungeon_hero.characters.Hero;
import com.mygame.dungeon_hero.characters.classes.HeroClass;

public class PrintUtils {
    public static void printGreetings() {
        System.out.println("Welcome to Dungeon Hero");
        System.out.println("Please select character");
        System.out.println("1-Bandit 2-Warior 3-Barbarian");
    }

    public static void printCreatedHeroInfo(Hero hero) {
        System.out.println("Ваш класс: " + hero.getClass());
        System.out.println("Strength: " + hero.getStrength() + " Agility: " + hero.getAgility() + " Endurance: " + hero.getEndurance());
    }
}
