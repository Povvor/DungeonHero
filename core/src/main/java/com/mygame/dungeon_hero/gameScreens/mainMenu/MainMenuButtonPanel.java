package com.mygame.dungeon_hero.gameScreens.mainMenu;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygame.dungeon_hero.GameCore;
import com.mygame.dungeon_hero.gameScreens.characterCreation.CreationScreen;
import com.mygame.dungeon_hero.assetsManagers.SoundManager;
import lombok.Getter;

@Getter
public class MainMenuButtonPanel {
    private final TextButton startButton;
    private final TextButton compendiumButton;
    private final TextButton bgmSwitchButton;
    private final TextButton exitButton;

    public MainMenuButtonPanel(Skin skin, GameCore game, Runnable showCompendium) {
        startButton = new TextButton("Начать игру", skin, "big");
        exitButton = new TextButton("Выйти из игры", skin, "big");
        bgmSwitchButton = new TextButton("Выключить фоновую музыку", skin, "big");
        compendiumButton = new TextButton("Компендиум Монстров", skin, "big");

        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new CreationScreen(game));
            }
        });

        compendiumButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                showCompendium.run();
            }
        });

        bgmSwitchButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                SoundManager.switchMute();
            }
        });

        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.disposeAllAnfQuit();
            }
        });
    }

    public Table getPanel() {
        Table table = new Table();
        table.add(startButton).width(800);
        table.row();
        table.add(compendiumButton).width(800);
        table.row();
        table.add(bgmSwitchButton).width(800);
        table.row();
        table.add(exitButton).width(800);
        return table;
    }

}
