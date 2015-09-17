package com.dobromir.rebellion.sprites;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.MathUtils;
import com.dobromir.rebellion.Game;
import com.dobromir.rebellion.data.Maps;

public class MovingSprite extends GameSprite {

    private TiledMapTileLayer collisionLayer;

    private float speed;
    private float dtSpeed;
    private float[] directionMove = new float[] {0, 0};
    private float[] velocityMove = new float[] {0, 0};

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

    public void setDirection() {
        directionMove[0] = velocityMove[0] * dtSpeed;
        directionMove[1] = velocityMove[1] * dtSpeed;
    }

    public void moveForward() {
        setDirection();
        checkCollision();

        x += velocityMove[0] * dtSpeed;
        y += velocityMove[1] * dtSpeed;
    }

    public void moveBack() {
        setDirection();
        checkCollision();

        x -= velocityMove[0] * dtSpeed;
        y -= velocityMove[1] * dtSpeed;
    }

    public void moveLeft() {
        setDirection();
        checkCollision();

        x -= velocityMove[1] * dtSpeed;
        y += velocityMove[0] * dtSpeed;
    }

    public void moveRight() {
        setDirection();
        checkCollision();

        x += velocityMove[1] * dtSpeed;
        y -= velocityMove[0] * dtSpeed;
    }

    public void checkCollision() {
        boolean collisionX = false, collisionY = false;

        if(directionMove[0] < 0){
            collisionX = collidesLeft();
        }else if(directionMove[0] > 0){
            collisionX = collidesRight();
        }

        if(collisionX) {
            velocityMove[0] = 0;
        }

        if(directionMove[1] < 0){
            collisionY = collidesBottom();
        }else if(directionMove[1] > 0){
            collisionY = collidesTop();
        }

        if(collisionY) {
            velocityMove[1] = 0;
        }
    }

    private boolean isCellBlocked(float x, float y) {
        TiledMapTileLayer.Cell cell = collisionLayer.getCell((int) (x / collisionLayer.getTileWidth()), (int) (y / collisionLayer.getTileHeight()));
        return cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey("collision");
    }

    public boolean collidesRight() {
        for(float step = 0; step < height; step += collisionLayer.getTileHeight() / 2)
            if(isCellBlocked(x + width + directionMove[0], y + step + directionMove[1]))
                return true;
        return false;
    }

    public boolean collidesLeft() {
        for(float step = 0; step < height; step += collisionLayer.getTileHeight() / 2)
            if(isCellBlocked(x + directionMove[0], y + step + directionMove[1]))
                return true;
        return false;
    }

    public boolean collidesTop() {
        for(float step = 0; step < width; step += collisionLayer.getTileWidth() / 2)
            if(isCellBlocked(x + step + directionMove[0], y + height + directionMove[1]))
                return true;
        return false;

    }

    public boolean collidesBottom() {
        for(float step = 0; step < width; step += collisionLayer.getTileWidth() / 2)
            if(isCellBlocked(x + step + directionMove[0], y + directionMove[1]))
                return true;
        return false;
    }

    public void setRotation(float dirX, float dirY) {
        rotation = MathUtils.radiansToDegrees * MathUtils.atan2(dirY - y , dirX - x);
    }

    public void setVelocityMove(float rotation) {
        setDirectionMove((float) (Math.cos(rotation * MathUtils.degreesToRadians)), (float) (Math.sin(rotation * MathUtils.degreesToRadians)));
    }

    public void setDirectionMove(float x, float y) {
        velocityMove = new float[] {x, y};
    }

    public float[] getVelocityMove() {
        return velocityMove;
    }

    public void setDirectionMove(float[] directionMove) {
        this.velocityMove = directionMove;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}
