package com.dobromir.rebellion.sprites;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.MathUtils;
import com.dobromir.rebellion.Game;
import com.dobromir.rebellion.data.Maps;
import com.dobromir.rebellion.utils.io.Console;

public class MovingSprite extends PhysicSprite {

    private TiledMapTileLayer collisionLayer;

    private float speed;
    private float dtSpeed;
    private float[] velocityMove;
    private float[] directionMove;

    public MovingSprite(String textureName, Game game, float x, float y, float speed) {
        super(textureName, game, x, y);
        collisionLayer = (TiledMapTileLayer) Maps.getTiledMap(game.gameData.mapName).getLayers().get(0);

        this.speed = speed;
        this.directionMove = new float[] {0, 0};
        this.velocityMove = new float[] {0, 0};
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
        chceckCollision();

        x += directionMove[0] * dtSpeed;
        y += directionMove[1] * dtSpeed;
    }

    public void moveBack() {
        setVelocity();
        chceckCollision();

        x -= directionMove[0] * dtSpeed;
        y -= directionMove[1] * dtSpeed;
    }

    public void moveLeft() {
        setVelocity();
        chceckCollision();

        x -= directionMove[1] * dtSpeed;
        y += directionMove[0] * dtSpeed;
    }

    public void moveRight() {
        setVelocity();
        chceckCollision();

        x += directionMove[1] * dtSpeed;
        y -= directionMove[0] * dtSpeed;
    }

    public void chceckCollision() {
        float oldX = x, oldY = y;
        boolean collisionX = false, collisionY = false;

        x = x + velocityMove[0];

        if(velocityMove[0] < 0){
            collisionX = collidesLeft();
        }else if(velocityMove[0] > 0){
            collisionX = collidesRight();
        }

        if(collisionX) {
            x = oldX;
            directionMove[0] = 0;
        }

        y = y + velocityMove[1];

        if(velocityMove[1] < 0){
            collisionY = collidesBottom();
        }else if(velocityMove[1] > 0){
            collisionY = collidesTop();
        }

        if(collisionY) {
            y = oldY;
            directionMove[1] = 0;
        }
    }

    private boolean isCellBlocked(float x, float y) {
        TiledMapTileLayer.Cell cell = collisionLayer.getCell((int) (x / collisionLayer.getTileWidth()), (int) (y / collisionLayer.getTileHeight()));
        return cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey("collision");
    }

    public boolean collidesRight() {
        for(float step = 0; step < height; step += collisionLayer.getTileHeight() / 2)
            if(isCellBlocked(x + width, y + step))
                return true;
        return false;
    }

    public boolean collidesLeft() {
        for(float step = 0; step < height; step += collisionLayer.getTileHeight() / 2)
            if(isCellBlocked(x, y + step))
                return true;
        return false;
    }

    public boolean collidesTop() {
        for(float step = 0; step < width; step += collisionLayer.getTileWidth() / 2)
            if(isCellBlocked(x + step, y + height))
                return true;
        return false;

    }

    public boolean collidesBottom() {
        for(float step = 0; step < width; step += collisionLayer.getTileWidth() / 2)
            if(isCellBlocked(x + step, y))
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
