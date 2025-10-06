package com.mygame.dungeon_hero.gameScreens.overlay;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygame.dungeon_hero.GameCore;
import com.mygame.dungeon_hero.characters.Enemies;
import com.mygame.dungeon_hero.characters.GameCharacter;
import com.mygame.dungeon_hero.characters.Hero;
import com.mygame.dungeon_hero.assetsManagers.UIManager;
import com.mygame.dungeon_hero.characters.wepons.Weapons;
import com.mygame.dungeon_hero.logic.Battle;
import com.mygame.dungeon_hero.logic.FreeModeCreation;

public final class OverlayManager {
    private static GameCore gameCore;
    private static InfoOverlay overlay;

    private OverlayManager() {}

    public static void initOverlay(Stage stage) {
        if (overlay == null) {
            overlay = new InfoOverlay(stage.getViewport(), gameCore);
        }
    }

    public static void show(Stage stage, Hero hero, Battle currentBattle) {
        initOverlay(stage);
        overlay.openOn(stage, hero, currentBattle);
    }

    public static void hide() {
        if (overlay != null && overlay.getStage() != null) overlay.close();
    }

    public static Table getCompendium(Viewport viewport, boolean isEnemies) {
        Table compendium = new Table();
        compendium.setFillParent(true);

        Image compendiumBg = new Image(UIManager.getSkin().newDrawable("white", 0, 0, 0, 0.6f));
        compendiumBg.setFillParent(true);

        Table compendiumTable;
        if (isEnemies) {
            compendiumTable = getMonsterGrid(viewport, () -> compendium.setVisible(false));
            compendiumTable.setFillParent(true);
        } else {
            compendiumTable = getWeaponGrid(viewport, () -> compendium.setVisible(false));
            compendiumTable.setFillParent(true);
        }

        compendium.addActor(compendiumBg);
        compendium.addActor(compendiumTable);

        compendium.setVisible(false);
        return compendium;
    }

    public static Table updateHeroTAble(Stage stage, Hero hero) {
        initOverlay(stage);
        Table table = overlay.getInfoTable();
        overlay.updateInfo(hero);
        return table;
    }

    private static Table getMonsterGrid(Viewport viewport, Runnable back) {
        Label compendiumName = new Label("Компендиум монстров", UIManager.getSkin(), "label");
        Table compendium = new Table();
        TextButton backButton = new TextButton("Вернуться", UIManager.getSkin(), "big");
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
               back.run();
            }
        });

        compendiumName.setFontScale(2f);
        compendium.add(compendiumName).row();
        Table monsterGrid = new Table();
        monsterGrid.defaults()
            .width(viewport.getWorldWidth() * 0.25f)
            .height(viewport.getWorldHeight() * 0.25f);
        for (int i = 0; i < Enemies.values().length; i++) {
            if (i % 2 == 0) {
                monsterGrid.row();
            }
            monsterGrid.add(new MonsterInfoCard(Enemies.values()[i], viewport));
        }
        compendium.add(monsterGrid).row();
        compendium.add(backButton);
        return compendium;
    }

    private static Table getWeaponGrid(Viewport viewport, Runnable back) {
        Label compendiumName = new Label("Компендиум оружия", UIManager.getSkin(), "label");
        Table compendium = new Table();
        compendiumName.setFontScale(2f);
        compendium.add(compendiumName).row();
        Table monsterGrid = new Table();

        monsterGrid.defaults()
            .width(viewport.getWorldWidth() * 0.25f)
            .height(viewport.getWorldHeight() * 0.25f);
        for (int i = 0; i < Weapons.values().length; i++) {
            if (i % 2 == 0) {
                monsterGrid.row();
            }
            monsterGrid.add(new WeaponInfoCard(Weapons.values()[i], viewport));
        }
        compendium.add(monsterGrid).row();
        if (back != null) {
            TextButton backButton = new TextButton("Вернуться", UIManager.getSkin(), "big");
            backButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    back.run();
                }
            });
            compendium.add(backButton);
        }
        return compendium;
    }

    public static Table getWeaponGridClickable(Viewport viewport, Runnable onClick, Hero hero) {
        Label compendiumName = new Label("Выбери оружие", UIManager.getSkin(), "label");
        Table compendium = new Table();
        compendiumName.setFontScale(2f);
        compendium.add(compendiumName).row();
        Table monsterGrid = new Table();

        monsterGrid.defaults()
            .width(viewport.getWorldWidth() * 0.25f)
            .height(viewport.getWorldHeight() * 0.25f);
        for (int i = 0; i < Weapons.values().length; i++) {
            int index = i;
            if (i % 2 == 0) {
                monsterGrid.row();
            }
            WeaponInfoCard card = new WeaponInfoCard(Weapons.values()[i], viewport);
            card.setTouchable(Touchable.enabled);
            card.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    hero.setWeapon(Weapons.values()[index]);
                    onClick.run();
                }
            });
            monsterGrid.add(card);
        }
        compendium.add(monsterGrid).row();
        return compendium;
    }

    public static Table getMonsterGridClickable(Viewport viewport, FreeModeCreation freeMode) {
        Label compendiumName = new Label("Выбери противника", UIManager.getSkin(), "label");
        Table compendium = new Table();
        compendiumName.setFontScale(2f);
        compendium.add(compendiumName).row();
        Table monsterGrid = new Table();
        monsterGrid.defaults()
            .width(viewport.getWorldWidth() * 0.25f)
            .height(viewport.getWorldHeight() * 0.25f);
        for (int i = 0; i < Enemies.values().length; i++) {
            int index = i;
            if (i % 2 == 0) {
                monsterGrid.row();
            }
            MonsterInfoCard card = new MonsterInfoCard(Enemies.values()[i], viewport);
            card.setTouchable(Touchable.enabled);
            card.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    freeMode.setEnemy(new GameCharacter(Enemies.values()[index]));
                    freeMode.startBattle();
                }
            });
            monsterGrid.add(card);
        }
        compendium.add(monsterGrid).row();
        return compendium;
    }

    public static void setGameCore(GameCore gameCore) {
        OverlayManager.gameCore = gameCore;
    }
}
