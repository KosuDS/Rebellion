package com.dobromir.rebellion.parsers;

import com.badlogic.gdx.maps.tiled.TmxMapLoader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public final class DataParser {
    public static HashMap<String, String> parse(String path) {
        HashMap<String, String> data = new HashMap<String, String>();

        BufferedReader br;
        try {

            br = new BufferedReader(new FileReader(path));

            for(String line; (line = br.readLine()) != null; ) {
                String name = line.split(" ")[0];
                String expand = line.split(" ")[1];

                data.put(name, expand);
            }
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }

        return data;
    }
}
