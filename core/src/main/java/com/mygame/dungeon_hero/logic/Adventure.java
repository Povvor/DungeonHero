package com.mygame.dungeon_hero.logic;

import com.mygame.dungeon_hero.GameCore;
import com.mygame.dungeon_hero.characters.Character;
import com.mygame.dungeon_hero.characters.Enemies;
import com.mygame.dungeon_hero.characters.Hero;

import java.util.concurrent.ThreadLocalRandom;

public class Adventure {
    private Hero hero;
    private GameCore game;

    public Adventure(Hero hero, GameCore game) {
        this.hero = hero;
        this.game = game;
    }

    public void startAdventure() {
        Enemies[] enemies = Enemies.values();
        for (int i = 0; i < 5; i++) {
            Enemies randomEnemy = enemies[ThreadLocalRandom.current().nextInt(0, enemies.length)];
            Character enemy = new Character(randomEnemy);
            Fight fight = new Fight(hero, enemy, game);
            fight.playBattle();
            if (hero.getHealth() <= 0) {
                System.out.println("You're out of hero.");
                return;
            }
            hero.setHealth(hero.getMaxHealth());
            System.out.println("You Win in a bsttle ");
            if(hero.getLevel() > 3) {
                System.out.println("Choose Level Up 1-Bandit, 2-Warrior, 3-Barbarian");
            }
        }
        System.out.println("You are true hero");
    }
}
