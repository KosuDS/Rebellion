package com.dobromir.rebellion.data;

import com.badlogic.gdx.Gdx;  
import com.badlogic.gdx.graphics.Texture;  
import com.badlogic.gdx.graphics.g2d.TextureAtlas;  
import com.badlogic.gdx.graphics.g2d.TextureRegion;  
import com.dobromir.rebellion.constantly.PathsConfig;

import static com.dobromir.rebellion.utils.Control.fail;

public class ImageCache {  
  
    public static Texture sheet;  
    public static TextureAtlas atlas;  
      
    public static void load () {  
        atlas = new TextureAtlas(Gdx.files.internal(PathsConfig.TEXTURES_CONFIG_FILE), Gdx.files.internal( PathsConfig.GRAPHICS_PATH));
    }  
      
    public static TextureRegion getTexture (String name) {
        TextureRegion texture = atlas.findRegion(name);

        if(texture == null) fail("Can't find textureRegion: " + name);
        return texture;
    }
      
    public static TextureRegion getFrame (String name, int index) {
        TextureRegion texture = atlas.findRegion(name, index);

        if(texture == null) fail("Can't find textureRegion: " + name + " " + index);
        return texture;
    }  
}  