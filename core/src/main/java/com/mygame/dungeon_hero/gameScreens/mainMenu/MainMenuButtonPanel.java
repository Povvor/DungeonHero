package com.mygame.dungeon_hero.gameScreens.mainMenu;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygame.dungeon_hero.GameCore;
import com.mygame.dungeon_hero.gameScreens.characterCreation.CreationScreen;
import com.mygame.dungeon_hero.assetsManagers.SoundManager;
import com.mygame.dungeon_hero.logic.FreeModeCreation;
import lombok.Getter;

@Getter
public class MainMenuButtonPanel {
    private final Viewport viewport;
    private final TextButton startButton;
    private final TextButton freeModeButton;
    private final TextButton compendiumButton;
    private final TextButton bgmSwitchButton;
    private final TextButton exitButton;

    public MainMenuButtonPanel(Skin skin, GameCore game, Runnable showCompendium, Viewport viewport) {
        startButton = new TextButton("Начать игру", skin, "big");
        freeModeButton = new TextButton("Свободный режим", skin, "big");
        exitButton = new TextButton("Выйти из игры", skin, "big");
        bgmSwitchButton = new TextButton("Выключить фоновую музыку", skin, "big");
        compendiumButton = new TextButton("Компендиум Монстров", skin, "big");
        this.viewport = viewport;

        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new CreationScreen(game));
            }
        });

        freeModeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                FreeModeCreation freeMode = new FreeModeCreation(game);
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
        float buttonWidth = viewport.getWorldWidth() / 6f;
        table.padTop(buttonWidth);
        table.add(startButton).width(buttonWidth).row();
        table.add(freeModeButton).width(buttonWidth).row();
        table.add(compendiumButton).width(buttonWidth).row();
        table.add(bgmSwitchButton).width(buttonWidth).row();
        table.add(exitButton).width(buttonWidth).row();
        return table;
    }

}
