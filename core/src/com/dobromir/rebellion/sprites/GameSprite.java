package com.dobromir.rebellion.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.dobromir.rebellion.Game;

import com.dobromir.rebellion.data.ImageCache;

public class GameSprite{
	
	public boolean active;
	public boolean visible;
	public float x = 0;
	public float y = 0;
	public float width = 0;
	public float height = 0;
	public float rotation = 0;
	
	public TextureRegion texture;

	protected Game game;
	
	public GameSprite (Game game, float x, float y) {
		this.game = game;
		this.x = x;
		this.y = y;
		active = true;
		visible = true;
		texture = null;
	}
	
	public GameSprite (String skinName, Game game, float x, float y) {
		this.game = game;
		active = true;
		visible = true;
		this.x = x;
		this.y = y;
		setTexture(skinName);
	}
	
	public boolean isVisible() {
		return visible;
	}

	public void setSkin (String skinName, int skinIndex) {
		setSkin (ImageCache.getFrame(skinName, skinIndex));
	}
	public void setTexture(String skinName) {
		setSkin (ImageCache.getTexture(skinName));
	}
	
	public void setSkin (TextureRegion texture) {
		this.texture = texture;
		width = this.texture.getRegionWidth();
		height = this.texture.getRegionHeight();
		x = x - this.texture.getRegionWidth() * 0.5f;
		y = y - this.texture.getRegionHeight() * 0.5f;
	}
	
	public float right () {
		return x + width;
	}
	
	public float left () {
		return x;
	}
	
	public float top () {
		return y + height;
	}
	
	public float bottom () {
		return y;
	}
	
	public Rectangle bounds () {
		return new Rectangle(x + width * 0.2f, y + height * 0.2f, width * 0.8f, height * 0.8f);
	}
	
	public void update (float dt) {}
	public void reset () {};
	
	public void draw () {
		game.spriteBatch.draw(texture, x, y);
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getRotation() {
		return rotation;
	}

	public void setRotation(float rotation) {
		this.rotation = rotation;
	}

	public TextureRegion getTexture() {
		return texture;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public boolean getVisible() {
		return this.visible;
	}
}
