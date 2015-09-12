package com.dobromir.rebellion.map.objects;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.dobromir.rebellion.Game;
import com.dobromir.rebellion.constantly.KeysConfig;
import com.dobromir.rebellion.data.ImageCache;
import com.dobromir.rebellion.data.Sounds;
import com.dobromir.rebellion.sprites.MovingSprite;


public class Player extends MovingSprite {

	private boolean dead = false;
	
	private Sprite sprite;

	public Player(Game game, float x, float y) {
		super(game, x, y, 100);
		texture = null;

		sprite = new Sprite(ImageCache.getTexture("player"));
		width = sprite.getWidth();
		height = sprite.getHeight();

//        TODO: Zrobic 3 stopniowa animacje
	}
	
	public void shoot() {
		Sounds.play("shot", 1f, 1f);
	}
	
	@Override
	public void reset() {
		visible = true;
		dead = false;
		active = true;
		
		x = 0;
		y = 0;
	}
	
	@Override
	public void draw() {
		sprite.draw(game.spriteBatch);
	}

	public void input() {
        if(Gdx.input.isKeyPressed(KeysConfig.MOVE_FORWARD)) {
            moveForward();
        } if(Gdx.input.isKeyPressed(KeysConfig.MOVE_BACK)){
            moveBack();
        } if(Gdx.input.isKeyPressed(KeysConfig.MOVE_LEFT)){
            moveLeft();
        } if(Gdx.input.isKeyPressed(KeysConfig.MOVE_RIGHT)){
            moveRight();
        }

        if(Gdx.input.isKeyJustPressed(KeysConfig.SHOOT)) shoot();
	}

	@Override
	public void update(float dt) {
        if(active) {
            setDirectionX((Gdx.input.getX() - game.screenWidth / 2) + x - width / 2);
            setDirectionY((Gdx.input.getY() - game.screenHeight / 2) + y + height / 2);

            super.update(dt);

            sprite.setRotation(rotation);
            sprite.setX(x);
            sprite.setY(y);

            input();
        }
	}

    public float[] getVertices() {
        float[] vertices = sprite.getVertices();
        return new float[] {vertices[Batch.X1], vertices[Batch.Y1], vertices[Batch.X2], vertices[Batch.Y2], vertices[Batch.X3], vertices[Batch.Y3], vertices[Batch.X4], vertices[Batch.Y4], vertices[Batch.X1], vertices[Batch.Y1]};
    }
}
