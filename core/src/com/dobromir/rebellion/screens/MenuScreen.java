package com.dobromir.rebellion.screens;

import com.dobromir.rebellion.sprites.GameSprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.dobromir.rebellion.Game;

public class MenuScreen extends Screen{
	

	public MenuScreen(Game game) {
		super(game);
	}
      
    @Override  
    public void createScreen() {  
          
        if (elements.size() == 0) {  
              
            GameSprite logo = new GameSprite ("logo", game, game.screenWidth * 0.5f,  game.screenHeight * 0.7f);  
            logo.width = game.screenWidth;
              
            elements.add(logo); 
        }  
    }  
  
    @Override  
    public void update(float dt) {  
          
        if (Gdx.input.justTouched()) {  
            Gdx.app.log("GameScreen", "Set");  
            game.setScreen("GameScreen");  
          
        } else {  
            Gdx.gl.glClearColor(1, 1, 1, 1);  
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);  
              
            game.camera.update();  
              
            game.spriteBatch.setProjectionMatrix(game.camera.combined); 
            game.spriteBatch.enableBlending(); 
            game.spriteBatch.begin(); 
            for (GameSprite element : elements) {
            	game.spriteBatch.draw(element.texture, element.x, element.y);
			}
            game.spriteBatch.end(); 
        }  
  
    }
}
