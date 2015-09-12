package com.dobromir.rebellion.data;

import java.util.HashMap;
import java.util.Map;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.dobromir.rebellion.constantly.PathsConfig;
import com.dobromir.rebellion.parsers.DataParser;

public class Sounds extends Storage{

	public static void load() {
		HashMap<String, String> parse = DataParser.parse(PathsConfig.SOUNDS_CONFIG_FILE);

		for (Map.Entry<String, String> entry : parse.entrySet()) {
			add(entry.getKey(), (Sound) Gdx.audio.newSound(Gdx.files.internal(PathsConfig.SOUNDS_PATH + entry.getValue())));
		}
	}

	public static Sound getSound(String name) {
		return (Sound) get(name);
	}
      
    public static void play (String soundName, float start, float end) {  
		getSound(soundName).play(1, start, end);
    }  
}
