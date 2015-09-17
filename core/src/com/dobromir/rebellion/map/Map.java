package com.dobromir.rebellion.map;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.dobromir.rebellion.Game;
import com.dobromir.rebellion.data.Maps;
import com.dobromir.rebellion.map.objects.Enemy;
import com.dobromir.rebellion.map.objects.Player;
import com.dobromir.rebellion.sprites.GameSprite;
import com.dobromir.rebellion.utils.io.Console;

public class Map {
    protected Game game;

    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer mapRenderer;
    private ShapeRenderer shapeRenderer;

    private String cameraClumping;
    private boolean drawShape;
    private boolean drawTiles;
    private boolean active;

    public Map(Game game) {
        this.game = game;

        tiledMap = Maps.getTiledMap(game.gameData.mapName);
        mapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setColor(Color.BLUE);

        game.screen.elements.put("Player", new Player(game, 110, 110, 200));
        game.screen.elements.put("Enemy", new Enemy(game, 210, 210, 100));
        game.screen.elements.put("Block", new GameSprite("alarm", game, 0, 0));

        cameraClumping = "Player";
        drawShape = true;
        drawTiles = true;
        active = true;
    }

    public void update(float dt) {
        if(active){
            if(!cameraClumping.equals("")) {
                GameSprite object = game.screen.elements.get(cameraClumping);
                game.camera.position.set(object.x, object.y, 0);
            }

            for(GameSprite object : game.screen.elements.values()) {
                if(object.active) object.update(dt);
            }
        }
    }

    public void draw() {
        if(drawTiles) {
            mapRenderer.setView(game.camera);
            mapRenderer.render();
        }

        if(drawShape) {
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setProjectionMatrix(game.camera.combined);
            for (GameSprite object : game.screen.elements.values()){
                shapeRenderer.polygon(object.getBody().getVertices());
            }
            shapeRenderer.end();
        }
    }

    public void dispose() {
        tiledMap.dispose();
        mapRenderer.dispose();
    }

    public String getCameraClumping() {
        return cameraClumping;
    }

    public void setCameraClumping(String cameraClumping) {
        this.cameraClumping = cameraClumping;
    }

    public boolean isDrawShape() {
        return drawShape;
    }

    public void setDrawShape(boolean drawShape) {
        this.drawShape = drawShape;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}