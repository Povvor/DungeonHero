package com.mygame.dungeon_hero;

import com.mygame.dungeon_hero.characters.Character;
import com.mygame.dungeon_hero.characters.Perks;
import com.mygame.dungeon_hero.characters.wepons.DamageType;

import java.util.concurrent.ThreadLocalRandom;

public class Fight {
    private Character hero;
    private Character enemy;
    private Character attacker;
    private Character defender;
    private int turnCount;

    public Fight(Character hero, Character enemy) {
        this.hero = hero;
        this.enemy = enemy;
    }

    public void playBattle() {
        System.out.println("Battle started. Your face " + enemy.getName());
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
