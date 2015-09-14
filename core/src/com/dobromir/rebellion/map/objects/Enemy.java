package com.dobromir.rebellion.map.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.dobromir.rebellion.Game;
import com.dobromir.rebellion.data.ImageCache;
import com.dobromir.rebellion.sprites.GameSprite;

/**
 * Created by Micha³ on 2015-09-13.
 */
public class Enemy extends GameSprite {

    private boolean death = false;

    private Sprite sprite;

    public Enemy(Game game, float x, float y) {
        super(game, x, y, 100);//TODO tu jest error ale nie wiem czemu
        texture = null;

        sprite = new Sprite(ImageCache.getTexture("enemy"));
        width = sprite.getWidth();
        height = sprite.getHeight();


    }


//TODO:zrobic przeciwnika
}
