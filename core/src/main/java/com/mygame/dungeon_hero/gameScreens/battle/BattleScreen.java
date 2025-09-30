package com.mygame.dungeon_hero.gameScreens.battle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygame.dungeon_hero.assetManger.Assets;
import com.mygame.dungeon_hero.assetManger.AtlasType;
import com.mygame.dungeon_hero.characters.GameCharacter;
import com.mygame.dungeon_hero.characters.Hero;
import com.mygame.dungeon_hero.gameScreens.UIManager;

import static com.badlogic.gdx.scenes.scene2d.ui.Value.*;

public class BattleScreen implements Screen {

    private final Stage stage;
    private final Skin skin = UIManager.getSkin();
    private final GameCharacter hero;
    private final GameCharacter enemy;
    private final Image heroSprite;
    private final Image enemySprite;
    private final Image background;
    private final Label heroHealthLabel;
    private final Label enemyHealthLabel;
    private final Label battleCountLabel;
    private final Runnable onCharReady;
    private final float W;
    private final float H;
    private final float heroXPos;
    private final float enemyXPos;
    private final Label damageLabel;
    private final WinScreenPanel winScreenPanel;

    public BattleScreen(int battleCount, GameCharacter hero, GameCharacter enemy, Runnable onCharReady, Runnable onBattleComplete) {
        this.hero = hero;
        this.heroSprite = new Image(hero.getSprite());
        heroHealthLabel = new Label("", skin,"label");
        heroHealthLabel.setFontScale(2f);

        this.enemy = enemy;
        this.enemySprite = new Image(enemy.getSprite());
        enemyHealthLabel = new Label("", skin,"label");
        enemyHealthLabel.setFontScale(2f);

        this.battleCountLabel = new Label("Бой: " + battleCount + " из 5", skin, "label");

        String bgName = enemy.getName() + MathUtils.random(1, 2) + ".png";
        Assets.changeBg(bgName);
        background = new Image(Assets.getBgTexture(bgName));

        this.onCharReady = onCharReady;

        stage = new Stage(new ScreenViewport());
        W = stage.getViewport().getWorldWidth();
        H = stage.getViewport().getWorldHeight();
        heroXPos = W * 0.20f;
        enemyXPos = W * 0.80f;

        // Генерация индикатора нанесенного урона
        damageLabel = new Label("", skin, "redLabel");
        damageLabel.setFontScale(2f);
        damageLabel.setVisible(false);
        winScreenPanel = new WinScreenPanel((Hero) hero, enemy.getLoot(), W, H, onBattleComplete);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        // ===== корневой стек =====
        Stack root = new Stack();
        root.setFillParent(true);
        stage.addActor(root);

        // --- слой 0: фон ---
        background.setFillParent(true);
        background.setScaling(Scaling.fill);
        background.getColor().a = 0f;
        root.add(background);
        background.addAction(Actions.fadeIn(1.5f));

        // --- слой 1: арена со спрайтами персонажей ---
        Group arena = new Group();
        root.add(arena); // между фоном и HUD

        // позиции сразу тут (W/H лучше брать уже после show/resized)
        float W = stage.getViewport().getWorldWidth();
        float H = stage.getViewport().getWorldHeight();
        float heroX  = W * 0.20f;
        float enemyX = W * 0.80f;
        float centerY = H * 0.50f;

        // старт и анимация въезда
        float heroStartX  = W * 0.25f;
        float enemyStartX = W * 0.75f;
        heroSprite.setPosition(heroStartX,  centerY, Align.center);
        enemySprite.setPosition(enemyStartX, centerY, Align.center);
        heroSprite.addAction(Actions.sequence(
            Actions.delay(1.0f),
            Actions.moveToAligned(heroX,  centerY, Align.center, 0.4f)
        ));
        enemySprite.addAction(Actions.sequence(
            Actions.delay(1.0f),
            Actions.moveToAligned(enemyX, centerY, Align.center, 0.4f)
        ));

        arena.addActor(heroSprite);
        arena.addActor(enemySprite);
        arena.addActor(damageLabel);

        // --- слой 2: HUD ---
        Table hud = new Table();
        hud.setFillParent(true);
        hud.top().pad(
            percentHeight(0.03f, hud),
            percentWidth (0.02f, hud),
            percentHeight(0.00f, hud),
            percentWidth (0.02f, hud)
        );
        hud.getColor().a = 1f;
        hud.addAction(Actions.sequence(Actions.delay(0.8f), Actions.fadeIn(0.6f)));
        root.add(hud); // будет поверх арены

        heroHealthLabel.setText(hero.getHealth() + "/" + hero.getMaxHealth());

        enemyHealthLabel.setText(enemy.getHealth() + "/" + enemy.getMaxHealth());

        // первая строка — тексты
        hud.add(heroHealthLabel).left().expandX();
        hud.add(battleCountLabel).center();
        hud.add(enemyHealthLabel).right().expandX();
        hud.row();

        // вторая строка — по ОДНОМУ heart в каждую ячейку
        Image heroHeart  = new Image(Assets.getRegion(AtlasType.MISC, "heart"));
        Image enemyHeart = new Image(Assets.getRegion(AtlasType.MISC, "heart"));
        hud.add(heroHeart)
            .left()
            .size(percentHeight(0.05f, hud), percentHeight(0.05f, hud))
            .padTop(percentHeight(0.012f, hud));
        hud.add().expandX();
        hud.add(enemyHeart)
            .right()
            .size(percentHeight(0.05f, hud), percentHeight(0.05f, hud))
            .padTop(percentHeight(0.012f, hud));



        // когда спрайты «доехали» — сообщаем наружу
        stage.addAction(Actions.sequence(Actions.delay(2f), Actions.run(onCharReady)));
    }

