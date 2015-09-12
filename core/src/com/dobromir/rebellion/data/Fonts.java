package com.dobromir.rebellion.data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.dobromir.rebellion.constantly.PathsConfig;
import com.dobromir.rebellion.parsers.DataParser;

public class Fonts extends Storage{

    public static void load() {
        HashMap<String, String> parse = DataParser.parse(PathsConfig.FONTS_CONFIG_FILE);

        for (Map.Entry<String, String> entry : parse.entrySet()) {
            add(entry.getKey(), (BitmapFont) new BitmapFont(Gdx.files.internal(PathsConfig.FONTS_PATH + entry.getValue())));
        }
    }

    public static BitmapFont getFont(String name) {
        return (BitmapFont) get(name);
    }
}
