package com.dobromir.rebellion.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.dobromir.rebellion.Game;
import com.dobromir.rebellion.constantly.KeysConfig;
import com.dobromir.rebellion.data.Maps;
import com.dobromir.rebellion.elements.Player;
import com.dobromir.rebellion.sprites.GameSprite;

import java.util.HashMap;

public class Map {
    protected Game game;

    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer mapRenderer;
    private ShapeRenderer shapeRenderer;

    private String cameraClumping;
    private boolean drawShape;

    private HashMap<String, GameSprite> objects;
    public Player player;

    public Map(Game game) {
        this.game = game;

        tiledMap = Maps.getTiledMap(game.gameData.mapName);
        mapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        shapeRenderer = new ShapeRenderer();

        objects = new HashMap<>();

        objects.put("Player", new Player(game, 110, 110));
        player = (Player) getObjects().get("Player");

        cameraClumping = "Player";
        drawShape = true;
    }

    public void update(float dt) {
        if(!cameraClumping.equals("")) {
            GameSprite object = objects.get(cameraClumping);
            game.camera.position.set(object.x, object.y, 0);
        }

        for(GameSprite object : objects.values()) {
            if(object.active) object.update(dt);
        }
    }

    public void draw() {

        mapRenderer.setView(game.camera);
        mapRenderer.render();

        if(drawShape) {
            shapeRenderer.setColor(Color.BLUE);
            shapeRenderer.setProjectionMatrix(game.camera.combined);

            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.rect(player.getRotationMinX(), player.getRotationMinY(), player.getRotationWidth(), player.getRotationHeight());
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
}
