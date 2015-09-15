package com.dobromir.rebellion.map.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.dobromir.rebellion.Game;
import com.dobromir.rebellion.constantly.KeysConfig;
import com.dobromir.rebellion.data.ImageCache;
import com.dobromir.rebellion.data.Sounds;
import com.dobromir.rebellion.sprites.MovingSprite;
import com.dobromir.rebellion.utils.io.Console;

public class Player extends MovingSprite{

    private boolean dead = false;

    private TextureRegion playerFrontTexture;
    private TextureRegion playerBackTexture;
    private TextureRegion playerLeftTexture;
    private TextureRegion playerRightTexture;

    public Player(Game game, float x, float y, float speed) {
        super("player_front", game, x, y, speed);

        playerFrontTexture = ImageCache.getTexture("player_front");
        playerBackTexture = ImageCache.getTexture("player_back");
        playerLeftTexture = ImageCache.getTexture("player_left");
        playerRightTexture = ImageCache.getTexture("player_right");
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
                setTexture(playerBackTexture);
            } else if (rotation > -135 && rotation < -45) {
                setTexture(playerFrontTexture);
            } else if (rotation < -135 || rotation > 135) {
                setTexture(playerLeftTexture);
            } else if (rotation < 45 && rotation > -45) {
                setTexture(playerRightTexture);
            }

            setRotation(game.camera.getPositionMouseWithCamera().x, game.camera.getPositionMouseWithCamera().y);
            setDirectionMove(rotation);
        }
    }

    @Override
    public void draw() {
        super.draw();
        input();
    }
}