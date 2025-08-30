package com.mygame.dungeon_hero.gameScreens.levels;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.graphics.Texture;

public class FirstLevelScreen implements Screen {

    private Stage stage;
    private SpriteBatch batch;
    private Skin skin;
    private BitmapFont font;

    public FirstLevelScreen(Skin skin) {
        this.skin = skin;
    }

    // Параметры здоровья
    private int maxHealth = 100;
    private int currentHealth = 100;

    // Индикатор здоровья в виде текста
    private Label healthLabel;

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        batch = new SpriteBatch();  // Для рисования спрайтов
        font = skin.getFont("font");  // Получаем стандартный шрифт из скина

        // Создаем текстовый индикатор здоровья
        String healthText = currentHealth + "/" + maxHealth;
        healthLabel = new Label(healthText, new Label.LabelStyle(font, null));
        healthLabel.setPosition(20, Gdx.graphics.getHeight() - 50);  // Размещение текста

        // Добавляем индикатор здоровья на сцену
        stage.addActor(healthLabel);
    }

    @Override
    public void render(float delta) {
        // Очистка экрана
        Gdx.gl.glClearColor(0, 0, 0, 1);  // Черный фон
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        // Отображение текста (здоровья)
        healthLabel.setText(currentHealth + "/" + maxHealth);

        batch.end();

        // Рисуем сцену
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));  // Обновление
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void hide() {
        stage.dispose();
        batch.dispose();
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void dispose() {}
}

