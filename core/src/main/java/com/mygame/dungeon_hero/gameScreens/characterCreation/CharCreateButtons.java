package com.mygame.dungeon_hero.gameScreens.characterCreation;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class CharCreateButtons {
    private TextButton banditButton;
    private final TextButton wariorButton;
    private TextButton barbarianButton;
    public CharCreateButtons(Skin skin, Game game) {
        // Создаем стиль для кнопок с кастомным шрифтом

        // Создаем кнопки с этим стилем
        banditButton = new TextButton("Разбойник", skin, "big");
        wariorButton = new TextButton("Воин", skin, "big");
        barbarianButton = new TextButton("Варвар", skin, "big");

        // Добавление логики на кнопку "Начать игру"
        banditButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

            }
        });

        // Добавление логики на кнопку "Выйти из игры"
        wariorButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

            }
        });


        barbarianButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

            }
        });
    }

    public Table getPanel() {
        Table table = new Table();
        table.add(banditButton).width(800).pad(10);
        table.row();
        table.add(wariorButton).width(800).pad(10);
        table.row();
        table.add(barbarianButton).width(800).pad(10);
        return table;
    }
}

