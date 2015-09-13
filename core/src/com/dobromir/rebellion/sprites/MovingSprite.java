package com.dobromir.rebellion.sprites;

import com.dobromir.rebellion.Game;

public class MovingSprite extends PhysicSprite {

    private float speed;
    private float[] directionMove;

    public MovingSprite(Game game, float x, float y, float speed) {
        super(game, x, y);

        this.speed = speed;
        this.directionMove = new float[] {0, 0};
    }

    public MovingSprite(String textureName, Game game, float x, float y, float speed) {
        super(textureName, game, x, y);

        this.speed = speed;
        this.directionMove = new float[] {0, 0};
    }

    public void moveForward() {
        x += directionMove[0] * speed;
        y += directionMove[1] * speed;
    }

    public void moveBack() {
        x -= directionMove[0] * speed;
        y -= directionMove[1] * speed;
    }

    public void moveLeft() {
        x -= directionMove[1] * speed;
        y += directionMove[0] * speed;
    }

    public void moveRight() {
        x += directionMove[1] * speed;
        y -= directionMove[0] * speed;
    }

    public void setDirectionMove(float x, float y, float dt) {
        directionMove = new float[] {x * dt, y * dt};
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
