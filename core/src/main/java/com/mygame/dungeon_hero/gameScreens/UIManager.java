package com.mygame.dungeon_hero.gameScreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygame.dungeon_hero.characters.classes.Barbarian;

public class UIManager {
    private static Skin skin;
    private static BitmapFont font;
    private static BitmapFont redFont;

    // Метод для инициализации шрифта и скина
    public static void init() {
        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));

        // Создание кастомного шрифта
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/ofont.ru_Glina Script.ttf"));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();

        parameter.size = Math.round(Gdx.graphics.getHeight() * 0.022f);  // Устанавливаем размер шрифта

        parameter.characters = FreeTypeFontGenerator.DEFAULT_CHARS
            + "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ"
            + "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
        // кириллица
        parameter.borderWidth = Math.max(1f, parameter.size * 0.06f);   // ~6% от кегля
        parameter.borderColor = Color.BLACK;
        parameter.borderStraight = true;
        font = generator.generateFont(parameter);// Генерация шрифта
        parameter.color = Color.RED;
        parameter.borderColor = Color.WHITE;
        redFont = generator.generateFont(parameter);// Генерация белого шрифта

        generator.dispose();  // Освобождение ресурсов

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle(skin.get(TextButton.TextButtonStyle.class));
        Label.LabelStyle whiteLabel = new Label.LabelStyle(skin.get(Label.LabelStyle.class));
        Label.LabelStyle redLabel = new Label.LabelStyle(skin.get(Label.LabelStyle.class));
        style.font = font;
        whiteLabel.font = font;
        skin.add("big", style); // Добавляем кастомный шрифт в скин
        skin.add("font", font);
        skin.add("label", whiteLabel);
        redLabel.font = redFont;
        skin.add("redLabel", redLabel);
    }

    public static Skin getSkin() {
        return skin;
    }

    public static BitmapFont getFont() {
        return font;
    }
    public static Label.LabelStyle getLabelStyle() {
        return skin.get(Label.LabelStyle.class);
    }

    public static void dispose() {
        skin.dispose();
        font.dispose();
    }
}
