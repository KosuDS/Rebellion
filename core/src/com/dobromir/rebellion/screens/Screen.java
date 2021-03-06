package com.dobromir.rebellion.screens;

import java.util.HashMap;
import com.dobromir.rebellion.sprites.GameSprite;
import com.dobromir.rebellion.Game;

public abstract class Screen implements com.badlogic.gdx.Screen {  
	  
    public HashMap<String, GameSprite> elements;
    protected Game game;  
      
    public Screen (Game game) {  
        this.game = game;  
        elements = new HashMap<>();
    }  
      
    public void pause () {};  
    public void resume () {};  
    public void dispose () {};  
    public void hide () {};  
    public void show () {};  
    public void destroy () {};  
      
    public abstract void createScreen ();  
    public abstract void update (float dt);  
      
    @Override  
    public void render(float arg0) {}  
  
    @Override  
    public void resize(int arg0, int arg1) {}
} 