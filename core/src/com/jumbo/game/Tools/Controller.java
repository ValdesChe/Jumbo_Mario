/*
 * Copyright (c) 2018. This code has been Written by V@ldes Che.
 * Date : 18-06-19 13:53 ...........
 */

package com.jumbo.game.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jumbo.game.Jumbo;
import com.jumbo.game.Screens.PlayScreen;


public class Controller implements Disposable {
    Viewport viewPort;
    Stage stage;
    boolean upPressed, downPressed, leftPressed , rightPressed;
    OrthographicCamera camera;
    private static final float btnWidth = 20;
    private static final float btnHeight = 20;
/*
    public Controller(SpriteBatch spriteBatch){*/
    public Controller(){
        camera = new OrthographicCamera();
        viewPort = new FitViewport(Jumbo.V_WIDTH , Jumbo.V_HEIGHT,camera );
        stage = new Stage(viewPort);
        /*stage = new Stage(viewPort,spriteBatch);*/
        Gdx.input.setInputProcessor(stage);

        Table table = new Table();

        table.left().bottom();

        Image upImage = new Image(new Texture("controllers/upBtn.png"));
        upImage.setSize(btnWidth,btnHeight);
        upImage.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                upPressed =true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                upPressed = false;
            }
        });


        Image downImage = new Image(new Texture("controllers/downBtn.png"));
        downImage.setSize(btnWidth,btnHeight);
        downImage.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                downPressed =true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                downPressed = false;
            }
        });

        Image leftImage = new Image(new Texture("controllers/leftBtn.png"));
        leftImage.setSize(btnWidth,btnHeight);
        leftImage.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                leftPressed =true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                leftPressed = false;
            }
        });

        Image rightImage = new Image(new Texture("controllers/rigthBtn.png"));
        rightImage.setSize(btnWidth,btnHeight);
        rightImage.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                rightPressed =true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                rightPressed = false;
            }
        });
        //table.row().expandX();
        table.add();
        table.add(upImage).size(btnWidth,btnHeight);
        table.add();
        table.row().pad(5 , 5 ,5 ,5 );

        table.add(leftImage).size(btnWidth,btnHeight);
        table.add();
        table.add(rightImage).size(btnWidth,btnHeight);
        table.row().padBottom(5);

        table.add();
        //table.add(downImage).size(btnWidth,btnHeight);
        table.add();
        table.add();
        stage.addActor(table);

    }

    public void draw(){
        stage.draw();
    }


    @Override
    public void dispose() {

    }

    public boolean isUpPressed() {
        return upPressed;
    }

    public boolean isDownPressed() {
        return downPressed;
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public void resize(int width , int height){
        viewPort.update(width,height);
    }
}
