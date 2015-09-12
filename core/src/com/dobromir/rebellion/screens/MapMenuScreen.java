package com.dobromir.rebellion.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.dobromir.rebellion.data.Skins;
import com.dobromir.rebellion.sprites.GameSprite;
import com.dobromir.rebellion.ui.ButtonBox;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.dobromir.rebellion.Game;

public class MapMenuScreen extends Screen{
	
	private ButtonBox buttonBox;
	
    public MapMenuScreen(Game game) {
		super(game);
	}

	@Override  
    public void createScreen() {  
          
		game.camera.setToOrtho(false, game.screenWidth, game.screenHeight);
		game.camera.update();
		
        if (elements.size() == 0) {  
        	buttonBox = new ButtonBox(game, 0, 0, Skins.getSkin("Default"));
            //Skin skin = new Skin(Gdx.files.internal("data/ui/uiskin.json"));
            buttonBox.addButton("MAP_0", "MAP 0", 30f, 320f, 200f, 30f);
            buttonBox.addButton("MAP_1",  "MAP 1",30f, 280f, 200f, 30f);

			buttonBox.getButtons().get("MAP_0").addListener(new ClickListener() {
				@Override
				public void touchUp(InputEvent e, float x, float y, int point, int button) {
					game.gameData.mapName = "TestMap_0";
					game.setScreen("GameScreen");
				}
			});

			buttonBox.getButtons().get("MAP_1").addListener(new ClickListener() {
				@Override
				public void touchUp(InputEvent e, float x, float y, int point, int button) {
					game.gameData.mapName = "TestMap_1";
					game.setScreen("GameScreen");
				}
			});
        }  
    }  
  
    @Override  
    public void update(float dt) {  

        Gdx.gl.glClearColor(1, 1, 1, 1);  
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);  
          
        game.camera.update();  
          
        game.spriteBatch.setProjectionMatrix(game.camera.combined); 
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
    }
    
	@Override
	public void resize(int arg0, int arg1) {
		game.camera.setToOrtho(false,  Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		game.camera.update();
		
		super.resize(arg0, arg1);
	}
}
