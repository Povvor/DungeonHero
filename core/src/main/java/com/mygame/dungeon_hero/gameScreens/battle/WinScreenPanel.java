package com.mygame.dungeon_hero.gameScreens.battle;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.mygame.dungeon_hero.uiManagers.TextureManager;
import com.mygame.dungeon_hero.characters.Hero;
import com.mygame.dungeon_hero.characters.wepons.Weapons;
import com.mygame.dungeon_hero.uiManagers.UIManager;
import lombok.Getter;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Getter
public class WinScreenPanel {
    private final Label nameLabel;
    private final Image droppedWeapon;
    private final TextButton changeWeaponButton;
    private final TextButton banditUpButton;
    private final TextButton wariorUpButton;
    private final TextButton barbarianUpButton;
    private final List<Image> previewTextures;
    private final List<Label> descriptions;
    private final Skin skin = UIManager.getSkin();
    private final Image background;
    private final float W;
    private final float H;

    public WinScreenPanel(Hero hero, Weapons weapon, float width, float height, Runnable onBattleComplete) {
        this.W = width;
        this.H = height;

        String labelText = "Вам выпал: " + weapon.getName() + "\n" + "Урон: " + weapon.getDamage() + "\n" + weapon.getDamageType().getDescription();
        nameLabel = new Label(labelText, skin, "label");

        this.droppedWeapon = new Image(weapon.getSprite());

        changeWeaponButton = new TextButton("ВЗЯТЬ!!!", skin, "big");
        banditUpButton = new TextButton("Путь Разбойника", skin, "big");
        wariorUpButton = new TextButton("Путь Воина", skin, "big");
        barbarianUpButton = new TextButton("Путь Варвара", skin, "big");
        this.previewTextures = new ArrayList<>();
        this.descriptions = new ArrayList<>();

        if (hero.getLevel() < 3) {
            for (Map.Entry<Image, String> entry : initPreviews(hero).entrySet()) {
                previewTextures.add(entry.getKey());
                descriptions.add(new Label(entry.getValue(), skin,"label"));
            }
        } else {
            for (int i = 0; i < 3; i++) {
                previewTextures.add(new Image());
                descriptions.add(new Label(" ", skin,"label"));
            }
            banditUpButton.setVisible(false);
            barbarianUpButton.setVisible(false);
            descriptions.get(1).setText("У вас максимальный уровень!");
            wariorUpButton.setText("Продолжить!");
        }

        this.background = new Image(TextureManager.getBgTexture("winbg.png"));

        banditUpButton.addListener(new ClickListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                super.enter(event, x, y, pointer, fromActor);

                wariorUpButton.setColor(1, 0.8f, 0.8f, 1);
                previewTextures.get(0).setVisible(false);
                descriptions.get(0).setVisible(true);
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                super.exit(event, x, y, pointer, toActor);

                wariorUpButton.setColor(1, 1, 1, 1);
                previewTextures.get(0).setVisible(true);
                descriptions.get(0).setVisible(false);
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {
                hero.lvlUp(0);
                onBattleComplete.run();
            }
        });

        wariorUpButton.addListener(new ClickListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                super.enter(event, x, y, pointer, fromActor);

                wariorUpButton.setColor(1, 0.8f, 0.8f, 1); // подсветка
                previewTextures.get(1).setVisible(false);
                descriptions.get(1).setVisible(true);
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                super.exit(event, x, y, pointer, toActor);

                wariorUpButton.setColor(1, 1, 1, 1);
                previewTextures.get(1).setVisible(true);
                descriptions.get(1).setVisible(false);
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (hero.getLevel() < 3) {
                    hero.lvlUp(1);
                    onBattleComplete.run();
                } else {
                    hero.setMaxHealth(hero.getMaxHealth() + hero.getEndurance());
                    onBattleComplete.run();
                }
            }
        });

        barbarianUpButton.addListener(new ClickListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                super.enter(event, x, y, pointer, fromActor);

                wariorUpButton.setColor(1, 0.8f, 0.8f, 1); // подсветка
                previewTextures.get(2).setVisible(false);
                descriptions.get(2).setVisible(true);
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                super.exit(event, x, y, pointer, toActor);

                wariorUpButton.setColor(1, 1, 1, 1);
                previewTextures.get(2).setVisible(true);
                descriptions.get(2).setVisible(false);
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {
                hero.lvlUp(2);
                onBattleComplete.run();

            }
        });

        changeWeaponButton.addListener(new ClickListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                super.enter(event, x, y, pointer, fromActor);
                changeWeaponButton.setColor(1, 0.8f, 0.8f, 1);
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                super.exit(event, x, y, pointer, toActor);
                changeWeaponButton.setColor(1, 1, 1, 1);
            }
            @Override
            public void clicked(InputEvent event, float x, float y) {
                hero.changeWeapon(weapon);
                changeWeaponButton.setVisible(false);
            }
        });

    }

    public Stack getPanel() {
        Stack stack = new Stack();
        Table root = new Table();
        Table heroButtons = new Table();
        for (Image image : previewTextures) {
            image.setOrigin(Align.bottom);
        }
        for (Label label : descriptions) {
            label.setOrigin(Align.bottom);
            label.setAlignment(Align.center);
        }

        Table col1 = new Table();
        Stack stack1 = new Stack();
        descriptions.get(0).setVisible(false);
        stack1.add(descriptions.get(0));
        stack1.add(previewTextures.get(0));
        col1.add(stack1).row();
        col1.add(banditUpButton);

        Table col2 = new Table();
        Stack stack2 = new Stack();
        descriptions.get(1).setVisible(false);
        stack2.add(descriptions.get(1));
        stack2.add(previewTextures.get(1));
        col2.add(stack2).row();
        col2.add(wariorUpButton);

        Table col3 = new Table();
        Stack stack3 = new Stack();
        descriptions.get(2).setVisible(false);
        stack3.add(descriptions.get(2));
        stack3.add(previewTextures.get(2));
        col3.add(stack3).row();
        col3.add(barbarianUpButton).row();

        heroButtons.add(col1);
        heroButtons.add(col2);
        heroButtons.add(col3);

        root.add(nameLabel).row();
        root.add(droppedWeapon).size(W / 12, H / 6).row();
        root.add(changeWeaponButton).row();
        root.add(heroButtons).size(W * 0.9f, H / 3).row();

        float rootScale = 0.15f;
        root.padLeft(W * rootScale);
        root.padRight(W * rootScale);
        root.padTop(H * rootScale);
        root.padBottom(H * rootScale);

        Table overlay = new Table();
        overlay.setFillParent(false);

        overlay.add(root);
        stack.add(background);
        stack.add(root);
        return stack;
    }

    private Map<Image, String> initPreviews (Hero hero) {
        Map<Image, String> previews = new LinkedHashMap<>();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < hero.getClasses().size(); i++) {
            for (int j = 0; j < hero.getClasses().size(); j++) {
                if (j == i) {
                    builder.append(hero.getClasses().get(j).getLvl() + 1);
                } else {
                    builder.append(hero.getClasses().get(j).getLvl());
                }
            }
            Image image = new Image(TextureManager.getRegion(TextureManager.AtlasType.HERO, builder.toString()));
            image.setScale(0.5f);
            previews.put(image, hero.getClasses().get(i).getLvlUpDescription(hero));
            System.out.println(hero.getClasses().get(i).getLvlUpDescription(hero));
            builder.setLength(0);
        }
        return previews;
    }

}
