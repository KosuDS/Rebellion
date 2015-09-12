package com.dobromir.rebellion.data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.dobromir.rebellion.constantly.PathsConfig;
import com.dobromir.rebellion.parsers.DataParser;
import java.util.HashMap;
import java.util.Map;

public class Skins extends Storage{

    public static void load() {
        HashMap<String, String> parse = DataParser.parse(PathsConfig.SKINS_CONFIG_FILE);

        for (Map.Entry<String, String> entry : parse.entrySet()) {
            add(entry.getKey(), (Skin) new Skin(Gdx.files.internal(PathsConfig.SKINS_PATH + entry.getValue())));
        }
    }

    public static Skin getSkin(String name) {
        return (Skin) get(name);
    }
}
