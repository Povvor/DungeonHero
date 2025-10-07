package com.mygame.dungeon_hero.gameScreens.battle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygame.dungeon_hero.assetsManagers.TextureManager;
import com.mygame.dungeon_hero.assetsManagers.SoundManager;
import com.mygame.dungeon_hero.characters.GameCharacter;
import com.mygame.dungeon_hero.characters.Hero;
import com.mygame.dungeon_hero.assetsManagers.UIManager;
import com.mygame.dungeon_hero.logic.Battle;

import static com.badlogic.gdx.scenes.scene2d.ui.Value.*;

public class BattleScreen implements Screen {

    private final Stage stage;
    private final GameCharacter hero;
    private final GameCharacter enemy;
    private final Image heroSprite;
    private final Image enemySprite;
    private final Image background;
    private final Label heroHealthLabel;
    private final Label enemyHealthLabel;
    private final Label battleCountLabel;
    private final Battle battle;
    private final float w;
    private final float h;
    private final float heroXPos;
    private final float enemyXPos;
    private final Label damageLabel;
    private final Label defenderPerksLabel;
    private final Label attackerPerksLabel;
    private final WinScreenPanel winScreenPanel;

    public BattleScreen(int battleCount, GameCharacter hero, GameCharacter enemy, Battle battle, Runnable onBattleComplete) {
        this.hero = hero;
        this.heroSprite = new Image(hero.getSprite());
        Skin skin = UIManager.getSkin();
        heroHealthLabel = new Label("", skin, "label");
        heroHealthLabel.setFontScale(2f);

        this.enemy = enemy;
        this.enemySprite = new Image(enemy.getSprite());
        enemyHealthLabel = new Label("", skin, "label");
        enemyHealthLabel.setFontScale(2f);

        this.battleCountLabel = new Label("Бой: " + battleCount + " из 5", skin, "label");

        String bgName = enemy.getName().toLowerCase() + MathUtils.random(1, 2) + ".png";
        TextureManager.changeBg(bgName);
        background = new Image(TextureManager.getBgTexture(bgName));

        this.battle = battle;

        stage = new Stage(new ScreenViewport());
        w = stage.getViewport().getWorldWidth();
        h = stage.getViewport().getWorldHeight();
        heroXPos = w * 0.20f;
        enemyXPos = w * 0.80f;
        heroSprite.setSize(w / 2.4f, h / 2.4f);
        heroSprite.setScaling(Scaling.fit);

        enemySprite.setSize(w / 2.4f, h / 2.4f);
        enemySprite.setScaling(Scaling.fit);

        damageLabel = new Label("", skin, "redLabel");
        damageLabel.setFontScale(2f);
        damageLabel.setVisible(false);

        defenderPerksLabel = new Label("", skin, "redLabel");
        defenderPerksLabel.setFontScale(2f);
        defenderPerksLabel.setVisible(false);
        defenderPerksLabel.setAlignment(Align.center);

        attackerPerksLabel = new Label("", skin, "redLabel");
        attackerPerksLabel.setFontScale(2f);
        attackerPerksLabel.setVisible(false);
        attackerPerksLabel.setAlignment(Align.center);

        winScreenPanel = new WinScreenPanel((Hero) hero, enemy.getLoot(), w, h, onBattleComplete);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        Stack root = new Stack();
        root.setFillParent(true);
        stage.addActor(root);

        background.setFillParent(true);
        background.setScaling(Scaling.fill);
        background.getColor().a = 0f;
        root.add(background);
        background.addAction(Actions.fadeIn(1.5f));

        PausableGroup arena = new PausableGroup();
        root.add(arena);

        float heroX  = w * 0.20f;
        float enemyX = w * 0.80f;
        float centerY = h * 0.50f;

        float heroStartX  = w * 0.25f;
        float enemyStartX = w * 0.75f;
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
        arena.addActor(defenderPerksLabel);
        arena.addActor(attackerPerksLabel);

        Table hud = new Table();
        hud.setFillParent(true);
        hud.top().pad(
            percentHeight(0.03f, hud),
            percentWidth(0.02f, hud),
            percentHeight(0.00f, hud),
            percentWidth(0.02f, hud)
        );
        hud.getColor().a = 1f;
        hud.addAction(Actions.sequence(Actions.delay(0.8f), Actions.fadeIn(0.6f)));
        root.add(hud);

        heroHealthLabel.setText(hero.getHealth() + "/" + hero.getMaxHealth());
        enemyHealthLabel.setText(enemy.getHealth() + "/" + enemy.getMaxHealth());

        hud.add(heroHealthLabel).left().expandX();
        hud.add(battleCountLabel).center();
        hud.add(enemyHealthLabel).right().expandX();
        hud.row();

        Image heroHeart  = new Image(TextureManager.getRegion(TextureManager.AtlasType.MISC, "heart"));
        Image enemyHeart = new Image(TextureManager.getRegion(TextureManager.AtlasType.MISC, "heart"));
        hud.add(heroHeart)
            .left()
            .size(percentHeight(0.05f, hud), percentHeight(0.05f, hud))
            .padTop(percentHeight(0.012f, hud));
        hud.add().expandX();
        hud.add(enemyHeart)
            .right()
            .size(percentHeight(0.05f, hud), percentHeight(0.05f, hud))
            .padTop(percentHeight(0.012f, hud));
        stage.addAction(Actions.sequence(Actions.delay(2f), Actions.run(battle::turn)));

        stage.addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if (keycode == com.badlogic.gdx.Input.Keys.ESCAPE) {
                    arena.switchPause(stage, (Hero) hero, battle);
                    return true;
                }
                return false;
            }
        });
    }

    public void playAttack(boolean isHeroAttacking, Battle.AttackMetadata attackMetadata, Runnable onAttackAniComplete) {
        if (isHeroAttacking) {
            heroAttackAnimation(onAttackAniComplete, attackMetadata);
        } else {
            enemyAttackAnimation(onAttackAniComplete, attackMetadata);
        }
    }

    public void heroAttackAnimation(Runnable onDone, Battle.AttackMetadata attackMetadata) {
        float heroXAttackPos = w * 0.60f;
        heroSprite.addAction(Actions.sequence(
            Actions.moveToAligned(heroXAttackPos, h * 0.5f, Align.center, 0.2f),
            Actions.delay(0.3f),
            Actions.run(() -> animateDamageTake(onDone, heroXAttackPos, attackMetadata,  false)),
            Actions.run(() -> checkDefenderStatus(enemy)),
            Actions.moveToAligned(heroXPos, h * 0.5f, Align.center, 0.7f)));
    }

    public void enemyAttackAnimation(Runnable onDone, Battle.AttackMetadata attackMetadata) {
        float enemyXAttackPos = w * 0.40f;
        enemySprite.addAction(Actions.sequence(
            Actions.moveToAligned(enemyXAttackPos, h * 0.5f, Align.center, 0.2f),
            Actions.delay(0.3f),
            Actions.run(() -> animateDamageTake(onDone, enemyXAttackPos, attackMetadata, true)),
            Actions.run(() -> checkDefenderStatus(hero)),
            Actions.moveToAligned(enemyXPos, h * 0.5f, Align.center, 0.7f)));
    }

    private void animateDamageTake(Runnable onDone, float attackPos, Battle.AttackMetadata attackMetadata, boolean isHeroGetDamage) {
        String defenderPerksString = String.join("\n", attackMetadata.getDefendPerksMsg());
        defenderPerksLabel.setText(defenderPerksString);

        String attackerPerksString = String.join("\n", attackMetadata.getAttackPerksMsg());
        attackerPerksLabel.setText(attackerPerksString);

        float damageLabelPos;
        float attackPerksXPos;

        GameCharacter defender;
        if (isHeroGetDamage) {
            damageLabelPos = heroXPos;
            attackPerksXPos = attackPos;
            defender = hero;
        } else {
            damageLabelPos = enemyXPos;
            attackPerksXPos = attackPos;
            defender = enemy;
        }

        if (attackMetadata.isAttackSuccessful()) {
            damageLabel.setText(attackMetadata.getDamage());
            defender.takeDamage(attackMetadata.getDamage());
            SoundManager.play(SoundManager.Sfx.HIT);
        } else {
            damageLabel.setText("ПРОМАХ!!!");
        }

        damageLabel.setPosition(damageLabelPos, h * 0.5f);
        damageLabel.setVisible(true);
        damageLabel.getColor().a = 1f;
        damageLabel.addAction(Actions.moveToAligned(damageLabelPos, h * 0.75f, Align.center, 1.6f));
        damageLabel.addAction(Actions.sequence(
            Actions.fadeOut(1.5f),
            Actions.delay(0.5f),
            Actions.run(onDone)));

        defenderPerksLabel.setPosition(damageLabelPos, h * 0.4f, Align.center);
        defenderPerksLabel.setVisible(true);
        defenderPerksLabel.getColor().a = 1f;
        defenderPerksLabel.addAction(Actions.moveToAligned(damageLabelPos, h * 0.25f, Align.center, 1.6f));
        defenderPerksLabel.addAction(Actions.fadeOut(1.5f));

        attackerPerksLabel.setPosition(attackPerksXPos, h * 0.5f, Align.center);
        attackerPerksLabel.setVisible(true);
        attackerPerksLabel.getColor().a = 1f;
        attackerPerksLabel.addAction(Actions.moveToAligned(attackPerksXPos, h * 0.75f, Align.center, 1.6f));
        attackerPerksLabel.addAction(Actions.fadeOut(1.5f));

    }

    public void animateWinScreen() {
        Stack table = winScreenPanel.getPanel();
        table.setFillParent(true);
        table.setPosition(0, h * 3, Align.center);
        stage.addActor(table);
        table.addAction(
            Actions.moveToAligned(w / 2f, h / 2f, Align.center, 3, Interpolation.bounceIn)
        );
    }

    public void animateLoseScreen(Runnable runnable) {
        Image diedLabel = new Image(TextureManager.getRegion(TextureManager.AtlasType.MISC, "diedLabel"));
        Table table = new Table();
        table.setFillParent(true);
        table.add(diedLabel);
        SoundManager.stopMusic();
        SoundManager.play(SoundManager.Sfx.DEFEAT);
        stage.addActor(table);
        stage.addAction(Actions.sequence(
            Actions.delay(2f),
            Actions.fadeOut(1f),
            Actions.delay(2f),
            Actions.run(runnable)));
    }

    public void checkDefenderStatus(GameCharacter attackedChar) {
        System.out.println(attackedChar.getHealth());
        if (attackedChar.getHealth() > 0) {
            return;
        }
        Image grave = new Image(TextureManager.getRegion(TextureManager.AtlasType.MISC, "grave"));
        if (attackedChar == hero) {
            heroSprite.setDrawable(grave.getDrawable());
        } else {
            enemySprite.setDrawable(grave.getDrawable());
        }
        SoundManager.play(SoundManager.Sfx.DIE);
        SoundManager.stopMusic();

    }

    @Override
    public void render(float delta) {
        enemyHealthLabel.setText(enemy.getHealth() + "/" + enemy.getMaxHealth());
        heroHealthLabel.setText(hero.getHealth() + "/" + hero.getMaxHealth());
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 60f));
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
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

}

