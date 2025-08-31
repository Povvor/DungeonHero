package com.mygame.dungeon_hero.logic;

import com.badlogic.gdx.Game;
import com.mygame.dungeon_hero.GameCore;
import com.mygame.dungeon_hero.characters.Character;
import com.mygame.dungeon_hero.characters.Perks;
import com.mygame.dungeon_hero.characters.wepons.DamageType;
import com.mygame.dungeon_hero.gameScreens.levels.BattleIntro;
import com.mygame.dungeon_hero.gameScreens.levels.BattleScreen;

import java.util.concurrent.ThreadLocalRandom;

public class Fight {
    private Character hero;
    private Character enemy;
    private Character attacker;
    private Character defender;
    private int turnCount;
    private final GameCore game;

    public Fight(Character hero, Character enemy, GameCore game) {
        this.hero = hero;
        this.enemy = enemy;
        this.game = game;
    }

    public void playBattle() {
        playIntro();
        if (hero.getAgility() >= enemy.getAgility()) {
            System.out.println("First attacking hero!");
            attacker = hero;
            defender = enemy;
        } else {
            System.out.println("First attacking " + enemy.getName() + "!");
            attacker = enemy;
            defender = hero;
        }
        while (hero.getHealth() > 0 && enemy.getHealth() > 0) {
            turn();
            turnCount++;
        }
    }

    public void playIntro() {
        game.setScreen(new BattleIntro(hero.getSprite(),enemy.getSprite(), this::startBattle));
    }

    public void startBattle() {

    }

    public void turn() {
        int damage = 0;
        if (isAttackSuccess(attacker, defender)) {
            damage = attacker.getDamage();
            damage = calculateAttackBuffs(damage);
            damage = calculateDefenderBuffs(damage);
            defender.takeDamage(damage);
        }
        shiftCharRoles();
    }

    public boolean isAttackSuccess(Character attacker, Character defender) {
        int random = ThreadLocalRandom.current().nextInt(1, attacker.getAgility() + defender.getAgility());
        return random > defender.getAgility();
    }

    private int calculateAttackBuffs(int damage) {
        for (Perks perk : attacker.getPerks()) {
            switch (perk) {
                case FURY:
                    if(turnCount <= 3) {
                        damage += 2;
                    } else {
                        damage -= 1;
                    }
                    break;
                case POISON:
                    damage += turnCount - 1;
                    break;
                case RUSH_ACTION:
                    damage *= 2;
                    break;
                case SNEAK_ATTACK:
                    if(attacker.getAgility() > defender.getAgility()) {
                        damage += 1;
                    }
                    break;
                case FIRE_BREATH:
                    if (turnCount % 3 == 0) {
                        damage += 3;
                    }
            }
        }
        return damage;
    }

    private int calculateDefenderBuffs(int damage) {
        for (Perks perk : defender.getPerks()) {
            switch (perk) {
                case SHIELD:
                    if(defender.getStrength() > attacker.getStrength()) {
                        damage -= 3;
                    }
                    break;
                case STONE_SKIN:
                    damage -= defender.getEndurance();
                    break;
                case SLASH_IMMUNITY:
                    if (attacker.getDamageType() == DamageType.SLASHING) {
                        damage -= attacker.getDamage();
                    }
                    break;
                case BLUDGEONING_WEAKNESS:
                    damage *= 2;
                    break;
            }
        }
        return damage;
    }

    private void shiftCharRoles() {
        Character temp = attacker;
        attacker = defender;
        defender = temp;
    }



}
