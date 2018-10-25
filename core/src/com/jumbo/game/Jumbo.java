/*
 * Copyright (c) 2018. This code has been Written by V@ldes Che.
 * Date : 18-06-19 13:53 ...........
 */

package com.jumbo.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.jumbo.game.Screens.PlayScreen;


import java.awt.event.InputEvent;

public class Jumbo extends Game {

	// Define the height and width of our game Environ'ment
	public static final int V_WIDTH = 155;
	public static final int V_HEIGHT = 275;

	public static final short DEFAULT_BIT = 1;
	public static final short MARIO_BIT = 2;
	public static final short BRICK_BIT = 4;
	public static final short COIN_BIT = 8;
	public static final short DESTROYED_BIT = 16;
	/*
	public static final int V_WIDTH = Gdx.graphics.getWidth();
	public static final int V_HEIGHT = Gdx.graphics.getHeight() ;
	*/
	// Pixel per Mid
	public static final float PPM = 2;

	public SpriteBatch batch;

	public static AssetManager assetManager;

	@Override
	public void create () {
		batch = new SpriteBatch();
		assetManager = new AssetManager();
		assetManager.load("audio/music/mario_music.ogg", Music.class);
		assetManager.load("audio/sound/coin.wav", Sound.class);
		assetManager.load("audio/sound/bump.wav", Sound.class);
		assetManager.load("audio/sound/mariodie.wav", Sound.class);
		assetManager.load("audio/sound/breakblock.wav", Sound.class);
		assetManager.load("audio/sound/powerdown.wav", Sound.class);
		assetManager.load("audio/sound/powerup.wav", Sound.class);
		assetManager.load("audio/sound/powerup_spawn.wav", Sound.class);
		assetManager.finishLoading();
		setScreen(new PlayScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();
		assetManager.dispose();
		batch.dispose();
	}

	@Override
	public void render () {
		super.render();
	}

}
