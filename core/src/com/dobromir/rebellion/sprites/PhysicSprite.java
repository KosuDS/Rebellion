package com.dobromir.rebellion.sprites;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.dobromir.rebellion.Game;

public class PhysicSprite extends GameSprite {

    private Polygon body;

    public PhysicSprite(Game game, float x, float y) {
        super(game, x, y);

        body = new Polygon();
    }

    public PhysicSprite(String textureName, Game game, float x, float y) {
        super(textureName, game, x, y);

        body = new Polygon();
    }

    public boolean isCollisionWith(Polygon polygon) {
        return Intersector.overlapConvexPolygons(body, polygon);
    }

    public Polygon getBody() {
        return body;
    }

    public void setBody(Polygon body) {
        this.body = body;
    }
}
