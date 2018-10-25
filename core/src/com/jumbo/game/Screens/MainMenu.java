/*
 * Copyright (c) 2018. This code has been Written by V@ldes Che.
 * Date : 18-06-19 13:53 ...........
 */

package com.jumbo.game.Screens;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.jumbo.game.Jumbo;
import com.badlogic.gdx.Screen;

public class MainMenu implements Screen {
    private Jumbo game ;
    private Texture texture;
    private OrthographicCamera gamecam;
    private FitViewport gamePort;

    public MainMenu(Jumbo game){
        this.game = game;
        texture = new Texture("badlogic.png");


        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(Jumbo.V_WIDTH,Jumbo.V_HEIGHT,gamecam);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
