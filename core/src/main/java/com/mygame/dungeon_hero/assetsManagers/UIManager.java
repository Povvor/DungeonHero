package com.mygame.dungeon_hero.assetsManagers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import lombok.Getter;

public class UIManager {
    @Getter
    private static Skin skin;
    @Getter
    private static BitmapFont font;

    public static void init() {
        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/ofont.ru_Glina Script.ttf"));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();

        parameter.size = Math.round(Gdx.graphics.getHeight() * 0.022f);

        parameter.characters = FreeTypeFontGenerator.DEFAULT_CHARS
            + "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ"
            + "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";

        parameter.borderWidth = Math.max(1f, parameter.size * 0.06f);
        parameter.borderColor = Color.BLACK;
        parameter.borderStraight = true;
        font = generator.generateFont(parameter);
        parameter.color = Color.RED;
        parameter.borderColor = Color.WHITE;
        BitmapFont redFont = generator.generateFont(parameter);

        generator.dispose();

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle(skin.get(TextButton.TextButtonStyle.class));
        Label.LabelStyle whiteLabel = new Label.LabelStyle(skin.get(Label.LabelStyle.class));
        Label.LabelStyle redLabel = new Label.LabelStyle(skin.get(Label.LabelStyle.class));
        style.font = font;
        whiteLabel.font = font;
        skin.add("big", style);
        skin.add("font", font);
        skin.add("label", whiteLabel);
        redLabel.font = redFont;
        skin.add("redLabel", redLabel);
    }

    public static void dispose() {
        skin.dispose();
        font.dispose();
    }
}
