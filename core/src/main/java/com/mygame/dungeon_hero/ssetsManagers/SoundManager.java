package com.mygame.dungeon_hero.ssetsManagers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class SoundManager {
    private SoundManager() {}

    private static Music currentBg;
    private static String currentBgPath;
    private static boolean isBgmMute = false;

    @Getter
    @RequiredArgsConstructor
    public enum Sfx {
        DEFEAT("assets/audio/sfx/defeat_sound.ogg"),
        DIE("assets/audio/sfx/die_sound.ogg"),
        HIT("assets/audio/sfx/hit_sound.ogg"),;
        private final String path;
    }

    private static final AssetManager am = new AssetManager();

    public static void loadAllSfx() {
        for(Sfx sfx : Sfx.values()) {
            am.load(sfx.path, Sound.class);
        }
        finisAll();
    }

    public static void loadAndPlayBgMusic(String bgName) {
        if (currentBg != null) {
            currentBg.stop();
            am.unload(currentBgPath);
        }
        currentBgPath = "assets/audio/bgm/" + bgName + "_bgm.ogg";
        am.load(currentBgPath, Music.class);
        finisAll();
        currentBg = am.get(currentBgPath);
        currentBg.setLooping(true);
        float volume = isBgmMute ? 0 : 1f;
        currentBg.setVolume(volume);
        currentBg.play();
    }

    public static void play(Sfx sfx) {
        Sound sound = am.get(sfx.path, Sound.class);
        sound.play(1f);
    }

    public static void stopMusic() {
        if (currentBg != null) {
            currentBg.stop();
        }
    }

    public static void switchMute() {
        if (currentBg.getVolume() > 0) {
            currentBg.setVolume(0);
            isBgmMute = true;
        } else  {
            currentBg.setVolume(1);
            isBgmMute = false;
        }
    }

    public static void finisAll() {
        am.finishLoading();
    }

    public static void dispose() {
        am.dispose();
    }
}
