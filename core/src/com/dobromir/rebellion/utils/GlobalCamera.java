package com.dobromir.rebellion.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

public class GlobalCamera extends OrthographicCamera {

    private Vector2 positionLeftCorner;
    private Vector2 positionMouse;
    private Vector2 positionMouseWithCamera;

    public GlobalCamera(float viewportWidth, float viewportHeight) {
        super(viewportWidth, viewportHeight);
    }

    @Override
    public void update() {
        super.update();

        positionLeftCorner = new Vector2(position.x - (viewportWidth / 2), position.y - (viewportHeight / 2));
        positionMouse = new Vector2(Gdx.input.getX(), -(Gdx.input.getY() - viewportHeight));
        positionMouseWithCamera = new Vector2(getPositionMouse().x + getPositionLeftCorner().x, getPositionMouse().y + getPositionLeftCorner().y);
    }

    public Vector2 getPositionLeftCorner() {
        return positionLeftCorner;
    }

    public void setPositionLeftCorner(Vector2 positionLeftCorner) {
        this.positionLeftCorner = positionLeftCorner;
    }

    public Vector2 getPositionMouse() {
        return positionMouse;
    }

    public void setPositionMouse(Vector2 positionMouse) {
        this.positionMouse = positionMouse;
    }

    public Vector2 getPositionMouseWithCamera() {
        return positionMouseWithCamera;
    }

    public void setPositionMouseWithCamera(Vector2 positionMouseWithCamera) {
        this.positionMouseWithCamera = positionMouseWithCamera;
    }
}
