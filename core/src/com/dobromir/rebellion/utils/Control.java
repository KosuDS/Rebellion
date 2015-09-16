package com.dobromir.rebellion.utils;

import com.dobromir.rebellion.utils.io.log.Logger;

public class Control {
    public static void fail(String message) {
        Logger.log(message);
        System.exit(1);
    }
}
