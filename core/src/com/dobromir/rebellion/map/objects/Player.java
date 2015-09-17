package com.dobromir.rebellion.map.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.dobromir.rebellion.Game;
import com.dobromir.rebellion.constantly.KeysConfig;
import com.dobromir.rebellion.data.ImageCache;
import com.dobromir.rebellion.data.Sounds;
import com.dobromir.rebellion.sprites.GameSprite;
import com.dobromir.rebellion.sprites.MovingSprite;
import com.dobromir.rebellion.utils.io.Console;

import java.util.Map;


public class Player extends MovingSprite{

    private boolean dead = false;

    private Sprite sprite;

    private TextureRegion playerFrontTexture;
    private TextureRegion playerBackTexture;
    private TextureRegion playerLeftTexture;
    private TextureRegion playerRightTexture;

    public Player(Game game, float x, float y, float speed) {
        super(game, x, y, speed);

        playerFrontTexture = ImageCache.getTexture("player_front");
        playerBackTexture = ImageCache.getTexture("player_back");
        playerLeftTexture = ImageCache.getTexture("player_left");
        playerRightTexture = ImageCache.getTexture("player_right");

        sprite = new Sprite(playerFrontTexture);
        width = sprite.getRegionWidth();
        height = sprite.getRegionHeight();
    }

    public void shoot() {
        Sounds.play("shot", 1f, 1f);
    }

    public void input() {
        if(Gdx.input.isKeyPressed(KeysConfig.MOVE_FORWARD)) {
            moveForward();
        } if(Gdx.input.isKeyPressed(KeysConfig.MOVE_BACK)){
            moveBack();
        } if(Gdx.input.isKeyPressed(KeysConfig.MOVE_LEFT)){
            moveLeft();
        } if(Gdx.input.isKeyPressed(KeysConfig.MOVE_RIGHT)){
            moveRight();
        }

        if(Gdx.input.isKeyJustPressed(KeysConfig.SHOOT)) shoot();
    }

    @Override
    public void update(float dt) {
        if(active) {
            super.update(dt);

//            TODO: Zrobic jako osobna klase do animacji p.s. poprawic texture player_left oraz player_right
            if(rotation < 135 && rotation > 45) {
                sprite.setRegion(playerBackTexture);
            } else if (rotation > -135 && rotation < -45) {
                sprite.setRegion(playerFrontTexture);
            } else if (rotation < -135 || rotation > 135) {
                sprite.setRegion(playerLeftTexture);
            } else if (rotation < 45 && rotation > -45) {
                sprite.setRegion(playerRightTexture);
            }

            sprite.setX(x);
            sprite.setY(y);

            setRotation(game.camera.getPositionMouseWithCamera().x, game.camera.getPositionMouseWithCamera().y);
            setVelocityMove(rotation);
            createBody(sprite);

            input();
        }
    }

    @Override
    public void draw() {
        sprite.draw(game.spriteBatch);
    }
}