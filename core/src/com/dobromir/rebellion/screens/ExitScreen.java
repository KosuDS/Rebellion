package com.dobromir.rebellion.screens;

import com.badlogic.gdx.Gdx;
import com.dobromir.rebellion.Game;

public class ExitScreen extends Screen{
	
	public ExitScreen(Game game) {
		super(game);
		
		Gdx.app.exit();
	}

	@Override
	public void createScreen() {

	}

	@Override
	public void update(float dt) {

	}

}
