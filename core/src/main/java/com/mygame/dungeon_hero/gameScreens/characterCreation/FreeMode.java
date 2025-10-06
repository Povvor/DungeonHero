package com.mygame.dungeon_hero.gameScreens.characterCreation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygame.dungeon_hero.assetsManagers.TextureManager;
import com.mygame.dungeon_hero.assetsManagers.UIManager;
import com.mygame.dungeon_hero.characters.Hero;
import com.mygame.dungeon_hero.gameScreens.overlay.OverlayManager;
import com.mygame.dungeon_hero.logic.FreeModeCreation;
import lombok.Setter;

public class FreeMode implements Screen {
    @Setter
    public Hero hero;
    public Stage stage;
    private final FreeModeCreation freeModeCreation;
    private TextButton nextButton;
    private final Table createHero;
    private Table choseWeapon;
    private final Table choseEnemy;
    private Table heroInfoLabel;

    public FreeMode(FreeModeCreation freeModeCreation) {
        stage = new Stage(new ScreenViewport());
        this.freeModeCreation = freeModeCreation;
        createHero = heroLvlUpButtons();
        createHero.setFillParent(true);
        choseEnemy = OverlayManager.getMonsterGridClickable(stage.getViewport(), freeModeCreation);
        choseEnemy.setVisible(false);
        choseEnemy.setFillParent(true);
        heroInfoLabel = new Table();
        heroInfoLabel.setVisible(true);
    }

    @Override
    public void show() {
        nextButton = new TextButton("К выбору оружия", UIManager.getSkin(), "big");
        nextButton.setVisible(false);
        nextButton.setPosition(stage.getViewport().getWorldWidth() / 2, 100, Align.center);
        nextButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                initWeapons();
                nextButton.setVisible(false);
                createHero.setVisible(false);
                heroInfoLabel.setVisible(false);
                choseWeapon.setVisible(true);
            }
        });
        TextureManager.changeBg("tavern.png");
        Image background = new Image(TextureManager.getBgTexture("tavern.png"));
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        background.setFillParent(true);
        background.setScaling(Scaling.fill);
        stage.addActor(background);
        stage.addActor(heroInfoLabel);
        stage.addActor(createHero);
        stage.addActor(choseEnemy);
        stage.addActor(nextButton);
    }

    public Table heroLvlUpButtons() {
        Table table = new Table();
        TextButton banditButton = new TextButton("+Разбойник", UIManager.getSkin(), "big");
        TextButton wariorButton = new TextButton("+Воин", UIManager.getSkin(), "big");
        TextButton barbarianButton = new TextButton("+Варвар", UIManager.getSkin(), "big");
        TextButton[] buttons = {banditButton, wariorButton, barbarianButton};

        for (int i = 0; i < buttons.length; i++) {
            int index = i;
            buttons[i].addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    lvlUpHero(index, buttons);
                }
            });
        }

        table.add(banditButton).row();
        table.add(wariorButton).row();
        table.add(barbarianButton).row();

        return table;
    }

    private void lvlUpHero(int index, TextButton[] buttons) {
        nextButton.setVisible(true);
        int lvl = freeModeCreation.createOrLvlUpHero(index);
        if (lvl > 2) {
            for (TextButton button : buttons) {
                button.setText("+Уровень");
            }
        }
        heroInfoLabel = OverlayManager.updateHeroTAble(stage, hero);
        heroInfoLabel.setSize(stage.getViewport().getWorldWidth() / 2.3f, stage.getViewport().getWorldHeight());
        heroInfoLabel.setPosition(0,0);
        stage.addActor(heroInfoLabel);
    }

    private void shiftToEnemy() {
        choseWeapon.setVisible(false);
        choseEnemy.setVisible(true);
    }

    private void initWeapons() {
        choseWeapon = OverlayManager.getWeaponGridClickable(stage.getViewport(), this::shiftToEnemy, hero);
        choseWeapon.setFillParent(true);
        choseWeapon.setVisible(false);
        stage.addActor(choseWeapon);
    }



    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 60f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {
        stage.dispose();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}


