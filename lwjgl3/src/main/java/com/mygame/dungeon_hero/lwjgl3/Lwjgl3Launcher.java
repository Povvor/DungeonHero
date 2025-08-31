package com.mygame.dungeon_hero.lwjgl3;

import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygame.dungeon_hero.GameCore;


/** Launches the desktop (LWJGL3) application. */
public class Lwjgl3Launcher {
    public static void main(String[] args) {
        if (StartupHelper.startNewJvmIfRequired()) return; // This handles macOS support and helps on Windows.
        createApplication();
    }

    private static Lwjgl3Application createApplication() {
        return new Lwjgl3Application(new GameCore(), getDefaultConfiguration());
    }

    private static Lwjgl3ApplicationConfiguration getDefaultConfiguration() {
        Lwjgl3ApplicationConfiguration configuration = new Lwjgl3ApplicationConfiguration();
        configuration.setTitle("DungeonHero");
        configuration.useVsync(true);

        // ВАЖНО: не используем exclusive fullscreen
        // configuration.setFullscreenMode(...);  <-- убрать

        // Делаем безрамочное окно размером с текущий дисплей
        Graphics.DisplayMode dm = Lwjgl3ApplicationConfiguration.getDisplayMode();
        configuration.setWindowedMode(dm.width, dm.height);
        configuration.setDecorated(false);     // без рамки
        configuration.setResizable(false);
        configuration.setWindowPosition(0, 0); // во весь экран

        // Можно оставить FPS как есть или убрать — при включённом VSync он не критичен
        configuration.setForegroundFPS(dm.refreshRate);

        configuration.setWindowIcon("libgdx128.png", "libgdx64.png", "libgdx32.png", "libgdx16.png");
        return configuration;
    }
}
