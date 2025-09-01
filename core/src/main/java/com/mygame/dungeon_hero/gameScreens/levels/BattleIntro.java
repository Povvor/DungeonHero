package com.mygame.dungeon_hero.gameScreens.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygame.dungeon_hero.assetManger.AssetManager;

public class BattleIntro implements Screen {
    private final Stage stage;
    private final Image enemyImage;       // вылетает снизу
    private final Image heroImage;// вылетает сверху
    private final Image vsLabel = new Image(AssetManager.getVslabel());
    private final Runnable onDone;       // колбэк по завершении (может быть null)

    public BattleIntro(Texture heroTexture, Texture enemyTexture, Runnable onDone) {
        this.stage = new Stage(new ScreenViewport());
        this.enemyImage = new Image(enemyTexture); // можно прямо Texture
        this.heroImage = new Image(heroTexture);
        this.onDone = onDone;
        stage.addActor(this.enemyImage);
        stage.addActor(this.heroImage);
        stage.addActor(this.vsLabel);
        vsLabel.getColor().a = 0f;
    }

    @Override public void show() {
        Gdx.input.setInputProcessor(stage);
        layoutAndAnimate();
    }

    private void layoutAndAnimate() {
        float W = stage.getViewport().getWorldWidth();
        float H = stage.getViewport().getWorldHeight();
        vsLabel.setPosition(W / 2, H / 2, Align.center);

        // целевые позиции (по центру X)
        float targetXHero = W  * 0.25f;
        float targetXEnemy = W * 0.75f;
        float targetY = H * 0.5f;

        // стартовые — за краями экрана
        enemyImage.setPosition(targetXEnemy, -enemyImage.getHeight() - 4000f, Align.center);
        heroImage.setPosition(targetXHero, H + 4000f, Align.center);

        // тайминг: 1.4 + 0.2 + 1.4 = 3.0 сек
        float d1 = 2f;

        heroImage.addAction(Actions.moveToAligned(targetXHero, targetY, Align.center, 2f, Interpolation.pow3Out));
        enemyImage.addAction(Actions.sequence(
            Actions.delay(2f),
            Actions.moveToAligned(targetXEnemy, targetY, Align.center, 2f, Interpolation.pow3Out)
        ));

        vsLabel.addAction(Actions.sequence(
            Actions.delay(4.5f),
            Actions.fadeIn(0.5f)
        ));

        stage.addAction(Actions.sequence(
            Actions.delay(6f),
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
        stage.getViewport().update(width, height, true);
    }

    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void dispose() {
        stage.dispose();
    }
}



