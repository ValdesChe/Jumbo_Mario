/*
 * Copyright (c) 2018. This code has been Written by V@ldes Che.
 * Date : 18-06-19 13:53 ...........
 */

package com.jumbo.game.Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.jumbo.game.Jumbo;
import com.jumbo.game.Screens.PlayScreen;


public abstract class InterractiveTileObject {
    protected Fixture fixture;
    protected World world;
    protected TiledMap map;
    protected TiledMapTile tile;
    protected Rectangle bounds;
    protected Body body;

    public InterractiveTileObject(PlayScreen screen, Rectangle rectangle){
        this.world = screen.getWorld();
        this.map = screen.getMap();
        this.bounds = rectangle;

        BodyDef bdef =new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set( (rectangle.getX() + rectangle.getWidth()/2)/ Jumbo.PPM  , (rectangle.getY() + rectangle.getHeight()/2) /Jumbo.PPM );

        // On ajoute notre objet au monde (jeu)
        body = world.createBody(bdef);

        shape.setAsBox( rectangle.getWidth()/2/Jumbo.PPM  , rectangle.getHeight()/2/Jumbo.PPM );
        fdef.shape = shape;

        fixture = body.createFixture(fdef);
    }

    public abstract void OnHeadHit();

    public void setCategoryFilter(short filterBit){
        Filter filter = new Filter();
        filter.categoryBits = filterBit;
        fixture.setFilterData(filter);
    }

    public TiledMapTileLayer.Cell getCell(){
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(1);
        return layer.getCell((int)(body.getPosition().x*Jumbo.PPM /16) ,(int)(body.getPosition().y*Jumbo.PPM / 16 ));
    }
}
