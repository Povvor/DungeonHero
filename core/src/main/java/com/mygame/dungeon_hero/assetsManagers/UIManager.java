package com.mygame.dungeon_hero.assetsManagers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
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
        initButtonSkin(font);
        skin.add("font", font);
        skin.add("label", whiteLabel);
        redLabel.font = redFont;
        skin.add("redLabel", redLabel);
    }

    private static void initButtonSkin(BitmapFont font) {
        Drawable up   = roundRect(new Color(0x9fb8bacc), 0.3f);
        Drawable over = roundRect(new Color(0xb7ccceee), 0.6f);
        Drawable down = roundRect(new Color(0x4b6366aa), 0.9f);
        TextButton.TextButtonStyle button = new TextButton.TextButtonStyle();
        button.up = up;
        button.over = over;
        button.down = down;
        button.checked = down;
        button.disabled = up;
        button.font = font;
        button.fontColor = Color.WHITE;
        button.overFontColor = Color.WHITE;
        button.downFontColor = Color.WHITE;
        button.disabledFontColor = new Color(1, 1, 1, 0.53f);
        button.pressedOffsetY = -1;
        skin.add("big", button);
    }

    private static Drawable roundRect(Color c, float a) {
        Pixmap pm = new Pixmap(32, 32, Pixmap.Format.RGBA8888);
        pm.setBlending(Pixmap.Blending.SourceOver);
        pm.setColor(c.r, c.g, c.b, a);
        pm.fillRectangle(4, 0, 24, 32);
        pm.fillRectangle(0, 4, 32, 24);
        pm.fillCircle(4, 4, 4);
        pm.fillCircle(27, 4, 4);
        pm.fillCircle(4, 27, 4);
        pm.fillCircle(27, 27, 4);
        Texture tex = new Texture(pm);
        pm.dispose();
        return new NinePatchDrawable(new NinePatch(new TextureRegion(tex), 8, 8, 8, 8));
    }

    public static void dispose() {
        skin.dispose();
        font.dispose();
    }
}
