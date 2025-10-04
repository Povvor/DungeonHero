package com.mygame.dungeon_hero.logic;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.MathUtils;
import com.mygame.dungeon_hero.GameCore;
import com.mygame.dungeon_hero.characters.GameCharacter;
import com.mygame.dungeon_hero.characters.Hero;
import com.mygame.dungeon_hero.characters.Perks;
import com.mygame.dungeon_hero.characters.wepons.Weapons;
import com.mygame.dungeon_hero.gameScreens.battle.BattleIntro;
import com.mygame.dungeon_hero.gameScreens.battle.BattleScreen;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class Battle {
    private final Hero hero;
    private GameCharacter attacker;
    private GameCharacter defender;
    private int turnCount = 0;
    private int attackCounter;
    private final GameCore game;
    private final BattleScreen battleScreen;
    private final Screen battleIntroScreen;

    private boolean isBattleUnderway = true;

    @Data
    public class AttackMetadata{
        private int damage = attacker.getDamage() + attacker.getStrength();
        private List<String> attackPerksMsg = new ArrayList<>();
        private List<String> defendPerksMsg = new ArrayList<>();
        boolean isAttackSuccessful;
    }

    public Battle(Hero hero, GameCharacter enemy, GameCore game, int battleCount, Runnable onBattleComplete) {
        this.hero = hero;
        this.game = game;
        battleScreen = new BattleScreen(battleCount, hero, enemy, this::turn, onBattleComplete);
        battleIntroScreen = new BattleIntro(hero.getSprite(), enemy.getSprite(), this::startBattle);
        if (hero.getAgility() > enemy.getAgility()) {
            attacker = hero;
            defender = enemy;
        } else {
            attacker = enemy;
            defender = hero;
        }
    }

    public void playIntro() {
        game.setScreen(battleIntroScreen);
    }

    public void startBattle() {
        game.setScreen(battleScreen);
    }

    public void turn() {
        if (attackCounter % 2 == 0){
            turnCount++;
        }
        boolean isAttackSuccess = isAttackSuccess(attacker, defender);
        AttackMetadata attackMetadata = new AttackMetadata();
        if (isAttackSuccess) {
            attackMetadata.isAttackSuccessful = true;
            applyAttackBuffs(attackMetadata);
            applyDefenderBuffs(attackMetadata);
            attackMetadata.setDamage(Math.max(attackMetadata.getDamage(), 0));
        }
        battleScreen.playAttack(attacker == hero, attackMetadata, this::continueFight);
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
        int random = MathUtils.random(1, attacker.getAgility() + defender.getAgility() + 1);
        return random > defender.getAgility();
    }

    private void applyAttackBuffs(AttackMetadata attackMetadata) {
        for (Perks perk : attacker.getPerks()) {
            switch (perk) {
                case FURY:
                    if(turnCount <= 3) {
                        attackMetadata.damage += 2;
                        attackMetadata.attackPerksMsg.add("Ярость + 2 урона!!!");
                    } else {
                        attackMetadata.damage -= 1;
                        attackMetadata.attackPerksMsg.add("Ярость -1 урон");
                    }

                    break;
                case POISON:
                    attackMetadata.damage += turnCount - 1;
                    attackMetadata.attackPerksMsg.add("Яд + " + (turnCount - 1));
                    break;
                case RUSH_ACTION:
                    if (turnCount == 1) {
                        attackMetadata.attackPerksMsg.add("Порыв к Действию: урон + " + attacker.getDamage());
                        attackMetadata.damage += hero.getDamage();
                    }
                    break;
                case SNEAK_ATTACK:
                    if(attacker.getAgility() > defender.getAgility()) {
                        attackMetadata.damage += 1;
                        attackMetadata.attackPerksMsg.add("Скрытая атака + 1 Урон");
                    }
                    break;
                case FIRE_BREATH:
                    if (turnCount % 3 == 0) {
                        attackMetadata.attackPerksMsg.add("Огненное дыхание: + 3 урона");
                        attackMetadata.damage += 3;
                    }
            }
        }
    }

    private void applyDefenderBuffs(AttackMetadata attackMetadata) {
        for (Perks perk : defender.getPerks()) {
            switch (perk) {
                case SHIELD:
                    if(defender.getStrength() > attacker.getStrength()) {
                        attackMetadata.damage -= 3;
                        attackMetadata.defendPerksMsg.add("Щит! Урон -3");
                    }
                    break;
                case STONE_SKIN:
                    attackMetadata.defendPerksMsg.add("Каменная кожа! урон -" + defender.getEndurance());
                    attackMetadata.damage -= defender.getEndurance();
                    break;
                case SLASH_IMMUNITY:
                    if (attacker.getDamageType() == Weapons.DamageType.SLASHING) {
                        attackMetadata.damage -= attacker.getDamage();
                        attackMetadata.defendPerksMsg.add("Невосприимчивость к рубящему урону!");
                    }
                    break;
                case BLUDGE_WEAKNESS:
                    if (attacker.getDamageType() == Weapons.DamageType.BLUDGE) {
                        attackMetadata.damage *= 2;
                        System.out.println("Уязвимость к дробящему урону: Двойной урон!");
                    }
                    break;
            }
        }
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
        battleScreen.dispose();
        battleIntroScreen.dispose();
    }
}
