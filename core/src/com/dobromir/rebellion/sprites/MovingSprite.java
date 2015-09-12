package com.dobromir.rebellion.sprites;

import java.util.ArrayList;
import java.util.Collections;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.dobromir.rebellion.Game;
import com.dobromir.rebellion.data.Maps;

public class MovingSprite extends GameSprite {
	
	private TiledMapTileLayer collisionLayer;
	
	private float directionX; // Kierunek X w ktorym podazamy
	private float directionY; // Kierunek Y w ktorym podazamy
	
	private float velocityX; // Przyspieszenie absolutne X
	private float velocityY; // Przyspieszenie absolutne Y
	
	private float rotationVelocityX; // Przyspieszenie X wzgledem wartosci rotacji 
	private float rotationVelocityY; // Przyspieszenie Y wzgledem wartosci rotacji 
	
	private float rotationX; // Pozycja X lewego dolnego rogu po rotacji
	private float rotationY; // Pozycja Y lewego dolnego rogu po rotacji
	
	private float rotationWidth; // Szerokosc po rotacji
	private float rotationHeight; // Wysokosc po rotacji
	
	private float rotationMinX; // Skrajny lewy punkt po rotacji
	private float rotationMinY; // Skrajny dolny punkt po rotacji
	private float rotationMaxX; // Skrajny prawy punkt po rotacji
	private float rotationMaxY; // Skrajny gorny punkt po rotacji
	
	private float speed;
	
	public MovingSprite(Game game, float x, float y, float speed) {
		super(game, x, y);
		this.speed = speed;
		
		collisionLayer = (TiledMapTileLayer) Maps.getTiledMap(game.gameData.mapName).getLayers().get(0);
	}
	
	public MovingSprite(String skinName, Game game, float x, float y, float speed) {
		super(skinName, game, x, y);
		this.speed = speed;
	}
	
	public void calculateRotationSize() { // Oblicza nowa wysokosc i szerokosc po rotacji
		float angle = -rotation * MathUtils.degreesToRadians;
		
		float centerX = x + (width / 2);
		float centerY = y + (height / 2);
		
		rotationX = (float) (centerX+(x-centerX) * Math.cos(angle) + (y-centerY) * Math.sin(angle));
		rotationY = (float) (centerY-(x-centerX) * Math.sin(angle) + (y-centerY) * Math.cos(angle));
		
		final Vector2 downLeftCorner = new Vector2((float)(centerX + (x - centerX) * Math.cos(angle) + (y - centerY) * Math.sin(angle)), (float) (centerY - (x - centerX) * Math.sin(angle) + (y-centerY) * Math.cos(angle)));
		final Vector2 downRightCorner = new Vector2((float)(centerX + (x + width - centerX) * Math.cos(angle)+(y-centerY)*Math.sin(angle)), (float) (centerY-(x + width-centerX)*Math.sin(angle)+(y-centerY)*Math.cos(angle)));
		final Vector2 upLeftCorner = new Vector2((float)(centerX+(x-centerX)*Math.cos(angle)+(y + height -centerY)*Math.sin(angle)), (float) (centerY-(x-centerX)*Math.sin(angle)+(y + height -centerY)*Math.cos(angle)));
		final Vector2 upRightCorner = new Vector2((float)(centerX+(x + width-centerX)*Math.cos(angle)+(y + height -centerY)*Math.sin(angle)), (float) (centerY-(x + width -centerX)*Math.sin(angle)+(y + height -centerY)*Math.cos(angle)));
		
		ArrayList<Float> xs = new ArrayList<Float>() {{
			add(downLeftCorner.x);
			add(downRightCorner.x);
			add(upLeftCorner.x);
			add(upRightCorner.x);
		}};
		
		ArrayList<Float> ys = new ArrayList<Float>() {{
			add(downLeftCorner.y);
			add(downRightCorner.y);
			add(upLeftCorner.y);
			add(upRightCorner.y);
		}};

		
		rotationMinX = Collections.min(xs);
		rotationMinY = Collections.min(ys);
		rotationMaxX = Collections.max(xs);
		rotationMaxY = Collections.max(ys);
		
		rotationWidth = rotationMaxX - rotationMinX;
		rotationHeight = rotationMaxY - rotationMinY;
	}
	
	public void calculateAngleVelocity(float dt) { // Oblicza przyspieszenie wzgledem celu
		rotation = -MathUtils.radiansToDegrees * MathUtils.atan2(directionY - y , directionX - x);

		rotationVelocityX = (float) (Math.cos(rotation * MathUtils.degreesToRadians)) * speed * dt;
		rotationVelocityY = (float) (Math.sin(rotation * MathUtils.degreesToRadians)) * speed * dt;
		
		calculateRotationSize();
	}

