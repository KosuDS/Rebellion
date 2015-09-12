package com.dobromir.rebellion.data;

import java.util.HashMap;

public abstract class Storage {
    public static HashMap<String, Object> collection = new HashMap<>();

    public static void load() {}

    public static void add(String name, Object object) {
        collection.put(name, object);
    }

    public static Object get(String name) {
        if(!collection.isEmpty()) {
            if(collection.containsKey(name)) {
                return collection.get(name);
            } else {
                System.out.println("Storage - collection dont have key: " + name);
            }
        } else {
            System.out.println("Storage - collection is empty");
        }

        return null;
    }
}
