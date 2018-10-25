/*
 * Copyright (c) 2018. This code has been Written by V@ldes Che.
 * Date : 18-06-19 13:53 ...........
 */

package com.jumbo.game.Sprites;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.jumbo.game.Jumbo;
import com.jumbo.game.Scenes.Hud;
import com.jumbo.game.Screens.PlayScreen;


public class Coin extends InterractiveTileObject {
    private static TiledMapTileSet tileset;
    // Variable for the blank coin id : 27 in tileset_gutter tileset
    private final int BLANK_COIN = 28;

    public Coin(PlayScreen screen, Rectangle rectangle) {
        super(screen, rectangle);
        tileset = map.getTileSets().getTileSet("tileset_gutter");
        fixture.setUserData(this);
        setCategoryFilter(Jumbo.COIN_BIT);
    }

    // Methode ors de la collision avec head
    @Override
    public void OnHeadHit() {

        if (getCell().getTile().getId() == BLANK_COIN)
            Jumbo.assetManager.get("audio/sound/bump.wav", Sound.class).play();
        else
            Jumbo.assetManager.get("audio/sound/coin.wav", Sound.class).play();

        getCell().setTile(tileset.getTile(BLANK_COIN));
        Hud.addScore(100);

    }
}
