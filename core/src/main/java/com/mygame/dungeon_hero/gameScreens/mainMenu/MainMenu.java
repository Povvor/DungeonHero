package com.mygame.dungeon_hero.gameScreens.mainMenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygame.dungeon_hero.assetManger.AssetManager;
import com.mygame.dungeon_hero.logic.GameWorld;
import com.mygame.dungeon_hero.GameCore;
import com.mygame.dungeon_hero.gameScreens.UIManager;

public class MainMenu implements Screen {
    private Stage stage;
    private final GameCore game;
    private GameWorld gameWorld;
    private int frame;
    private Image background = new Image(AssetManager.getMAIN_MENU_BG());

    public MainMenu(GameCore game) {
        this.game = game;
    }

    @Override
    public void show() {
        MainMenuButtonPanel buttons = new MainMenuButtonPanel(UIManager.getSkin(),game);
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        Table table = buttons.getPanel();
        table.setFillParent(true);
        background.setFillParent(true);
        background.setScaling(Scaling.fill);
        stage.addActor(background);
        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        frame++;
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void hide() {
        stage.dispose();
         // Освобождаем ресурсы UIManager
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
