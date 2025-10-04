package com.mygame.dungeon_hero.uiManagers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public final class TextureManager {
    private TextureManager() {}

    public static final AssetManager am = new AssetManager();

    public static final String BG_FOLDER = "assets/backgrounds/";
    public static String previousBg;
    public static String currentBg;
    public static String winningBg = "assets/backgrounds/winbg.png";

    @Getter
    @RequiredArgsConstructor
    public enum AtlasType {
        ENEMY("assets/textures/enemies.atlas"),
        WEAPON("assets/textures/weapons.atlas"),
        MISC("assets/textures/misc.atlas"),
        HERO("assets/textures/hero.atlas");
        private final String path;
    }

    public static void loadTexture(String path) {
        if (!am.isLoaded(path)) {
            am.load(path, Texture.class);
            am.finishLoadingAsset(path);
        }
    }

    public static void changeBg(String bg) {
        if (currentBg != null) {
            unload(previousBg);
            previousBg = currentBg;
        }
        loadTexture(BG_FOLDER + bg);
        currentBg = BG_FOLDER + bg;
    }

    public static void initMainAtlases() {
        am.load("assets/textures/enemies.atlas", TextureAtlas.class);
        am.load("assets/textures/hero.atlas", TextureAtlas.class);
        am.load("assets/textures/misc.atlas", TextureAtlas.class);
        am.load("assets/textures/weapons.atlas", TextureAtlas.class);
        am.load(winningBg, Texture.class);
    }

    public static Texture getBgTexture(String name) {
        return am.get(BG_FOLDER + name, Texture.class);
    }

    public static TextureRegion getRegion(AtlasType atlasType, String regionName) {
        if (!am.isLoaded(atlasType.getPath())) {
            throw new IllegalStateException("Atlas is not loaded" + atlasType + " " + regionName);
        }
        TextureAtlas atlas = am.get(atlasType.getPath(), TextureAtlas.class);
        TextureRegion region = atlas.findRegion(regionName);
        if (region == null) {
            throw new IllegalArgumentException("Region'" + regionName + "'not found in atlas:" + atlasType.getPath());
        }
        return region;
    }

    public static void finishAll() {
        am.finishLoading();
    }

    public static void unload(String file) {
        if (am.isLoaded(file)) am.unload(file);
    }

    public static void dispose() {
        am.dispose();
    }
}
