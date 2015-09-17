package com.dobromir.rebellion.sandbox;

import java.util.HashMap;
import java.util.Map;

public class HashMapyItp {

    public static void main(String[] args) {
        HashMap<String, String> slownik = new HashMap<>();

        slownik.put("pies", "dog");
        slownik.put("dom", "home");

        System.out.println(slownik.get("pies"));
        System.out.println(slownik.get("home"));

        for (String value : slownik.values()) {
            System.out.println(value);
        }

        HashMap<String, String> spis = new HashMap<>();
        spis.put("1", "Karol");
        spis.put("2", "Michal");

        for(Map.Entry<String, String> entry : spis.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            System.out.println(key + " : " + value);
        }
    }
}
