/*
 * Copyright (c) 2018. This code has been Written by V@ldes Che.
 * Date : 18-06-19 13:53 ...........
 */

package com.jumbo.game.Sprites;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.jumbo.game.Jumbo;
import com.jumbo.game.Scenes.Hud;
import com.jumbo.game.Screens.PlayScreen;

public class Brick extends InterractiveTileObject {
    public Brick(PlayScreen screen, Rectangle rectangle) {
        super(screen, rectangle);
        fixture.setUserData(this);
        setCategoryFilter(Jumbo.BRICK_BIT);
    }


    // Methode ors de la collision avec head
    @Override
    public void OnHeadHit() {
        Gdx.app.log("Brick ", "Collision");
        setCategoryFilter(Jumbo.DESTROYED_BIT);
        getCell().setTile(null);


        Jumbo.assetManager.get("audio/sound/breakblock.wav", Sound.class).play();
        // On augmente le score de 200
        Hud.addScore(200);
    }
}
