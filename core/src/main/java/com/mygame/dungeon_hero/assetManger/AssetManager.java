package com.mygame.dungeon_hero.assetManger;

import com.badlogic.gdx.graphics.Texture;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class AssetManager {

    private final static @Getter Texture[] ENEMY_SPRITES = {
        new Texture("textures/goblin.png"),
        new Texture("textures/skeleton.png"),
        new Texture("textures/slime.png"),
        new Texture("textures/ghost.png"),
        new Texture("textures/golem.png"),
        new Texture("textures/dragon.png"),
    };

    private final static @Getter Texture[] BATTLE_BG = {
        new Texture("backgrounds/meadow.png"),
        new Texture("backgrounds/forest.png"),
        new Texture("backgrounds/darkForest.png"),
        new Texture("backgrounds/foggySwamp.png"),
        new Texture("backgrounds/bloodMoon.png"),
        new Texture("backgrounds/goblin1.png"),
        new Texture("backgrounds/goblin2.png"),
        new Texture("backgrounds/ghost1.png"),
        new Texture("backgrounds/ghost2.png"),
        new Texture("backgrounds/skeleton1.png"),
        new Texture("backgrounds/skeleton2.png"),
        new Texture("backgrounds/golem1.png"),
        new Texture("backgrounds/golem2.png"),
        new Texture("backgrounds/slime1.png"),
        new Texture("backgrounds/slime2.png"),
        new Texture("backgrounds/dragon1.png"),
        new Texture("backgrounds/dragon2.png"),
    };

    private final static @Getter Texture[] WEAPON_ICON = {
        new Texture("icons/sword.png"),
        new Texture("icons/club.png"),
        new Texture("icons/dagger.png"),
        new Texture("icons/axe.png"),
        new Texture("icons/spear.png"),
        new Texture("icons/legendary.png"),
    };

    private final static @Getter Texture HEART_ICON = new Texture("textures/heart.png");

    private final static @Getter Texture MAIN_MENU_BG = new Texture("textures/mainMenuBg.png");

    private final static @Getter Texture VS_LABEL = new Texture("textures/vsLabel.png");

    private final static @Getter Map<String, Texture> HERO_SPRITES = new HashMap<>();


    public static void init() {
        HERO_SPRITES.put("001", new Texture("skin/001.png"));
        HERO_SPRITES.put("010", new Texture("skin/010.png"));
        HERO_SPRITES.put("100", new Texture("skin/100.png"));
        HERO_SPRITES.put("002", new Texture("skin/002.png"));
        HERO_SPRITES.put("020", new Texture("skin/020.png"));
        HERO_SPRITES.put("200", new Texture("skin/200.png"));
        HERO_SPRITES.put("011", new Texture("skin/011.png"));
        HERO_SPRITES.put("101", new Texture("skin/101.png"));
        HERO_SPRITES.put("110", new Texture("skin/110.png"));
        HERO_SPRITES.put("003", new Texture("skin/003.png"));
        HERO_SPRITES.put("030", new Texture("skin/030.png"));
        HERO_SPRITES.put("300", new Texture("skin/300.png"));
        HERO_SPRITES.put("012", new Texture("skin/012.png"));
        HERO_SPRITES.put("102", new Texture("skin/102.png"));
        HERO_SPRITES.put("021", new Texture("skin/021.png"));
        HERO_SPRITES.put("120", new Texture("skin/120.png"));
        HERO_SPRITES.put("201", new Texture("skin/201.png"));
        HERO_SPRITES.put("210", new Texture("skin/210.png"));
        HERO_SPRITES.put("111", new Texture("skin/111.png"));
    }


    public static Texture findHeroSprite(String id) {
        for (String string : HERO_SPRITES.keySet()) {
            if (string.equals(id)) {
                return HERO_SPRITES.get(string);
            }
        }
        return null;
    }

    public void dispose(){
        for (Texture texture : ENEMY_SPRITES) {
            texture.dispose();
        }
        for (Texture texture : HERO_SPRITES.values()) {
            texture.dispose();
        }
    }
}
