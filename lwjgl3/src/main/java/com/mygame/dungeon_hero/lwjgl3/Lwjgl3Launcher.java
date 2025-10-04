package com.mygame.dungeon_hero.lwjgl3;

import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygame.dungeon_hero.GameCore;


public class Lwjgl3Launcher {
    public static void main(String[] args) {
        if (StartupHelper.startNewJvmIfRequired()) return;
        createApplication();
    }

    private static Lwjgl3Application createApplication() {
        return new Lwjgl3Application(new GameCore(), getDefaultConfiguration());
    }

    private static Lwjgl3ApplicationConfiguration getDefaultConfiguration() {
        Lwjgl3ApplicationConfiguration configuration = new Lwjgl3ApplicationConfiguration();
        configuration.setTitle("DungeonHero");
        configuration.useVsync(true);

        Graphics.DisplayMode dm = Lwjgl3ApplicationConfiguration.getDisplayMode();
        configuration.setWindowedMode(dm.width, dm.height);
        configuration.setDecorated(false);
        configuration.setResizable(false);
        configuration.setWindowPosition(0, 0);
        configuration.setForegroundFPS(dm.refreshRate);

        configuration.setWindowIcon("libgdx128.png", "libgdx64.png", "libgdx32.png", "libgdx16.png");
        return configuration;
    }
}
