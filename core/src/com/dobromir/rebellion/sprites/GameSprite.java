package com.dobromir.rebellion.sprites;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.dobromir.rebellion.Game;
import com.badlogic.gdx.graphics.g2d.Batch.*;
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
    private Polygon body;

	protected Game game;
	
	public GameSprite (Game game, float x, float y) {
		this.game = game;
		this.x = x;
		this.y = y;
		active = true;
		visible = true;
		texture = null;

        createBody(new float[]{x, y, x + width, y, x + width, y + height, x, y + height, x, y});
	}
	
	public GameSprite (String textureName, Game game, float x, float y) {
		this.game = game;
		active = true;
		visible = true;
		this.x = x;
		this.y = y;
		setTexture(textureName);
	}

    public void createBody(Sprite sprite) {
        float[] spriteVertices = sprite.getVertices();

        createBody(new float[]{spriteVertices[Batch.X1], spriteVertices[Batch.Y1], spriteVertices[Batch.X2], spriteVertices[Batch.Y2], spriteVertices[Batch.X3], spriteVertices[Batch.Y3], spriteVertices[Batch.X4], spriteVertices[Batch.Y4]});
    }

    public void createBody(TextureRegion texture) {
        createBody(new float[] {x, y, x + texture.getRegionWidth(), y, x + texture.getRegionWidth(), y + texture.getRegionHeight(), x, y + texture.getRegionHeight()});
    }

    public void createBody(float[] vertices) {
        body = new Polygon(vertices);
    }
	
	public boolean isVisible() {
		return visible;
	}

	public void setTexture (String textureName, int textureIndex) {
		setTexture(ImageCache.getFrame(textureName, textureIndex));
	}

	public void setTexture(String textureName) {
		setTexture(ImageCache.getTexture(textureName));
	}
	
	public void setTexture(TextureRegion texture) {
		this.texture = texture;

		width = this.texture.getRegionWidth();
		height = this.texture.getRegionHeight();

        createBody(texture);
	}

    public boolean isCollisionWith(Polygon polygon) {
        return Intersector.overlapConvexPolygons(polygon, body);
    }

    public Polygon getBody() {
        return body;
    }

    public void setBody(Polygon body) {
        this.body = body;
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
		return new Rectangle(x , y, width, height);
	}
	
	public void update (float dt) {}

	public void reset () {
		x = 0;
		y = 0;
	}
	
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
