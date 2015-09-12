package com.dobromir.rebellion.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.dobromir.rebellion.RebellionGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Rebellion";
		config.width = 1280;
		config.height = 720;
		new LwjglApplication(new RebellionGame(), config);
	}
}
