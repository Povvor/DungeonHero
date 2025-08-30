package com.mygame.dungeon_hero;

import com.mygame.dungeon_hero.characters.Character;
import com.mygame.dungeon_hero.characters.Enemies;
import com.mygame.dungeon_hero.characters.Hero;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Adventure {
    private Hero hero;

    public Adventure(Hero hero) {
        this.hero = hero;
    }

    public void startAdventure() {
        Scanner sc = new Scanner(System.in);
        Enemies[] enemies = Enemies.values();
        for (int i = 0; i < 5; i++) {
            Enemies randomEnemy = enemies[ThreadLocalRandom.current().nextInt(0, enemies.length)];
            Character enemy = new Character(randomEnemy);
            Fight fight = new Fight(hero, enemy);
            fight.playBattle();
            if (hero.getHealth() <= 0) {
                System.out.println("You're out of hero.");
                return;
            }
            hero.setHealth(hero.getMaxHealth());
            System.out.println("You Win in a bsttle ");
            if(hero.getLevel() > 3) {
                System.out.println("Choose Level Up 1-Bandit, 2-Warrior, 3-Barbarian");
                hero = hero.getClasses().get(sc.nextInt()).lvlUp(hero);
            }
        }
        System.out.println("You are true hero");
    }
}
