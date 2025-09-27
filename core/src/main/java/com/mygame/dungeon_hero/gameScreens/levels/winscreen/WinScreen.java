package com.mygame.dungeon_hero.gameScreens.levels.winscreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygame.dungeon_hero.assetManger.Assets;
import com.mygame.dungeon_hero.assetManger.AtlasType;
import com.mygame.dungeon_hero.characters.GameCharacter;
import com.mygame.dungeon_hero.characters.Hero;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class WinScreen implements Screen {
    private final Stage stage;
    private final GameCharacter enemy;
    private final GameCharacter hero;
    private final Image background;
    private final float W;
    private final float H;

    public WinScreen(GameCharacter hero, GameCharacter enemy) {
        this.enemy = enemy;
        this.hero = hero;
        this.background = new Image();
        stage = new Stage(new ScreenViewport());

        W = stage.getViewport().getWorldWidth();
        H = stage.getViewport().getWorldHeight();
    }

    public Map<Image, String> initPreviews () {
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
            Image image = new Image(Assets.getRegion(AtlasType.HERO, builder.toString()));
            image.setScale(0.5f);
            previews.put(image, hero.getClasses().get(i).getLvlUpDescription((Hero) hero));
            System.out.println(hero.getClasses().get(i).getLvlUpDescription((Hero) hero));
            builder.setLength(0);
        }
        return previews;
    }


    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        WinScreenPanel buttons = new WinScreenPanel((Hero) hero, enemy.getLoot(), W, H);
        Stack table = buttons.getPanel();
        table.setFillParent(true);
        table.setPosition(0, H * 3, Align.center);
        stage.addActor(table);
        table.addAction(
            Actions.moveToAligned(W / 2f, H / 2f, Align.center, 3, Interpolation.bounceIn)
        );
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
