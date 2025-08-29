package com.mygame.dungeon_hero;

import com.badlogic.gdx.Game;
import com.mygame.dungeon_hero.characters.Hero;
import com.mygame.dungeon_hero.characters.classes.Bandit;
import com.mygame.dungeon_hero.characters.classes.Barbarian;
import com.mygame.dungeon_hero.characters.classes.HeroClass;
import com.mygame.dungeon_hero.characters.classes.Warior;

import java.util.Scanner;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    @Override
    public void create() {
        setScreen(new FirstScreen());
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Dungeon Hero");
        System.out.println("Please select character");
        int heroClassChose = sc.nextInt();
        HeroClass heroClass = null;
        switch (heroClassChose) {
            case 1:
                heroClass = new  Bandit();
                break;
            case 2:
                heroClass = new Warior();
                break;
            case 3:
                heroClass = new Barbarian();
                break;
        }
        Hero hero = new Hero(heroClass);
        System.out.println("Hero Class: " + heroClass);
        System.out.println(hero.getWeapon() + " " + hero.getEndurance() + " " + hero.getAgility() + " " +  hero.getStrength());
    }
}
