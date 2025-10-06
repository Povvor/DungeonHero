package com.mygame.dungeon_hero.gameScreens.battle;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygame.dungeon_hero.characters.Hero;
import com.mygame.dungeon_hero.gameScreens.overlay.OverlayManager;
import com.mygame.dungeon_hero.logic.Battle;

public class PausableGroup extends Group {
    private boolean paused = false;

    public void switchPause(Stage stage, Hero hero, Battle currentBattle) {
        if (paused) {
            OverlayManager.hide();
        } else {
            OverlayManager.show(stage, hero, currentBattle);
        }
        paused = !paused;
    }

    @Override public void act(float delta) {
        if (paused) return;
        super.act(delta);
    }
}
