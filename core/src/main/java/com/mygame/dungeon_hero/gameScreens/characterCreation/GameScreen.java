package com.mygame.dungeon_hero.gameScreens.characterCreation;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygame.dungeon_hero.Main;
import com.mygame.dungeon_hero.gameScreens.UIManager;
import com.mygame.dungeon_hero.gameScreens.mainMenu.MainMenuButtonPanel;

// Ваши классы логики, например, GameLogic или что-то подобное
// import your.game.logic.GameLogic;

public class GameScreen implements Screen {
    private Stage stage;
    private final Game game;
    // Ваша игровая логика (инициализируйте здесь)
    // private GameLogic gameLogic;

    public GameScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        CharCreateButtons buttons = new CharCreateButtons(UIManager.getSkin(), game);
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        Table table = buttons.getPanel();
        table.setFillParent(true);
        stage.addActor(table);
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
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {
        stage.dispose();
        UIManager.dispose();  // Освобождаем ресурсы UIManager
    }

    @Override
    public void dispose() {
        stage.dispose();
        // gameLogic.dispose(); // Если логика имеет ресурсы для очистки
    }
}
