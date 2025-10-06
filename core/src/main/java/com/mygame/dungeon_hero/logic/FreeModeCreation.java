package com.mygame.dungeon_hero.logic;

import com.mygame.dungeon_hero.GameCore;
import com.mygame.dungeon_hero.characters.GameCharacter;
import com.mygame.dungeon_hero.characters.Hero;
import com.mygame.dungeon_hero.gameScreens.characterCreation.FreeMode;
import lombok.Setter;

public class FreeModeCreation {
    private final GameCore game;
    private Hero hero;
    @Setter
    private GameCharacter enemy;
    private final FreeMode freeMode;

    public FreeModeCreation(GameCore game) {
       this.game = game;
        freeMode = new FreeMode(this);
       game.setScreen(freeMode);
    }


    public int createOrLvlUpHero(int index) {
        if (hero == null) {
            hero  = new Hero(index);
            freeMode.setHero(hero);
            return hero.getLevel();
        }

        if (hero.getLevel() < 3) {
            hero.lvlUp(index);
        } else {
            hero.lvlUp();
        }
        freeMode.setHero(hero);
        return hero.getLevel();
    }

    public void startBattle() {
        Battle battle = new Battle(hero, enemy, game, 0, game::restartGame);
        battle.playIntro();
    }
}