    public void playAttack(boolean isHeroAttacking, boolean isAttackSuccessfull, int damage, Runnable onAttackAniComplete) {
        if(isHeroAttacking) {
            heroAttackAnimation(onAttackAniComplete, damage, isAttackSuccessfull);
        } else {
            enemyAttackAnimation(onAttackAniComplete, damage, isAttackSuccessfull);
        }
    }

    public void heroAttackAnimation(Runnable onDone, int damage, boolean isAttackSuccefull) {
        float heroXAttackPos = W * 0.60f;
        heroSprite.addAction(Actions.sequence(
            Actions.moveToAligned(heroXAttackPos, H * 0.5f, Align.center, 0.2f),
            Actions.delay(0.3f),
            Actions.run(() -> animateDamageTake(onDone, damage, false, isAttackSuccefull)),
            Actions.moveToAligned(heroXPos, H * 0.5f, Align.center, 0.7f)));


    }

    public void enemyAttackAnimation(Runnable onDone, int damage, boolean isAttackSuccessful) {
        float enemyXAttackPos = W * 0.40f;
        enemySprite.addAction(Actions.sequence(
            Actions.moveToAligned(enemyXAttackPos, H * 0.5f, Align.center, 0.2f),
            Actions.delay(0.3f),
            Actions.run(() -> animateDamageTake(onDone, damage, true, isAttackSuccessful)),
            Actions.moveToAligned(enemyXPos, H * 0.5f, Align.center, 0.7f)));

    }

    private void animateDamageTake(Runnable onDone, int damage, boolean isHeroGetDamage, boolean isAttackSuccessful) {
        GameCharacter defender;
        float damageLabelPos;
        if (isHeroGetDamage) {
            damageLabelPos = heroXPos;
            defender = hero;
        } else {
            damageLabelPos = enemyXPos;
            defender = enemy;
        }
        damageLabel.setPosition(damageLabelPos, H * 0.5f);
        if (isAttackSuccessful) {
            damageLabel.setText(damage);
        } else {
            damageLabel.setText("ПРОМАХ!!!");
        }
        damageLabel.setVisible(true);
        damageLabel.addAction(Actions.moveToAligned(damageLabelPos, H * 0.75f, Align.center, 1.6f));
        damageLabel.addAction(Actions.sequence(
            Actions.run(() -> defender.takeDamage(damage)),
            Actions.fadeOut(1.5f),
            Actions.delay(0.5f),
            Actions.run(() -> damageLabel.setVisible(false)),
            Actions.run(() -> damageLabel.getColor().a = 1f),
            Actions.run(onDone)));
    }

    public void animateWinScreen() {
        Stack table = winScreenPanel.getPanel();
        table.setFillParent(true);
        table.setPosition(0, H * 3, Align.center);
        stage.addActor(table);
        table.addAction(
            Actions.moveToAligned(W / 2f, H / 2f, Align.center, 3, Interpolation.bounceIn)
        );
    }

    public void animateLoseScreen(Runnable runnable) {
        Image diedLabel = new Image(Assets.getRegion(AtlasType.MISC, "diedLabel"));
        Table table = new Table();
        table.setFillParent(true);
        table.add(diedLabel);
        stage.addActor(table);
        stage.addAction(Actions.sequence(
            Actions.delay(2f),
            Actions.fadeOut(1f),
            Actions.delay(1f),
            Actions.run(runnable)));
    }

    public void playTitles(Runnable runnable) {
        Assets.changeBg("completeGame.png");
        Image title = new Image(Assets.getBgTexture("completeGame.png"));
        title.getColor().a = 0;
        stage.addActor(title);
        title.addAction(Actions.fadeIn(0.5f));
    }


    @Override
    public void render(float delta) {
        enemyHealthLabel.setText(enemy.getHealth() + "/" + enemy.getMaxHealth());
        heroHealthLabel.setText(hero.getHealth() + "/" + hero.getMaxHealth());
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1/30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void dispose() {}

}

