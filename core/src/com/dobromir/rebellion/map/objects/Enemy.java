package com.dobromir.rebellion.map.objects;

import com.dobromir.rebellion.Game;
import com.dobromir.rebellion.sprites.GameSprite;

/**
 * Created by Micha³ on 2015-09-13.
 */
public class Enemy extends GameSprite {

    public Enemy(Game game, float x, float y) {
        super(game, x, y);
    }

    public Enemy(String skinName, Game game, float x, float y) {
        super(skinName, game, x, y);
    }
}
