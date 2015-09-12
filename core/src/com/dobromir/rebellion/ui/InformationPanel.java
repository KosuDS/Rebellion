package com.dobromir.rebellion.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.dobromir.rebellion.Game;

import com.dobromir.rebellion.constantly.KeysConfig;
import com.dobromir.rebellion.data.Fonts;
import com.dobromir.rebellion.sprites.GameSprite;

public class InformationPanel extends GameSprite{
	
	private float playerPositionX, playerPositionY;
	private float cameraPositionX, cameraPositionY;
	
	private float framePerSeconds;
	
	private String cameraClumping;
	private String mapName;
	
	private boolean shapeRenderer;
	private boolean informationRenderer;

	private BitmapFont font;

	private String textToDraw;
	
	public InformationPanel(Game game, float x, float y) {
		super(game, x, y);
		
		font = Fonts.getFont("default");
		font.setColor(Color.WHITE);
		
		game.screen.elements.add(this);
	}
	
	public void setInformation(float playerPositionX, float playerPositionY, float cameraPositionX, float cameraPositionY, String cameraClumping, boolean shapeRenderer, String mapName) {
		this.playerPositionX = playerPositionX;
		this.playerPositionY = playerPositionY;
		this.cameraPositionX = cameraPositionX;
		this.cameraPositionY = cameraPositionY;
		this.cameraClumping = cameraClumping;
		this.shapeRenderer = shapeRenderer;
		this.mapName = mapName;
	}

	@Override
	public void draw() {
		setX(game.camera.position.x - (game.screenWidth / 2) + 20);
		setY(game.camera.position.y + (game.screenHeight / 2) - 20);
		
		font.draw(game.spriteBatch, textToDraw, x, y, 700, 10, true);
	}

	@Override
	public void update(float dt) {
		framePerSeconds = Gdx.graphics.getFramesPerSecond();
		
		textToDraw = "FPS: " + framePerSeconds + "\n\n" + 
					 "player_x: " + playerPositionX + " - player_y: " + playerPositionY + "\n" +
					 "camera_x: " + cameraPositionX + " - camera_y: " + cameraPositionY + "\n" +
					 "camera_clumping: " + cameraClumping + "\n" +
					 "shape_renderer: " + shapeRenderer + "\n" +
					 "map_name: " + mapName + "\n";
	}

	public float getPlayerPositionX() {
		return playerPositionX;
	}

	public void setPlayerPositionX(float playerPositionX) {
		this.playerPositionX = playerPositionX;
	}

	public float getPlayerPositionY() {
		return playerPositionY;
	}

	public void setPlayerPositionY(float playerPositionY) {
		this.playerPositionY = playerPositionY;
	}

	public float getCameraPositionX() {
		return cameraPositionX;
	}

	public void setCameraPositionX(float cameraPositionX) {
		this.cameraPositionX = cameraPositionX;
	}

	public float getCameraPositionY() {
		return cameraPositionY;
	}

	public void setCameraPositionY(float cameraPositionY) {
		this.cameraPositionY = cameraPositionY;
	}

	public float getFramePerSeconds() {
		return framePerSeconds;
	}

	public void setFramePerSeconds(float framePerSeconds) {
		this.framePerSeconds = framePerSeconds;
	}

	public String getCameraClumping() {
		return cameraClumping;
	}

	public void setCameraClumping(String cameraClumping) {
		this.cameraClumping = cameraClumping;
	}

	public String getMapName() {
		return mapName;
	}

	public void setMapName(String mapName) {
		this.mapName = mapName;
	}

	public boolean isShapeRenderer() {
		return shapeRenderer;
	}

	public void setShapeRenderer(boolean shapeRenderer) {
		this.shapeRenderer = shapeRenderer;
	}

	public boolean isInformationRenderer() {
		return informationRenderer;
	}

	public void setInformationRenderer(boolean informationRenderer) {
		this.informationRenderer = informationRenderer;
	}
}
