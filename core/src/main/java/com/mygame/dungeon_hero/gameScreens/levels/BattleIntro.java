package com.mygame.dungeon_hero.gameScreens.levels;

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
import com.mygame.dungeon_hero.assetManger.Assets;
import com.mygame.dungeon_hero.assetManger.AtlasType;

public class BattleIntro implements Screen {
    private final Stage stage = new Stage(new ScreenViewport());
    private final Image enemyImage;        // снизу
    private final Image heroImage;         // сверху
    private final Image vsImage = new Image(Assets.getRegion(AtlasType.MISC, "vsLabel"));
    private final Runnable onDone;

    public BattleIntro(TextureRegion heroTexture, TextureRegion enemyTexture, Runnable onDone) {
        this.heroImage  = new Image(heroTexture);
        this.enemyImage = new Image(enemyTexture);
        this.onDone = onDone;

        vsImage.getColor().a = 0f;            // старт прозрачно
        vsImage.setOrigin(Align.center);
        vsImage.setScaling(Scaling.fit);

        // порядок важен: спрайты под VS
        stage.addActor(enemyImage);
        stage.addActor(heroImage);
        stage.addActor(vsImage);
    }

    @Override public void show() {
        Gdx.input.setInputProcessor(stage);
        layoutAndAnimate();
    }

    private void layoutAndAnimate() {
        float W = stage.getViewport().getWorldWidth();
        float H = stage.getViewport().getWorldHeight();

        // целевые точки: центры левой/правой половины
        float heroXPos = W * 0.30f;
        float enemyXPos = W * 0.70f;
        float centerY = H * 0.50f;

        // насколько «за экран» уводим старт (процент от высоты экрана)
        float offsetY = H * 0.12f;

        // стартовые позиции — строго по центру актёра
        heroImage.setPosition (heroXPos,  H + heroImage.getHeight() + offsetY,  Align.center); // сверху-вне
        enemyImage.setPosition(enemyXPos, -enemyImage.getHeight() - offsetY,    Align.center); // снизу-вне

        // размер «VS» — от меньшей стороны экрана
        float vsSize = Math.min(W, H) * 0.50f; // 18% от min(W,H)
        vsImage.setSize(vsSize, vsSize);
        vsImage.setPosition(W * 0.5f, H * 0.5f, Align.center);

        // тайминги (сек)
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
    @Override public void dispose() { stage.dispose(); } // текстуры освобождает ваш AssetManager
}

