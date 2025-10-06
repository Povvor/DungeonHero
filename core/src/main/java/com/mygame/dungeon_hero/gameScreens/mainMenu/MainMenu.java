package com.mygame.dungeon_hero.gameScreens.mainMenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygame.dungeon_hero.assetsManagers.SoundManager;
import com.mygame.dungeon_hero.assetsManagers.TextureManager;
import com.mygame.dungeon_hero.gameScreens.overlay.OverlayManager;
import com.mygame.dungeon_hero.GameCore;
import com.mygame.dungeon_hero.assetsManagers.UIManager;

public class MainMenu implements Screen {
    private final Stage stage;
    private final GameCore game;
    private final Table compendium;

    public MainMenu(GameCore game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        compendium = OverlayManager.getCompendium(stage.getViewport(), true);
        compendium.setFillParent(true);
    }

    @Override
    public void show() {
        SoundManager.loadAndPlayBgMusic("main_menu");
        TextureManager.changeBg("mainMenu.png");
        TextureManager.finishAll();
        Image background = new Image(TextureManager.getBgTexture("mainMenu.png"));
        MainMenuButtonPanel buttons = new MainMenuButtonPanel(UIManager.getSkin(),game, () -> compendium.setVisible(true), stage.getViewport());
        Gdx.input.setInputProcessor(stage);
        Table table = buttons.getPanel();
        table.setFillParent(true);
        background.setFillParent(true);
        background.setScaling(Scaling.fill);
        stage.addActor(background);
        stage.addActor(table);
        stage.addActor(compendium);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
