package com.dobromir.rebellion.data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.dobromir.rebellion.constantly.PathsConfig;
import com.dobromir.rebellion.parsers.DataParser;

public class Maps extends Storage{

	public static void load() {
		HashMap<String, String> parse = DataParser.parse(PathsConfig.MAPS_CONFIG_FILE);

		for (Map.Entry<String, String> entry : parse.entrySet()) {
			add(entry.getKey(), (TiledMap) new TmxMapLoader().load(PathsConfig.MAPS_PATH + entry.getValue()));
		}
	}

	public static TiledMap getTiledMap(String name) {
		return (TiledMap) get(name);
	}
}
