/*
 * Copyright (c) 2018. This code has been Written by V@ldes Che.
 * Date : 18-06-19 13:53 ...........
 */

package com.jumbo.game.Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.jumbo.game.Jumbo;
import com.jumbo.game.Screens.PlayScreen;
import com.jumbo.game.Sprites.Brick;
import com.jumbo.game.Sprites.Coin;

public class B2World {


    public B2World(PlayScreen screen) {

        World world = screen.getWorld() ;
        TiledMap map = screen.getMap() ;

        // Contenu de gestion du player
        BodyDef bodyDef = new BodyDef();
        PolygonShape polygonShape = new PolygonShape();
        FixtureDef fixtureDef = new FixtureDef();
        Body body;


        // Nous recuperons nos objects de notre  tileset

        // Recuperation de nos Objects grounds
        // Tout d abord le ground position index 2
        for (MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
            //objects static
            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set( (rectangle.getX() + rectangle.getWidth()/2)/ Jumbo.PPM  , (rectangle.getY() + rectangle.getHeight()/2) /Jumbo.PPM );

            // On ajoute notre objet au monde (jeu)
            body = world.createBody(bodyDef);

            polygonShape.setAsBox( rectangle.getWidth()/2/Jumbo.PPM  , rectangle.getHeight()/2/Jumbo.PPM );
            fixtureDef.shape = polygonShape;

            body.createFixture(fixtureDef);
        }


        // Recuperation de nos Objects pipes ( les fus )
        // Tout d abord le ground position index 3
        for (MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
            // Les pipes sont des objects static
            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set( (rectangle.getX() + rectangle.getWidth()/2)/ Jumbo.PPM  , (rectangle.getY() + rectangle.getHeight()/2) /Jumbo.PPM );

            // On ajoute notre objet au monde (jeu)
            body = world.createBody(bodyDef);

            polygonShape.setAsBox( rectangle.getWidth()/2/Jumbo.PPM  , rectangle.getHeight()/2/Jumbo.PPM );
            fixtureDef.shape = polygonShape;

            body.createFixture(fixtureDef);
        }

        // Recuperation de nos Objects Coins ( les points )
        // index 4 from game.tmx
        for (MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
            // Les pipes sont des objects static
            new Coin(screen,rectangle);
        }

        // Recuperation de nos Objects Bricks ( les briques )
        // index 5 from game.tmx
        for (MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
            // Les pipes sont des objects static
            new Brick(screen,rectangle);
        }


    }
}
