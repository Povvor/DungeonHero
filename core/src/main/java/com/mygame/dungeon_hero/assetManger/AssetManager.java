package com.mygame.dungeon_hero.assetManger;

import com.badlogic.gdx.graphics.Texture;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class AssetManager {

    public static @Getter Texture[] enemySprites = {
        new Texture("textures/goblin.png"),
        new Texture("textures/skeleton.png"),
        new Texture("textures/slime.png"),
        new Texture("textures/ghost.png"),
        new Texture("textures/skeleton.png"),
        new Texture("textures/dragon.png"),
    };

    public static @Getter Texture vslabel = new Texture("textures/vsLabel.png");

    public static @Getter Map<String, Texture> heroSprites = new HashMap<>();

    public static void init() {
        heroSprites.put("001", new Texture("skin/001.png"));
        heroSprites.put("010", new Texture("skin/010.png"));
        heroSprites.put("100", new Texture("skin/100.png"));
        heroSprites.put("002", new Texture("skin/002.png"));
        heroSprites.put("020", new Texture("skin/020.png"));
        heroSprites.put("200", new Texture("skin/200.png"));
        heroSprites.put("011", new Texture("skin/011.png"));
        heroSprites.put("101", new Texture("skin/101.png"));
        heroSprites.put("110", new Texture("skin/110.png"));
        heroSprites.put("003", new Texture("skin/003.png"));
        heroSprites.put("030", new Texture("skin/030.png"));
        heroSprites.put("300", new Texture("skin/300.png"));
        heroSprites.put("012", new Texture("skin/012.png"));
        heroSprites.put("102", new Texture("skin/102.png"));
        heroSprites.put("021", new Texture("skin/021.png"));
        heroSprites.put("120", new Texture("skin/120.png"));
        heroSprites.put("201", new Texture("skin/201.png"));
        heroSprites.put("210", new Texture("skin/210.png"));
        heroSprites.put("111", new Texture("skin/111.png"));
    }


    public static Texture findHeroSprite(String id) {
        for (String string : heroSprites.keySet()) {
            if (string.equals(id)) {
                return heroSprites.get(string);
            }
        }
        return null;
    }

    public void dispose(){
        for (Texture texture : enemySprites) {
            texture.dispose();
        }
        for (Texture texture : heroSprites.values()) {
            texture.dispose();
        }
    }
}
