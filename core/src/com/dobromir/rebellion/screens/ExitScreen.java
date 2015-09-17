package com.dobromir.rebellion.screens;

import com.badlogic.gdx.Gdx;
import com.dobromir.rebellion.Game;
import com.dobromir.rebellion.sprites.GameSprite;

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
		game.spriteBatch.begin();
		for (GameSprite element : elements.values()) {
			if(!element.visible) continue;

			if(element.texture == null) {
				element.draw();
			} else {
				game.spriteBatch.draw(element.texture, element.x, element.y);
			}
		}
		game.spriteBatch.end();
	}

}
