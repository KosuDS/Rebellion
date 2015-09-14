package com.dobromir.rebellion.sprites;

import com.badlogic.gdx.math.MathUtils;
import com.dobromir.rebellion.Game;
import com.dobromir.rebellion.utils.io.Console;

public class MovingSprite extends PhysicSprite {

    private float speed;
    private float dtSpeed;
    private float[] directionMove;

    public MovingSprite(Game game, float x, float y, float speed) {
        super(game, x, y);

        this.speed = speed;
        this.dtSpeed = speed;
        this.directionMove = new float[] {0, 0};
    }

    public MovingSprite(String textureName, Game game, float x, float y, float speed) {
        super(textureName, game, x, y);

        this.speed = speed;
        this.directionMove = new float[] {0, 0};
    }

    @Override
    public void update(float dt) {
        super.update(dt);

        this.dtSpeed = speed * dt;
    }

    public void moveForward() {
        x += directionMove[0] * dtSpeed;
        y += directionMove[1] * dtSpeed;
    }

    public void moveBack() {
        x -= directionMove[0] * dtSpeed;
        y -= directionMove[1] * dtSpeed;
    }

    public void moveLeft() {
        x -= directionMove[1] * dtSpeed;
        y += directionMove[0] * dtSpeed;
    }

    public void moveRight() {
        x += directionMove[1] * dtSpeed;
        y -= directionMove[0] * dtSpeed;
    }

    public void setRotation(float dirX, float dirY) {
        rotation = MathUtils.radiansToDegrees * MathUtils.atan2(dirY - y , dirX - x);
    }

    public void setDirectionMove(float rotation) {
        setDirectionMove((float) (Math.cos(rotation * MathUtils.degreesToRadians)), (float) (Math.sin(rotation * MathUtils.degreesToRadians)));
    }

    public void setDirectionMove(float x, float y) {
        directionMove = new float[] {x, y};
    }

    public float[] getDirectionMove() {
        return directionMove;
    }

    public void setDirectionMove(float[] directionMove) {
        this.directionMove = directionMove;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}
