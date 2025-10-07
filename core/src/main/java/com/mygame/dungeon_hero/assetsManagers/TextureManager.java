package com.mygame.dungeon_hero.assetsManagers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public final class TextureManager {
    private TextureManager() {
    }

    public static final AssetManager TEXTURE_MANAGER = new AssetManager();

    public static final String BG_FOLDER = "backgrounds/";
    public static String previousBg;
    public static String currentBg;
    public static String winningBg = "backgrounds/winbg.png";

    @Getter
    @RequiredArgsConstructor
    public enum AtlasType {
        ENEMY("textures/enemies.atlas"),
        WEAPON("textures/weapons.atlas"),
        MISC("textures/misc.atlas"),
        HERO("textures/hero.atlas");
        private final String path;
    }

    public static void loadTexture(String path) {
        if (!TEXTURE_MANAGER.isLoaded(path)) {
            TEXTURE_MANAGER.load(path, Texture.class);
            TEXTURE_MANAGER.finishLoadingAsset(path);
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
        TEXTURE_MANAGER.load("textures/enemies.atlas", TextureAtlas.class);
        TEXTURE_MANAGER.load("textures/hero.atlas", TextureAtlas.class);
        TEXTURE_MANAGER.load("textures/misc.atlas", TextureAtlas.class);
        TEXTURE_MANAGER.load("textures/weapons.atlas", TextureAtlas.class);
        TEXTURE_MANAGER.load(winningBg, Texture.class);
    }

    public static Texture getBgTexture(String name) {
        return TEXTURE_MANAGER.get(BG_FOLDER + name, Texture.class);
    }

    public static TextureRegion getRegion(AtlasType atlasType, String regionName) {
        if (!TEXTURE_MANAGER.isLoaded(atlasType.getPath())) {
            throw new IllegalStateException("Atlas is not loaded" + atlasType + " " + regionName);
        }
        TextureAtlas atlas = TEXTURE_MANAGER.get(atlasType.getPath(), TextureAtlas.class);
        TextureRegion region = atlas.findRegion(regionName);
        if (region == null) {
            throw new IllegalArgumentException("Region'" + regionName + "'not found in atlas:" + atlasType.getPath());
        }
        return region;
    }

    public static void finishAll() {
        TEXTURE_MANAGER.finishLoading();
    }

    public static void unload(String file) {
        if (TEXTURE_MANAGER.isLoaded(file)) TEXTURE_MANAGER.unload(file);
    }

    public static void dispose() {
        TEXTURE_MANAGER.dispose();
    }
}
