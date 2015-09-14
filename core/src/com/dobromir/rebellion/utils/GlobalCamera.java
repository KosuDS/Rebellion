package com.dobromir.rebellion.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

public class GlobalCamera extends OrthographicCamera {

    private Vector2 positionLeftCorner;
    private Vector2 positionMouse;

    public GlobalCamera(float viewportWidth, float viewportHeight) {
        super(viewportWidth, viewportHeight);
    }

    @Override
    public void update() {
        super.update();

        positionLeftCorner = new Vector2(position.x - (viewportWidth / 2), position.y - (viewportHeight / 2));
        positionMouse = new Vector2(Gdx.input.getX(), -(Gdx.input.getY() - viewportHeight));
    }
}
