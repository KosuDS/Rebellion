package com.dobromir.rebellion.map.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.dobromir.rebellion.Game;
import com.dobromir.rebellion.data.ImageCache;
import com.dobromir.rebellion.sprites.GameSprite;

public class Door extends GameSprite {

    private float count;
    private float speed;

    private boolean state;
    private boolean orientation;

    private Sprite sprite;

    public Door(Game game, float x, float y, boolean state, boolean orientation) {
        super(game, x, y);

        this.state = state;
        this.orientation = orientation;

        sprite = new Sprite(ImageCache.getTexture("door"));
        sprite.setRotation(orientation ? 90 : 0);
        sprite.setPosition(x, y);

        height = orientation ? sprite.getHeight() : sprite.getWidth();
        width = orientation ? sprite.getWidth() : sprite.getHeight();

        if(orientation) count = state ? height : 0;
        else count = state ? width : 0;

        speed = 20;

        game.screen.elements.add(this);
    }

    public Rectangle getRectangle() {
        return new Rectangle(x, y, width, height);
    }

    @Override
    public void update(float dt) {
        if(Gdx.input.justTouched()) {
            float x = (Gdx.input.getX() - (game.screenWidth / 2)) + game.camera.position.x;
            float y = ((game.screenHeight - Gdx.input.getY()) - (game.screenHeight / 2)) + game.camera.position.y;

            if(getRectangle().overlaps(new Rectangle(x, y, 3, 3))){
                state = !state;
            }
        }

        if(orientation){ //Drzwi pionowo
            if(state &&  count < width) {
                y += speed * dt;
                count += speed * dt;

            } else if (!state && count > 0) {
                y -= speed * dt;
                count -= speed * dt;
            }
        } else { // Drzwi poziomo
            if(state &&  count < height) {
                x += speed * dt;
                count += speed * dt;

            } else if (!state && count > 0) {
                x -= speed * dt;
                count -= speed * dt;
            }
        }

        sprite.setPosition(x, y);
    }

    @Override
    public void draw() {
        sprite.draw(game.spriteBatch);
    }
}