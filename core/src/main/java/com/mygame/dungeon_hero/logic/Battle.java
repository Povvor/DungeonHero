package com.mygame.dungeon_hero.logic;

import com.mygame.dungeon_hero.GameCore;
import com.mygame.dungeon_hero.characters.GameCharacter;
import com.mygame.dungeon_hero.characters.Perks;
import com.mygame.dungeon_hero.characters.wepons.DamageType;
import com.mygame.dungeon_hero.gameScreens.levels.BattleIntro;
import com.mygame.dungeon_hero.gameScreens.levels.BattleScreen;

import java.util.concurrent.ThreadLocalRandom;

public class Battle {
    private GameCharacter hero;
    private GameCharacter enemy;
    private GameCharacter attacker;
    private GameCharacter defender;
    private int turnCount;
    private final GameCore game;
    private int battleCount = 1;
    private BattleScreen battleScreen;
    private Runnable onBattleComplete;

    public Battle(GameCharacter hero, GameCharacter enemy, GameCore game, int battleCount, Runnable onBattleComplete) {
        this.hero = hero;
        this.enemy = enemy;
        this.game = game;
        battleScreen = new BattleScreen(battleCount, hero, enemy, this::turn);
        if (hero.getAgility() > enemy.getAgility()) {
            attacker = hero;
            defender = enemy;
        } else {
            attacker = enemy;
            defender = hero;
        }
        this.onBattleComplete = onBattleComplete;
    }

    public void playBattle() {
        playIntro();
    }

    public void playIntro() {
        game.setScreen(new BattleIntro(hero.getSprite(),enemy.getSprite(), this::startBattle));
    }

    public void startBattle() {
        game.setScreen(battleScreen);

    }

    public void turn() {
        int damage = 0;
        boolean isAttackSuccess = isAttackSuccess(attacker, defender);
        if (isAttackSuccess) {
            damage = attacker.getDamage() + attacker.getStrength();
            damage = calculateAttackBuffs(damage);
            damage = calculateDefenderBuffs(damage);
        }
        battleScreen.playAttack(attacker == hero, isAttackSuccess, damage, this::continueFight);
    }

    public void continueFight() {
        if (enemy.getHealth() <= 0) {
            onBattleComplete.run();
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
        GameCharacter temp = attacker;
        attacker = defender;
        defender = temp;
    }
}