	public void chceckCollision() {
		float oldX = getX(), oldY = getY();
		boolean collisionX = false, collisionY = false;
		
		setX(getX() + velocityX);
		
		if(velocityX < 0){
			collisionX = collidesLeft();
		}else if(velocityX > 0){
			collisionX = collidesRight();
		}
		
		if(collisionX) {
			setX(oldX);
			velocityX = 0;
		}
		
		setY(getY() + velocityY);
		
		if(velocityY < 0){
			collisionY = collidesBottom();
		}else if(velocityY > 0){
			collisionY = collidesTop();
		}
		
		if(collisionY) {
			setY(oldY);
			velocityY = 0;
		}
	}
	
	public void moveForward() {
		velocityX = rotationVelocityX;
		velocityY = rotationVelocityY;
		
		chceckCollision();
		
		x += velocityX;
		y += velocityY;
	}
	
	public void moveBack() {
		velocityX = -rotationVelocityX;
		velocityY = -rotationVelocityY;
		
		chceckCollision();
		
		x += velocityX;
		y += velocityY;
	}
	
	public void moveLeft() {
		velocityX = -rotationVelocityY;
		velocityY = rotationVelocityX;
		
		chceckCollision();
		
		x += velocityX;
		y += velocityY;
	}
	
	public void moveRight() {
		velocityX = rotationVelocityY;
		velocityY = -rotationVelocityX;
		
		chceckCollision();
		
		x += velocityX;
		y += velocityY;
	}
	
	private boolean isCellBlocked(float x, float y) {
		Cell cell = collisionLayer.getCell((int) (x / collisionLayer.getTileWidth()), (int) (y / collisionLayer.getTileHeight()));
		return cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey("collision");
	}

	public boolean collidesRight() {
		for(float step = 0; step < rotationHeight; step += collisionLayer.getTileHeight() / 2)
			if(isCellBlocked(rotationMinX + rotationWidth, rotationMinY + step))
				return true;
		return false;
	}

	public boolean collidesLeft() {
		for(float step = 0; step < rotationHeight; step += collisionLayer.getTileHeight() / 2)
			if(isCellBlocked(rotationMinX, rotationMinY + step))
				return true;
		return false;
	}

	public boolean collidesTop() {
		for(float step = 0; step < getRotationWidth(); step += collisionLayer.getTileWidth() / 2)
			if(isCellBlocked(rotationMinX + step, rotationMinY + rotationHeight))
				return true;
		return false;

	}

	public boolean collidesBottom() {
		for(float step = 0; step < getRotationWidth(); step += collisionLayer.getTileWidth() / 2)
			if(isCellBlocked(rotationMinX + step, rotationMinY))
				return true;
		return false;
	}
	
	@Override
	public void update(float dt) {
		calculateAngleVelocity(dt);
	}

	public TiledMapTileLayer getCollisionLayer() {
		return collisionLayer;
	}

	public void setCollisionLayer(TiledMapTileLayer collisionLayer) {
		this.collisionLayer = collisionLayer;
	}

	public float getDirectionX() {
		return directionX;
	}

	public void setDirectionX(float directionX) {
		this.directionX = directionX;
	}

	public float getDirectionY() {
		return directionY;
	}

	public void setDirectionY(float directionY) {
		this.directionY = directionY;
	}

	public float getVelocityX() {
		return velocityX;
	}

	public void setVelocityX(float velocityX) {
		this.velocityX = velocityX;
	}

	public float getVelocityY() {
		return velocityY;
	}

	public void setVelocityY(float velocityY) {
		this.velocityY = velocityY;
	}

	public float getRotationVelocityX() {
		return rotationVelocityX;
	}

	public void setRotationVelocityX(float rotationVelocityX) {
		this.rotationVelocityX = rotationVelocityX;
	}

	public float getRotationVelocityY() {
		return rotationVelocityY;
	}

	public void setRotationVelocityY(float rotationVelocityY) {
		this.rotationVelocityY = rotationVelocityY;
	}

	public float getRotationX() {
		return rotationX;
	}

	public void setRotationX(float rotationX) {
		this.rotationX = rotationX;
	}

	public float getRotationY() {
		return rotationY;
	}

	public void setRotationY(float rotationY) {
		this.rotationY = rotationY;
	}

	public float getRotationWidth() {
		return rotationWidth;
	}

	public void setRotationWidth(float rotationWidth) {
		this.rotationWidth = rotationWidth;
	}

	public float getRotationHeight() {
		return rotationHeight;
	}

	public void setRotationHeight(float rotationHeight) {
		this.rotationHeight = rotationHeight;
	}

	public float getRotationMinX() {
		return rotationMinX;
	}

	public void setRotationMinX(float rotationMinX) {
		this.rotationMinX = rotationMinX;
	}

	public float getRotationMinY() {
		return rotationMinY;
	}

	public void setRotationMinY(float rotationMinY) {
		this.rotationMinY = rotationMinY;
	}

	public float getRotationMaxX() {
		return rotationMaxX;
	}

	public void setRotationMaxX(float rotationMaxX) {
		this.rotationMaxX = rotationMaxX;
	}

	public float getRotationMaxY() {
		return rotationMaxY;
	}

	public void setRotationMaxY(float rotationMaxY) {
		this.rotationMaxY = rotationMaxY;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
}
