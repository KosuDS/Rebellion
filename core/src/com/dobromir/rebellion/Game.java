package com.dobromir.rebellion;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import com.dobromir.rebellion.data.GameData;
import com.dobromir.rebellion.screens.Screen;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dobromir.rebellion.utils.GlobalCamera;

import static com.dobromir.rebellion.utils.Control.fail;

public class Game extends ApplicationAdapter {
	
	
	public static final int GAME_STATE_PLAY = 0;  
    public static final int GAME_STATE_PAUSE = 1;  
    public static final int GAME_STATE_ANIMATE = 2;  
      
    public Screen screen;
      
    public GameData gameData;
    public SpriteBatch spriteBatch;  
    public GlobalCamera camera;
    public int screenWidth = 0;  
    public int screenHeight = 0;  
      
    protected HashMap<String, Screen> screens;  
      
      
    public void create() {  
        screens = new HashMap<>();
    }  
          
    public void setScreen (String screenClassName) {  
    		screens.clear();
    	
            screenClassName = "com.dobromir.rebellion.screens."+screenClassName;
            Screen newScreen = null;  
              
            if (!screens.containsKey(screenClassName)) {
                  
                try {  
                    Class screenClass =  Class.forName(screenClassName);   
                    Constructor constructor = screenClass.getConstructor(Game.class);      
                    newScreen = (Screen) constructor.newInstance(this);  
                    screens.put(screenClassName, newScreen);  
                } catch ( InvocationTargetException ex ){
                    fail(ex + " Screen with Wrong args in Constructor.");
                } catch ( NoSuchMethodException ex ){
                    fail(ex + " No such method.");
                } catch ( ClassNotFoundException ex ){
                    fail(ex + " Screen Class Not Found.");
                } catch( InstantiationException ex ){
                    fail(ex + " Screen Must be a concrete class.");
                } catch( IllegalAccessException ex ){
                    fail(ex + " Screen with Wrong number of args.");
                }  
            } else {  
                newScreen = screens.get(screenClassName);  
            }  
              
            if (newScreen == null) return;  
              
            if (screen != null) {  
                screen.destroy();  
            }  
            screen = newScreen;  
            screen.createScreen();  
    }  
}
