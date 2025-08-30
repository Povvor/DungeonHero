package com.mygame.dungeon_hero.gameScreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import lombok.Getter;

public class UIManager {
    private static Skin skin;
    private static BitmapFont bigFont;

    // Метод для инициализации шрифта и скина
    public static void initialize() {
        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));

        // Создание кастомного шрифта
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/ofont.ru_Glina Script.ttf"));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = 48;  // Устанавливаем размер шрифта
        parameter.characters = FreeTypeFontGenerator.DEFAULT_CHARS
            + "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ"
            + "абвгдеёжзийклмнопрстуфхцчшщъыьэюя"; // кириллица
        bigFont = generator.generateFont(parameter);  // Генерация шрифта
        generator.dispose();  // Освобождение ресурсов

        // Заменить стандартный шрифт в скине
        skin.getFont("default-font").dispose(); // Освобождаем стандартный шрифт
        skin.add("default-font", bigFont, BitmapFont.class); // Добавляем кастомный шрифт в скин
    }

    public static Skin getSkin() {
        return skin;
    }

    public static BitmapFont getBigFont() {
        return bigFont;
    }

    public static void dispose() {
        skin.dispose();
        bigFont.dispose();
    }
}
