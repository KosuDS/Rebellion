package com.dobromir.rebellion;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.dobromir.rebellion.data.*;
import com.dobromir.rebellion.utils.GlobalCamera;

import java.awt.*;

public class RebellionGame extends Game{

	@Override
	public void create() {

		super.create();

		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();

		camera = new GlobalCamera(screenWidth, screenHeight);
		camera.position.set(screenWidth * 0.5f, screenHeight * 0.5f, 0);

		Sounds.load();
		ImageCache.load();
		Maps.load();
		Fonts.load();
		Skins.load();

		gameData = new GameData(this);
		spriteBatch = new SpriteBatch();

		setScreen("MainMenuScreen");
	}

	@Override
	public void dispose() {
		if (screen != null) screen.dispose();
	}

	@Override
	public void pause() {
		if (screen != null) screen.pause();
	}

	@Override
	public void render() {
		if (screen != null) {
			screen.update(Gdx.graphics.getDeltaTime());
		} else {

			Gdx.gl.glClearColor(0, 0, 0, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		}
	}

	@Override
	public void resize(int arg0, int arg1) {
		screenWidth = arg0;
		screenHeight = arg1;
		if (screen != null) screen.resize(arg0, arg1);
	}

	@Override
	public void resume() {
		if (screen != null) screen.resume();
	}
}
