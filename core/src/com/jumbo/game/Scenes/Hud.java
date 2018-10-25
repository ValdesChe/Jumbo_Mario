/*
 * Copyright (c) 2018. This code has been Written by V@ldes Che.
 * Date : 18-06-19 13:53 ...........
 */

package com.jumbo.game.Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jumbo.game.Jumbo;
import com.jumbo.game.Screens.PlayScreen;

import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Created by Valdes on 2017-03-21.
 */
public class Hud implements Disposable{
    public Stage stage;
    private Viewport viewport;

    private int worldTimer;
    private float timeCount;
    private static Integer score;

    private Label countdownLabel;
    private static Label scoreLabel;
    private Label timeLabel;
    private Label levelLabel;
    private Label worldLabel;
    private Label jumboLabel;

    public Hud(SpriteBatch sprite){
        worldTimer = 300;
        timeCount = 0;
        score = 0;

        viewport = new FitViewport(Jumbo.V_WIDTH , Jumbo.V_HEIGHT , new OrthographicCamera());
        stage = new Stage(viewport,sprite);

        // table here is a 2Dscene table which help us to
        // pixelize our game
        Table table = new Table();

        //Set table on the top
        table.top();

        //Prendre la taille de l ecran
        table.setFillParent(true);

        //  Notre date aura trois caracteres et sera worldTimer
        // Et the other argument is the colorBipmap
        // countdownLabel = new Label(String.format("%03d",worldTimer),new LabelStyle(new BitmapFont(),Color.WHITE));

        countdownLabel = new Label(String.format("%03d",worldTimer),new Label.LabelStyle(new BitmapFont(),Color.GREEN));
        scoreLabel = new Label(String.format("%04d",score) ,new Label.LabelStyle(new BitmapFont(),Color.GREEN));
        timeLabel = new Label("Time",new Label.LabelStyle(new BitmapFont(),Color.MAROON));
        levelLabel = new Label("1-1",new Label.LabelStyle(new BitmapFont(),Color.RED));
        worldLabel = new Label("Level",new Label.LabelStyle(new BitmapFont(),Color.MAROON));
        jumboLabel = new Label("Enemies",new Label.LabelStyle(new BitmapFont(),Color.MAROON));



        table.add(jumboLabel);
        table.add(worldLabel);
        table.add(timeLabel);

        table.row().expandX();

        // Others methods expandX() , padleft ., width()
        table.add(scoreLabel).expandX();
        table.add(levelLabel).expandX();
        table.add(countdownLabel).expandX();


        stage.addActor(table);

    }


    public void update(float dt) {
        timeCount += dt;

        if (timeCount >= 1){
            worldTimer--;
            countdownLabel.setText(String.format("%03d",worldTimer));
            timeCount =0;
        }
    }


    public static void  addScore(int value) {
        score += value;
        scoreLabel.setText(String.format("%06d",score));
    }


    @Override
    public void dispose() {
        stage.dispose();
    }


}
