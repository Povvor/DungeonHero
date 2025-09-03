package com.mygame.dungeon_hero.gameScreens.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygame.dungeon_hero.GameCore;
import com.mygame.dungeon_hero.assetManger.AssetManager;
import com.mygame.dungeon_hero.characters.GameCharacter;
import com.mygame.dungeon_hero.characters.Hero;
import com.mygame.dungeon_hero.characters.classes.Bandit;
import com.mygame.dungeon_hero.characters.classes.Barbarian;
import com.mygame.dungeon_hero.characters.classes.HeroClass;
import com.mygame.dungeon_hero.characters.classes.Warior;

public class WinScreen implements Screen {
    private final Stage stage;
    private final GameCharacter enemy;
    private final GameCharacter hero;
    private final Image background;
    private final Image[] heroSkinPrewiev;
    private final float W;
    private final float H;

    public WinScreen(GameCharacter hero, GameCharacter enemy) {
        this.enemy = enemy;
        this.hero = hero;
        this.background = new Image();

        heroSkinPrewiev = initPreviews();
        stage = new Stage(new ScreenViewport());

        W = stage.getViewport().getWorldWidth();
        H = stage.getViewport().getWorldHeight();
    }

    public Image[] initPreviews () {
        Image[] heroSkinPreview = new Image[hero.getClasses().size()];
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < heroSkinPreview.length; i++) {
            for (int j = 0; j < heroSkinPreview.length; j++) {
                if (j == i) {
                    builder.append(hero.getClasses().get(j).getLvl() + 1);
                } else {
                    builder.append(hero.getClasses().get(j).getLvl());
                }
            }
            heroSkinPreview[i] = new Image(AssetManager.getHERO_SPRITES().get(builder.toString()));
            builder.setLength(0);
        }
        return heroSkinPreview;
    }


    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        heroSkinPrewiev[0].setPosition(W * 0.25f, H * 0.5f);
        heroSkinPrewiev[1].setPosition(W * 0.50f, H * 0.5f);
        heroSkinPrewiev[2].setPosition(W * 0.75f, H * 0.5f);
        for (int i = 0; i < hero.getClasses().size(); i++) {
            heroSkinPrewiev[i].setPosition(W / (hero.getClasses().size() + 1) * (i + 1) , H * 0.5f, Align.center);
            stage.addActor(heroSkinPrewiev[i]);
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1/30f));
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
