package com.mygame.dungeon_hero.assetManger;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public final class Assets {
    private Assets() {}

    // Глобальный менеджер
    public static final AssetManager am = new AssetManager();

    public static final String BG_FOLDER = "assets/backgrounds/";

    public static String previousBg;
    public static String currentBg;

    public static Texture getOrLoadTexture(String path) {
        if (!am.isLoaded(path)) {
            am.load(path, Texture.class);
            am.finishLoadingAsset(path); // блокирующая загрузка ТОЛЬКО этого ассета
        }
        return am.get(path, Texture.class);
    }

    public static void changeBg(String bg) {
        if (currentBg != null) {
            unload(previousBg);
            previousBg = currentBg;
        }
        getOrLoadTexture(BG_FOLDER + bg);
        currentBg = BG_FOLDER + bg;
    }

    public static void initMainAtlases() {
        am.load("assets/textures/enemies.atlas", TextureAtlas.class);
        am.load("assets/textures/hero.atlas", TextureAtlas.class);
        am.load("assets/textures/misc.atlas", TextureAtlas.class);
        am.load("assets/textures/weapons.atlas", TextureAtlas.class);
    }

    public static Texture getBgTexture(String name) {
        return am.get(BG_FOLDER + name, Texture.class);
    }

    public static TextureRegion getRegion(AtlasType atlasType, String regionName) {
        if (!am.isLoaded(atlasType.getPath())) {
            throw new IllegalStateException("Атлас не загружен: " + atlasType);
        }
        TextureAtlas atlas = am.get(atlasType.getPath(), TextureAtlas.class);
        TextureRegion region = atlas.findRegion(regionName);
        if (region == null) {
            throw new IllegalArgumentException("Регион '" + regionName + "' не найден в атласе " + atlasType.getPath());
        }
        return region;
    }

    public static boolean update() { return am.update(); }  // зови каждый кадр на экране загрузки
    public static float progress() { return am.getProgress(); }
    public static void finishAll() { am.finishLoading(); }  // блокирующая — вся очередь

    public static void unload(String file) {
        if (am.isLoaded(file)) am.unload(file);
    }

    public static void dispose() { am.dispose(); }
}
