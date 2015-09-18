package com.dobromir.rebellion.ui;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.dobromir.rebellion.Game;

import com.dobromir.rebellion.sprites.GameSprite;

public class ButtonBox extends GameSprite{

	private Stage stage;
	private HashMap<String, Button> buttons;
	private Skin skin;
	
	public ButtonBox(Game game, float x, float y, Skin skin) {
		super(game, x, y);

		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
		buttons = new HashMap<>();
		this.skin = skin;
		
		game.screen.elements.put("ButtonBox", this);
	}
	
	public void addButton(String name, String text, float x, float y, float width, float height) {
        TextButton btn = new TextButton(text, skin);
        btn.setPosition(x, y);
        btn.setSize(width, height);
        
        stage.addActor(btn);
        buttons.put(name, btn);
//		TODO: To musi sie dziac automatycznie, segregowanie, ukaladanie itp.
	}
	
	@Override
	public void update(float dt) {
		for (Button button : buttons.values()) {
			button.act(dt);
		}
	}
	
	@Override
	public void draw() {
		for (Button button : buttons.values()) {
			button.draw(game.spriteBatch, visible ? 1 : 0);
		}
	}

	public HashMap<String, Button> getButtons() {
		return buttons;
	}

	public void setButtons(HashMap<String, Button> buttons) {
		this.buttons = buttons;
	}
}
