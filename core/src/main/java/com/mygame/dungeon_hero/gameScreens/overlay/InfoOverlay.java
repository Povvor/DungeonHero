package com.mygame.dungeon_hero.gameScreens.overlay;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygame.dungeon_hero.GameCore;
import com.mygame.dungeon_hero.characters.Hero;
import com.mygame.dungeon_hero.uiManagers.SoundManager;
import com.mygame.dungeon_hero.uiManagers.UIManager;


import static com.badlogic.gdx.scenes.scene2d.ui.Value.percentHeight;


public class InfoOverlay extends Group {
    private final Table panel;
    private final Viewport viewport;
    private final Table infoTable;
    private final Table compendiumTable;
    private final GameCore gameCore;

    public InfoOverlay(Viewport viewport, GameCore gameCore) {
        this.viewport = viewport;
        this.gameCore = gameCore;
        Table buttonTable = getButtonPanel();
        infoTable = new Table();
        infoTable.setVisible(false);
        compendiumTable = OverlayManager.getCompendium(viewport);
        compendiumTable.setVisible(false);

        Image background = new Image(UIManager.getSkin().newDrawable("white", 0, 0, 0, 0.6f));
        background.setFillParent(true);
        setTouchable(Touchable.enabled);

        Stack stack = new Stack();
        stack.setFillParent(true);
        stack.add(background);
        stack.add(buttonTable);
        stack.add(infoTable);
        stack.add(compendiumTable);

        panel = new Table();
        panel.setFillParent(true);
        panel.add(stack).expand().fill();
        addActor(panel);
        getColor().a = 0f;
    }

    public void openOn(Stage stage, Hero hero) {
        updateInfo(hero);
        infoTable.setVisible(false);
        setSize(stage.getViewport().getWorldWidth(), stage.getViewport().getWorldHeight());
        panel.pack();
        panel.setPosition((getWidth()-panel.getWidth())/2f, (getHeight()-panel.getHeight())/2f);
        if (getStage() == null) stage.addActor(this);
        toFront();
        clearActions();
        addAction(Actions.fadeIn(0.15f));
    }

    public void updateInfo(Hero hero) {
        updateInfoTable(infoTable, hero);
        infoTable.setVisible(true);

    }

    public void close() {
        clearActions();
        addAction(Actions.sequence(Actions.fadeOut(0.12f), Actions.removeActor()));
    }

    public void updateInfoTable(Table infoTable, Hero hero) {
        Skin skin = UIManager.getSkin();
        infoTable.clear();

        Image bg = new Image(skin.newDrawable("white", 0, 0, 0, 0.6f));
        bg.setFillParent(true);
        infoTable.addActor(bg);
        infoTable.pack();
        infoTable.setPosition(
            (viewport.getWorldWidth() - infoTable.getWidth()) / 2f,
            (viewport.getWorldHeight() - infoTable.getHeight()) / 2f
        );

        Table content = new Table();
        infoTable.add(content);
        Image heroImg = new Image(hero.getSprite());

        Table right = new Table();
        Label info = new Label(hero.getHeroInfo(), skin, "label");
        info.setFontScale(1.5f);
        info.setWrap(true);
        right.add(info).expand().fill().row();

        Image weaponImg = new Image(hero.getWeapon().getSprite());
        weaponImg.setScaling(Scaling.fit);
        weaponImg.setAlign(Align.left);
        right.add(weaponImg).height(percentHeight(0.35f, right)).row();

        content.add(heroImg);
        content.add(right).expand().fill();

        TextButton backButton = new TextButton("Назад", skin, "big");
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                infoTable.setVisible(false);
            }
        });

        content.row();
        content.add(backButton);
    }

    public Table getButtonPanel() {
        Table buttonPanel = new Table();
        TextButton heroButton = new TextButton("Инфо о герое", UIManager.getSkin(), "big");
        TextButton compendiumButton = new TextButton("Компендиум Монстров", UIManager.getSkin(), "big");
        TextButton bgmSwitchButton = new TextButton("Выключить фоновую музыку", UIManager.getSkin(), "big");
        TextButton mainMenuButton = new TextButton("В главное меню", UIManager.getSkin(), "big");

        heroButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                infoTable.setVisible(true);
            }
        });

        compendiumButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                compendiumTable.setVisible(true);
            }

        });

        bgmSwitchButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                SoundManager.switchMute();
            }
        });

        mainMenuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameCore.restartGame();
            }
        });

        buttonPanel.add(heroButton).row();
        buttonPanel.add(compendiumButton).row();
        buttonPanel.add(bgmSwitchButton).row();
        buttonPanel.add(mainMenuButton).row();
        return buttonPanel;
    }
}
