package com.dobromir.rebellion.map.objects;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.dobromir.rebellion.Game;
import com.dobromir.rebellion.constantly.KeysConfig;
import com.dobromir.rebellion.data.ImageCache;
import com.dobromir.rebellion.data.Sounds;
import com.dobromir.rebellion.sprites.MovingSprite_exp;


public class Player extends MovingSprite_exp {

	private boolean dead = false;
	
	private Sprite sprite;

	private TextureRegion leftSidePlayer;
	private TextureRegion rightSidePlayer;
	private TextureRegion middleSidePlayer;

	public Player(Game game, float x, float y) {
		super(game, x, y, 100);
		texture = null;

		//leftSidePlayer = ImageCache.getTexture("left_side_player");
		//rightSidePlayer = ImageCache.getTexture("right_side_player");
		//middleSidePlayer = ImageCache.getTexture("middle_side_player");

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
}
