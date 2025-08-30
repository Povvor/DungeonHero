package com.mygame.dungeon_hero.gameScreens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

// Ваши классы логики, например, GameLogic или что-то подобное
// import your.game.logic.GameLogic;

public class GameScreen implements Screen {
    private Stage stage;
    private Skin skin;
    // Ваша игровая логика (инициализируйте здесь)
    // private GameLogic gameLogic;

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        skin = new Skin(Gdx.files.internal("ui/uiskin.json")); // Или ваша кастомная скин

        // Инициализация вашей логики
        // gameLogic = new GameLogic(); // Здесь запустите вашу консольную логику, но адаптированную

        // Создайте UI: таблица для布局
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        // Пример: лейбл выбор класса
        Label statusLabel = new Label("Chose your class!", skin);


        // Кнопки классов
        TextButton banditButton = new TextButton("Bandit", skin);
        banditButton.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
            }
        });


        TextButton warriorButton = new TextButton("Warior", skin);
        warriorButton.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
            }
        });

        TextButton barbarianButton = new TextButton("Barbarian", skin);
        barbarianButton.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {

            }
        });

        table.add(statusLabel).pad(10);
        table.row();
        table.add(banditButton).pad(10);
        table.row();
        table.add(warriorButton).pad(10);
        table.row();
        table.add(barbarianButton).pad(10);
        // Добавьте другие UI-элементы: кнопки для действий, изображения юнитов, текст для логов боя и т.д.
        // Например, если автобатлер, добавьте лейблы для HP, атак и т.д.
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Обновите логику игры (например, симулируйте ходы автобатлера)
        // gameLogic.update(delta); // Здесь вызывайте обновление состояния

        // Обновите UI на основе состояния (например, обновите текст лейблов)
        // statusLabel.setText(gameLogic.getCurrentStatus());

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        if (width <= 0 || height <= 0) return;
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
        // gameLogic.dispose(); // Если логика имеет ресурсы для очистки
    }
}
