package com.mygame.dungeon_hero.gameScreens.overlay;

import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygame.dungeon_hero.characters.Enemies;
import com.mygame.dungeon_hero.assetsManagers.UIManager;

public class MonsterInfoCard extends Stack {
    private final Image image;
    private final Label info;

    public MonsterInfoCard(Enemies enemy, Viewport viewport) {
        image = new Image(enemy.getSprite());
        image.setScaling(Scaling.fit);
        image.setAlign(Align.center);

        info = new Label(enemy.getInfo(), UIManager.getSkin(), "label");
        info.setWrap(true);
        info.setAlignment(Align.center);
        info.setVisible(false);
        info.setColor(1f,1f,1f,0f);

        float w = viewport.getWorldWidth() * 0.1f;
        float h = viewport.getWorldHeight() * 0.1f;
        setSize(w, h);

        add(image);
        add(info);

        addListener(new InputListener() {
            @Override public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                swapToInfo(true);
            }
            @Override public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                swapToInfo(false);
            }
        });

        setTransform(false);
        setTouchable(Touchable.enabled);
    }

    private void swapToInfo(boolean showInfo) {
        image.clearActions();
        info.clearActions();

        if (showInfo) {
            info.setVisible(true);
            image.addAction(Actions.fadeOut(0.15f));
            info.addAction(Actions.fadeIn(0.15f));
        } else {
            image.addAction(Actions.fadeIn(0.15f));
            info.addAction(Actions.sequence(Actions.fadeOut(0.12f), Actions.visible(false)));
        }
    }
}
