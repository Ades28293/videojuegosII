package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends Game {


	private AssetManager manager;

	public AssetManager getManager() {
		return manager;
	}

	@Override
	public void create() {
		manager = new AssetManager();
		manager.load("dino.png", Texture.class);
		manager.load("roca.png", Texture.class);
		manager.load("suelo.png", Texture.class);
		manager.finishLoading();
		setScreen(new GameScreen(this));
	}

}

