package com.dobromir.rebellion.map.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.dobromir.rebellion.Game;
import com.dobromir.rebellion.constantly.KeysConfig;
import com.dobromir.rebellion.data.ImageCache;
import com.dobromir.rebellion.data.Sounds;
import com.dobromir.rebellion.sprites.MovingSprite;

public class NewPlayer extends MovingSprite{

    private boolean dead = false;

    public NewPlayer(Game game, float x, float y, float speed) {
        super("player_front", game, x, y, speed);
    }

    public void shoot() {
        Sounds.play("shot", 1f, 1f);
    }

    @Override
    public void reset() {
        visible = true;
        dead = false;
        active = true;

        x = 0;
        y = 0;
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
//            TODO: Usunac stara klase MovinSprite a na jej miejsce wstawic to nowe cudo z nowymi kolizjami z poligonami i takimi bajerami + RotationSprite - dzis jest juz na to za pozno...
            float dirX = (Gdx.input.getX() - game.screenWidth / 2) + x - width / 2;
            float dirY = (Gdx.input.getY() - game.screenHeight / 2) + y + height / 2;

            rotation = -MathUtils.radiansToDegrees * MathUtils.atan2(dirY - y , dirX - x);

            float rotationVelocityX = (float) (Math.cos(rotation * MathUtils.degreesToRadians));
            float rotationVelocityY = (float) (Math.sin(rotation * MathUtils.degreesToRadians));

            setDirectionMove(rotationVelocityX, rotationVelocityY, dt);
            input();
        }
    }
}