package com.dobromir.rebellion.map.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.dobromir.rebellion.Game;
import com.dobromir.rebellion.constantly.KeysConfig;
import com.dobromir.rebellion.data.ImageCache;
import com.dobromir.rebellion.map.Map;
import com.dobromir.rebellion.sprites.GameSprite;
import com.dobromir.rebellion.sprites.MovingSprite;

import java.util.Objects;

public class Enemy extends MovingSprite {

    private Sprite sprite;

    private int distance;

    public Enemy(Game game, float x, float y, float speed) {
        super(game, x, y, speed);

        sprite = new Sprite(ImageCache.getTexture("watchman_front"));
//TODO: A moze by tak, jeszcze jeden konstruktor z Sprite zamiast zwyklym texture???!!!
        width = sprite.getRegionWidth();
        height = sprite.getRegionHeight();
    }

    public void input() {
        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            moveForward();
            System.out.println();
        } if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            moveBack();
        } if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            moveLeft();
        } if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            moveRight();
        }
    }

    @Override
    public void update(float dt) {
        if(active) {
            super.update(dt);

            sprite.setRotation(rotation);
            sprite.setX(x);
            sprite.setY(y);

            setRotation(game.camera.getPositionMouseWithCamera().x, game.camera.getPositionMouseWithCamera().y);
            setDirectionMove(rotation);

            createBody(sprite);

            distance = Math.sqrt(Math.pow(Objects.get("Player").getX() - getX(), 2) + Math.pow(Objects.get("Player").getX() - getY(), 2)); // prawie dobrze

            if (distance > 100){
                moveForward();
            }

        }
    }

    @Override
    public void draw() {
        sprite.draw(game.spriteBatch);
        if(active) input();
    }
}
