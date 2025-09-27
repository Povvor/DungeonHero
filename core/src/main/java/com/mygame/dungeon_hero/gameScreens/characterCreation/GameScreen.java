package com.mygame.dungeon_hero.gameScreens.characterCreation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygame.dungeon_hero.GameCore;
import com.mygame.dungeon_hero.assetManger.Assets;
import com.mygame.dungeon_hero.gameScreens.UIManager;

public class GameScreen implements Screen {
    public  Stage stage;
    private final GameCore game;
    private Image heroIcon;
    private Image background;

    public GameScreen(GameCore game) {
        this.game = game;
    }

    @Override
    public void show() {
        Assets.changeBg("meadow.png");
        background = new Image(Assets.getBgTexture("meadow.png"));
        CharCreateButtons buttons = new CharCreateButtons(UIManager.getSkin(), game, this);
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        background.setFillParent(true);
        background.setScaling(Scaling.fill);
        stage.addActor(background);
        Table table = buttons.getPanel();
        table.setFillParent(true);
        stage.addActor(table);
    }

    public void drawHeroIcon() {
        heroIcon = new Image(game.getGameWorld().getHero().getSprite());
        heroIcon.setSize(512, 512);
        heroIcon.setOrigin(Align.center);
        float w = stage.getViewport().getWorldWidth();
        float h = stage.getViewport().getWorldHeight();
        heroIcon.setPosition(w * (0.55f), h * (0.58f), Align.center);

        stage.addActor(heroIcon);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {
        stage.dispose();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
