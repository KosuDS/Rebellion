package com.dobromir.rebellion.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.dobromir.rebellion.Game;

import com.dobromir.rebellion.constantly.KeysConfig;
import com.dobromir.rebellion.map.Map;

import com.dobromir.rebellion.sprites.GameSprite;
import com.dobromir.rebellion.ui.InformationPanel;


public class GameScreen extends Screen{

	private InformationPanel informationPanel;
    private Map map;

	public GameScreen(Game game) {
		super(game);
	}

	@Override
	public void createScreen() {

		game.camera.setToOrtho(false, game.screenWidth, game.screenHeight);
		game.camera.update();

		if(elements.size() == 0){
            map = new Map(game);
		}
		
		game.gameData.gameMode = Game.GAME_STATE_PLAY;
		
		informationPanel = new InformationPanel(game, 0, 0);
	}

	@Override
	public void update(float dt) {
        ///////////////////////////// UPDATE /////////////////////////////
        map.update(dt);

        informationPanel.update(dt);
        informationPanel.setInformation(map.getObjects().get("Player").getX(), map.getObjects().get("Player").getX(), game.camera.position.x, game.camera.position.y, map.getCameraClumping(), map.isDrawShape(), game.gameData.mapName);

        game.camera.update();
        game.spriteBatch.setProjectionMatrix(game.camera.combined);


        ///////////////////////////// RENDERER /////////////////////////////
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        map.draw();

        game.spriteBatch.enableBlending();
        game.spriteBatch.begin();

        for (GameSprite element : elements) {
            if(!element.visible) continue;

            if(element.texture == null) {
                element.draw();
            } else {
                game.spriteBatch.draw(element.texture, element.x, element.y);
            }
        }

        game.spriteBatch.end();


        ///////////////////////////// INPUT /////////////////////////////
        if (Gdx.input.isKeyJustPressed(KeysConfig.DRAW_INFORMATION)) informationPanel.setVisible(!informationPanel.getVisible());

		if(Gdx.input.isKeyJustPressed(KeysConfig.CHANGE_CAMERA)) {
            map.setCameraClumping(map.getCameraClumping().equals("Player") ? "" : "Player");
            map.player.setActive(!map.player.active);
        }
		if(Gdx.input.isKeyJustPressed(KeysConfig.DRAW_SHAPE)) map.setDrawShape(!map.isDrawShape());

		if(map.getCameraClumping().equals("")) {
			if(Gdx.input.isKeyPressed(KeysConfig.MOVE_FORWARD)){
				game.camera.translate(0, 32);
			} if(Gdx.input.isKeyPressed(KeysConfig.MOVE_BACK)){
				game.camera.translate(0, -32);
			} if(Gdx.input.isKeyPressed(KeysConfig.MOVE_LEFT)){
				game.camera.translate(-32, 0);
			} if(Gdx.input.isKeyPressed(KeysConfig.MOVE_RIGHT)){
				game.camera.translate(32, 0);
			}
		}

		if(Gdx.input.isKeyJustPressed(KeysConfig.BACK_MAIN_MENU)) {
			game.setScreen("MainMenuScreen");
        }
	}
	
	@Override
	public void resize(int arg0, int arg1) {
		game.camera.setToOrtho(false,  Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		game.camera.update();
		
		super.resize(arg0, arg1);
	}
	
	@Override
	public void dispose() {
		map.dispose();
		super.dispose();
	}
}