package com.dobromir.rebellion.sprites;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.dobromir.rebellion.Game;
import com.dobromir.rebellion.data.Maps;
import com.dobromir.rebellion.utils.io.Console;

public class MovingSprite extends GameSprite {

    private TiledMapTileLayer collisionLayer;

    private float speed;
    private float dtSpeed;
    private float[] velocityMove = new float[] {0, 0};
    private float[] directionMove = new float[] {0, 0};

    public Polygon cellPolygon;

    public MovingSprite(Game game, float x, float y, float speed) {
        super(game, x, y);
        collisionLayer = (TiledMapTileLayer) Maps.getTiledMap(game.gameData.mapName).getLayers().get(0);

        this.speed = speed;
    }

    public MovingSprite(String textureName, Game game, float x, float y, float speed) {
        super(textureName, game, x, y);
        collisionLayer = (TiledMapTileLayer) Maps.getTiledMap(game.gameData.mapName).getLayers().get(0);

        this.speed = speed;
    }

    @Override
    public void update(float dt) {
        super.update(dt);

        this.dtSpeed = speed * dt;
    }

    public void setVelocity() {
        velocityMove[0] = directionMove[0] * dtSpeed;
        velocityMove[1] = directionMove[1] * dtSpeed;
    }

    public void moveForward() {
        setVelocity();
        checkCollision();

        x += directionMove[0] * dtSpeed;
        y += directionMove[1] * dtSpeed;
    }

    public void moveBack() {
        setVelocity();
        checkCollision();

        x -= directionMove[0] * dtSpeed;
        y -= directionMove[1] * dtSpeed;
    }

    public void moveLeft() {
        setVelocity();
        checkCollision();

        x -= directionMove[1] * dtSpeed;
        y += directionMove[0] * dtSpeed;
    }

    public void moveRight() {
        setVelocity();
        checkCollision();

        x += directionMove[1] * dtSpeed;
        y -= directionMove[0] * dtSpeed;
    }

    public void checkCollision() {
        boolean collisionX = false, collisionY = false;

        if(velocityMove[0] < 0){
            collisionX = collidesLeft();
        }else if(velocityMove[0] > 0){
            collisionX = collidesRight();
        }

        if(collisionX) {
            directionMove[0] = 0;
        }

        if(velocityMove[1] < 0){
            collisionY = collidesBottom();
        }else if(velocityMove[1] > 0){
            collisionY = collidesTop();
        }

        if(collisionY) {
            directionMove[1] = 0;
        }

//        TODO: Okazalo sie ze polygony sa zamkniete wiec nie trzeba powtarzac pierwszego x, y
    }

    private boolean isCellBlocked(float x, float y) {
        TiledMapTileLayer.Cell cell = collisionLayer.getCell((int) (x / collisionLayer.getTileWidth()), (int) (y / collisionLayer.getTileHeight()));
        return cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey("collision");
    }

    public boolean collidesRight() {
        for(float step = 0; step < height; step += collisionLayer.getTileHeight() / 2)
            if(isCellBlocked(x + width + velocityMove[0], y + step + velocityMove[1]))
                return true;
        return false;
    }

    public boolean collidesLeft() {
        for(float step = 0; step < height; step += collisionLayer.getTileHeight() / 2)
            if(isCellBlocked(x + velocityMove[0], y + step + velocityMove[1]))
                return true;
        return false;
    }

    public boolean collidesTop() {
        for(float step = 0; step < width; step += collisionLayer.getTileWidth() / 2)
            if(isCellBlocked(x + step + velocityMove[0], y + height + velocityMove[1]))
                return true;
        return false;

    }

    public boolean collidesBottom() {
        for(float step = 0; step < width; step += collisionLayer.getTileWidth() / 2)
            if(isCellBlocked(x + step + velocityMove[0], y + velocityMove[1]))
                return true;
        return false;
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
