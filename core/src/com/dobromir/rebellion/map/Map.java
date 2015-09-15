package com.dobromir.rebellion.map;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Polygon;
import com.dobromir.rebellion.Game;
import com.dobromir.rebellion.data.Maps;
import com.dobromir.rebellion.map.objects.Enemy;
import com.dobromir.rebellion.map.objects.Player;
import com.dobromir.rebellion.sprites.GameSprite;
import com.dobromir.rebellion.utils.io.Console;
import com.sun.xml.internal.ws.client.sei.ResponseBuilder;

import java.util.HashMap;

public class Map {
    protected Game game;

    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer mapRenderer;
    private ShapeRenderer shapeRenderer;

    private String cameraClumping;
    private boolean drawShape;
    private boolean drawObjects;
    private boolean drawTiles;
    private boolean active;

    private HashMap<String, GameSprite> objects;

    public Map(Game game) {
        this.game = game;

        tiledMap = Maps.getTiledMap(game.gameData.mapName);
        mapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setColor(Color.BLUE);

        objects = new HashMap<>();

        objects.put("Player", new Player(game, 110, 110, 100));
        objects.put("Enemy", new Enemy(game, 210, 210, 100));

        cameraClumping = "Player";
        drawShape = true;
        drawObjects = true;
        drawTiles = true;
        active = true;
    }

    public void checkCollision() {

        if(objects.get("Player").isCollisionWith(objects.get("Enemy").getBody())) {
            Console.puts("COLLLLLLIDISDSDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
        }

//        for (GameSprite object : objects.values()) {
//            for (GameSprite object2 : objects.values()) {
//                if(object.isCollisionWith(object2.getBody())) {
//                    Console.puts("COLLLLLLIDISDSDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
//                }
//            }
//        }
    }

    public void update(float dt) {

        if(active){
            if(!cameraClumping.equals("")) {
                GameSprite object = objects.get(cameraClumping);
                game.camera.position.set(object.x, object.y, 0);
            }

            for(GameSprite object : objects.values()) {
                if(object.active) object.update(dt);
            }

            checkCollision();
        }
    }

    public void draw() {
        if(drawTiles) {
            mapRenderer.setView(game.camera);
            mapRenderer.render();
        }

        if(drawObjects) {
            game.spriteBatch.begin();
            for (GameSprite object : objects.values()) {
                object.draw();
            }
            game.spriteBatch.end();
        }

        if(drawShape) {
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setProjectionMatrix(game.camera.combined);
            for (GameSprite object : objects.values()){
                shapeRenderer.polygon(object.getBody().getVertices());
            }
            shapeRenderer.end();
        }
    }

    public void dispose() {
        tiledMap.dispose();
        mapRenderer.dispose();
    }

    public HashMap<String, GameSprite> getObjects() {
        return objects;
    }

    public void setObjects(HashMap<String, GameSprite> objects) {
        this.objects = objects;
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
