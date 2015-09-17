package com.dobromir.rebellion.sandbox;

import java.util.ArrayList;

public class For {
    public static void main(String[] args) {
        ArrayList<String> imiona = new ArrayList<>();
        imiona.add("Karol");
        imiona.add("Michal");
        imiona.add("Seba");

        for (int index = 0; index < imiona.size(); index++) {
            System.out.println("- " + imiona.get(index));
        }

        for (String objects : imiona) {
            System.out.println("- " + objects);
        }
    }
}
