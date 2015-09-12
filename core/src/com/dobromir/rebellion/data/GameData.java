package com.dobromir.rebellion.data;

import com.dobromir.rebellion.Game;

public class GameData {
	
	public int score = 0;
	public int level = 1;
	public int lives = 3;
	public int gameMode;
	public String mapName = "";

	public GameData(Game game) {
		gameMode = Game.GAME_STATE_PAUSE;
	}
	
	public void reset(){
		score = 0;
		level = 1;
		lives = 3;
		mapName = "";
	}
}
