package com.mygame.dungeon_hero.gameScreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygame.dungeon_hero.ssetsManagers.TextureManager;
import com.mygame.dungeon_hero.ssetsManagers.UIManager;

public class CreditsScreen implements Screen {
    private Stage stage;
    private final Runnable exitToMenu;

    public CreditsScreen(Runnable complete) {
        exitToMenu = complete;
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        float W = stage.getViewport().getScreenWidth();
        float H = stage.getViewport().getScreenHeight();

        TextureManager.changeBg("completeGame.png");
        TextureManager.finishAll();
        Image background = new Image(TextureManager.getBgTexture("completeGame.png"));

        String credits = Gdx.files.internal("assets/fonts/credits.txt").readString();
        Label creditsLabel = new Label(credits, UIManager.getSkin(), "label");
        creditsLabel.setAlignment(Align.center);
        creditsLabel.setPosition(W / 2, H / 10, Align.bottom);

        TextButton backButton = new TextButton("Вернутся в меню!", UIManager.getSkin(), "big");
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                exitToMenu.run();
            }
        });
        backButton.align(Align.center);
        backButton.setPosition(W / 2, 0, Align.bottom);



        background.setFillParent(true);
        background.setScaling(Scaling.fill);
        stage.addActor(background);
        stage.addActor(creditsLabel);
        stage.addActor(backButton);
    }

    @Override
    public void render(float delta) {
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 60f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void dispose() {
        stage.dispose();

    }
}
