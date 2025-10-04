package com.mygame.dungeon_hero.gameScreens.battle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygame.dungeon_hero.ssetsManagers.TextureManager;

public class BattleIntro implements Screen {
    private final Stage stage = new Stage(new ScreenViewport());
    private final Image enemyImage;
    private final Image heroImage;
    private final Image vsImage = new Image(TextureManager.getRegion(TextureManager.AtlasType.MISC, "vsLabel"));
    private final Runnable onDone;

    public BattleIntro(TextureRegion heroTexture, TextureRegion enemyTexture, Runnable onDone) {
        this.heroImage  = new Image(heroTexture);
        this.enemyImage = new Image(enemyTexture);
        this.onDone = onDone;
        float w = stage.getViewport().getScreenWidth();
        float h = stage.getViewport().getScreenHeight();

        heroImage.setSize(w / 2.4f, h / 2.4f);
        heroImage.setScaling(Scaling.fit);
        enemyImage.setSize(w / 2.4f, h / 2.4f);
        enemyImage.setScaling(Scaling.fit);

        vsImage.getColor().a = 0f;
        vsImage.setOrigin(Align.center);
        vsImage.setScaling(Scaling.fit);
        vsImage.setSize(w / 5f, h / 5f);

        stage.addActor(enemyImage);
        stage.addActor(heroImage);
        stage.addActor(vsImage);
    }

    @Override public void show() {
        Gdx.input.setInputProcessor(stage);
        layoutAndAnimate();
    }

    private void layoutAndAnimate() {
        float W = stage.getViewport().getScreenWidth();
        float H = stage.getViewport().getScreenHeight();

        float heroXPos = W * 0.25f;
        float enemyXPos = W * 0.75f;
        float centerY = H * 0.5f;

        float offsetY = H * 0.12f;

        heroImage.setPosition(heroXPos,  H + heroImage.getHeight() + offsetY, Align.center);
        enemyImage.setPosition(enemyXPos, -enemyImage.getHeight() - offsetY, Align.center);

        vsImage.setPosition(W * 0.5f, H * 0.5f, Align.center);

        float slideTime = 2.0f;
        float delayTime = 0.5f;
        float vsIn = 1f;

        heroImage.addAction(Actions.sequence(
            Actions.delay(delayTime),
            Actions.moveToAligned(heroXPos, centerY, Align.center, slideTime, Interpolation.pow3Out)
        ));
        enemyImage.addAction(Actions.sequence(
            Actions.delay(slideTime + delayTime),
            Actions.moveToAligned(enemyXPos, centerY, Align.center, slideTime, Interpolation.pow3Out)
        ));

        vsImage.setScale(0.8f);
        vsImage.addAction(Actions.sequence(
            Actions.delay(slideTime * 2f + delayTime),
            Actions.fadeIn(vsIn),
            Actions.delay(vsIn),
            Actions.fadeOut(vsIn / 2)));

        float total = slideTime * 2f + delayTime + vsIn * 2.5f;

        stage.addAction(Actions.sequence(
            Actions.delay(total),
            Actions.run(() -> { if (onDone != null) onDone.run(); })
        ));
    }

    @Override public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override public void resize(int width, int height) {
    }

    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void dispose() { stage.dispose(); }
}

