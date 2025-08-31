package com.mygame.dungeon_hero.gameScreens.characterCreation;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygame.dungeon_hero.GameCore;
import com.mygame.dungeon_hero.characters.Hero;
import com.mygame.dungeon_hero.gameScreens.UIManager;
import com.mygame.dungeon_hero.gameScreens.levels.BattleScreen;

public class CharCreateButtons {
    private final TextButton banditButton;
    private final TextButton wariorButton;
    private final TextButton barbarianButton;
    private final TextButton startButton;
    private final Label label;
    private final GameCore game;
    private final GameScreen screen;

    public CharCreateButtons(Skin skin, GameCore game, GameScreen screen) {
        this.game = game;
        this.screen = screen;
        // Создаем стиль для кнопок с кастомным шрифтом

        // Создаем кнопки с этим стилем

        label = new Label("Выбери класс героя:", skin, "label");
        banditButton = new TextButton("Разбойник", skin, "big");
        wariorButton = new TextButton("Воин", skin, "big");
        barbarianButton = new TextButton("Варвар", skin, "big");
        startButton = new TextButton("Начать Приключение", skin, "big");
        startButton.setVisible(false);

        // Добавление логики на кнопку "Начать игру"
        banditButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.getGameWorld().createHero(0);
                showCreatedHero();

            }
        });

        // Добавление логики на кнопку "Выйти из игры"
        wariorButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.getGameWorld().createHero(1);
                showCreatedHero();
            }
        });


        barbarianButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.getGameWorld().createHero(2);
                showCreatedHero();
            }
        });

        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.getGameWorld().startAdventure();
            }
        });
    }

    public void showCreatedHero() {
        banditButton.setVisible(false);
        wariorButton.setVisible(false);
        barbarianButton.setVisible(false);
        Hero hero = game.getGameWorld().getHero();
        String heroInfo =
            "Ваш герой создан \n" +
            hero.getHeroInfo();
        label.setText(heroInfo);
        startButton.setVisible(true);
        screen.drawHeroIcon();

    }

    public Table getPanel() {
        Table table = new Table();
        table.add(label).width(800).pad(10);
        table.row();
        table.add(banditButton).width(800).pad(10);
        table.row();
        table.add(wariorButton).width(800).pad(10);
        table.row();
        table.add(barbarianButton).width(800).pad(10);
        table.row();
        table.add(startButton).width(800).pad(10);
        return table;
    }
}

