package com.mygame.dungeon_hero.logic;

import com.mygame.dungeon_hero.GameCore;
import com.mygame.dungeon_hero.characters.GameCharacter;
import com.mygame.dungeon_hero.characters.Hero;
import com.mygame.dungeon_hero.characters.Perks;
import com.mygame.dungeon_hero.characters.wepons.DamageType;
import com.mygame.dungeon_hero.characters.wepons.Weapons;
import com.mygame.dungeon_hero.gameScreens.battle.BattleIntro;
import com.mygame.dungeon_hero.gameScreens.battle.BattleScreen;

import java.util.concurrent.ThreadLocalRandom;

public class Battle {
    private Hero hero;
    private GameCharacter enemy;
    private GameCharacter attacker;
    private GameCharacter defender;
    private int turnCount = 0;
    private int attackCounter;
    private final GameCore game;
    private int battleCount = 1;
    private BattleScreen battleScreen;
    private boolean isBattleUnderway = true;

    public Battle(Hero hero, GameCharacter enemy, GameCore game, int battleCount, Runnable onBattleComplete) {
        this.hero = hero;
        this.enemy = enemy;
        this.game = game;
        battleScreen = new BattleScreen(battleCount, hero, enemy, this::turn, onBattleComplete);
        if (hero.getAgility() > enemy.getAgility()) {
            attacker = hero;
            defender = enemy;
        } else {
            attacker = enemy;
            defender = hero;
        }
        System.out.println(hero.getPerks());
    }


    public void playIntro() {
        game.setScreen(new BattleIntro(hero.getSprite(),enemy.getSprite(), this::startBattle));
    }

    public void startBattle() {
        game.setScreen(battleScreen);
    }

    public void turn() {
        System.out.println(attackCounter);
        if (attackCounter % 2 == 0){
            turnCount++;
        }
        System.out.println(turnCount);
        int damage = 0;
        boolean isAttackSuccess = isAttackSuccess(attacker, defender);
        if (isAttackSuccess) {
            damage = attacker.getDamage() + attacker.getStrength();
            damage = calculateAttackBuffs(damage);
            damage = calculateDefenderBuffs(damage);
        }
        battleScreen.playAttack(attacker == hero, isAttackSuccess, damage, this::continueFight);
        attackCounter++;
    }

    public void continueFight() {
        if (!isBattleUnderway) {
            return;
        }
        if (defender.getHealth() <= 0) {
            finishBattle(hero.getHealth() > 0);
            isBattleUnderway = false;
            return;
        }
        shiftCharRoles();
        turn();
    }

    public boolean isAttackSuccess(GameCharacter attacker, GameCharacter defender) {
        int random = ThreadLocalRandom.current().nextInt(1, attacker.getAgility() + defender.getAgility() + 1);
        return random > defender.getAgility();
    }

    private int calculateAttackBuffs(int damage) {
        for (Perks perk : attacker.getPerks()) {
            switch (perk) {
                case FURY:
                    if(turnCount <= 3) {
                        damage += 2;
                        System.out.println("Fury buff");
                    } else {
                        damage -= 1;
                        System.out.println("Fury debuff");
                    }

                    break;
                case POISON:
                    damage += turnCount - 1;
                    System.out.println("poison" + (turnCount - 1));
                    break;
                case RUSH_ACTION:
                    if (turnCount == 1) {
                        System.out.println("Rush Action");
                        damage *= 2;
                    }
                    break;
                case SNEAK_ATTACK:
                    if(attacker.getAgility() > defender.getAgility()) {
                        damage += 1;
                        System.out.println("Sneak");
                    }
                    break;
                case FIRE_BREATH:
                    if (turnCount % 3 == 0) {
                        System.out.println("Fire Breath");
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
                        System.out.println("Shield");
                    }
                    break;
                case STONE_SKIN:
                    System.out.println("Stone Skin");
                    damage -= defender.getEndurance();
                    break;
                case SLASH_IMMUNITY:

                    if (attacker.getDamageType() == DamageType.SLASHING) {
                        damage -= attacker.getDamage();
                        System.out.println("Slash Immunity");
                    }
                    break;
                case BLUDGEONING_WEAKNESS:

                    if (attacker.getDamageType() == DamageType.BLUDGEONING) {
                        damage *= 2;
                        System.out.println("Bludgeoning Weakness");
                    }
                    break;
            }
        }
        return damage;
    }

    private void shiftCharRoles() {
        GameCharacter temp = attacker;
        attacker = defender;
        defender = temp;
    }

    private void finishBattle(boolean isHeroWin) {
        if (isHeroWin) {
            battleScreen.animateWinScreen();
        } else {
            battleScreen.animateLoseScreen(this::returnToMenu);
        }
    }

    private void returnToMenu() {
        game.restartGame();
    }

    private void changeWeapon(Weapons weapon) {
        hero.setWeapon(weapon);
    }
}
