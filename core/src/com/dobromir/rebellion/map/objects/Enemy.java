package com.dobromir.rebellion.map.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.dobromir.rebellion.Game;
import com.dobromir.rebellion.data.ImageCache;
import com.dobromir.rebellion.sprites.GameSprite;
import com.dobromir.rebellion.sprites.MovingSprite;
import com.dobromir.rebellion.utils.io.Console;

import java.util.HashMap;

public class Enemy extends MovingSprite {

    private Sprite sprite;

    public Enemy(Game game, float x, float y, float speed) {
        super(game, x, y, speed);

        sprite = new Sprite(ImageCache.getTexture("watchman_front"));
//TODO: A moze by tak, jeszcze jeden konstruktor z Sprite zamiast zwyklym texture???!!!
        width = sprite.getRegionWidth();
        height = sprite.getRegionHeight();

        setRotation(90);
    }

    public void input() {
        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            moveForward();
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

            setRotation(game.screen.elements.get("Player").getX(), game.screen.elements.get("Player").getY());
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


