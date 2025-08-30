package com.mygame.dungeon_hero.gameScreens.mainMenu;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygame.dungeon_hero.gameScreens.characterCreation.GameScreen;

public class MainMenuButtonPanel {
    private TextButton startButton;
    private TextButton exitButton;

    public MainMenuButtonPanel(Skin skin, Game game) {
        // Создаем стиль для кнопок с кастомным шрифтом

        // Создаем кнопки с этим стилем
        startButton = new TextButton("Начать игру", skin, "big");
        exitButton = new TextButton("Выйти из игры", skin, "big");

        // Добавление логики на кнопку "Начать игру"
        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Логика при нажатии на "Начать игру"
                game.setScreen(new GameScreen(game)); // Переход к экрану игры
            }
        });

        // Добавление логики на кнопку "Выйти из игры"
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Логика при нажатии на "Выйти из игры"
                System.exit(0); // Выход из приложения
            }
        });
    }

    public Table getPanel() {
        Table table = new Table();
        table.add(startButton).width(800).pad(10);
        table.row();
        table.add(exitButton).width(800).pad(10);
        return table;
    }

    public TextButton getStartButton() {
        return startButton;
    }

    public TextButton getExitButton() {
        return exitButton;
    }
}
