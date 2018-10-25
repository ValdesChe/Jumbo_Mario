/*
 * Copyright (c) 2018. This code has been Written by V@ldes Che.
 * Date : 18-06-19 13:53 ...........
 */

package com.jumbo.game.Screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.jumbo.game.Scenes.Hud;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jumbo.game.Jumbo;
import com.jumbo.game.Scenes.Hud;
import com.jumbo.game.Sprites.Mario;
import com.jumbo.game.Tools.B2World;
import com.jumbo.game.Tools.Controller;
import com.jumbo.game.Tools.WorlContactListener;


/**
 * Created by Valdes on 2017-03-21.
 */

// Ecran de jeu
public class PlayScreen  implements Screen {

    private Jumbo game;
    Texture texture;

    private TextureAtlas atlas;

    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private Hud hud;

    // after creating my tiledMap
    //
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    // Then makes instances of this

    private Box2DDebugRenderer b2dr ;
    // Declaration du monde
    private World world;
    // Notre player influencé par la physique
    private Mario player ;


    //  For touch
    public Vector2 position, touchPosition;

    private Music music;

    // Control du player
    Controller controller ;

    public PlayScreen(Jumbo game){


        this.game = game;
        texture = new Texture("badlogic.png");

        music = Jumbo.assetManager.get("audio/music/mario_music.ogg", Music.class);
        music.setLooping(true);
        music.play();

        atlas = new TextureAtlas("Mario_and_Enemies.pack");

        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(Jumbo.V_WIDTH/Jumbo.PPM  ,Jumbo.V_HEIGHT/Jumbo.PPM ,gamecam);
        hud = new Hud(game.batch);

        // initializing object
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("game.tmx");

        renderer = new OrthogonalTiledMapRenderer(map, 1/Jumbo.PPM );

        //positioning map into the center of our screen
        gamecam.position.set(gamePort.getWorldWidth() /2 , gamePort.getWorldHeight() /2, 0);




        // Definition du monde : vect(0,0) sans gravity
        world = new World(new Vector2(0,-5 ), true);
        // Pour debug dans le monde reel
        b2dr = new Box2DDebugRenderer();
        b2dr.SHAPE_STATIC.set(0,0,0,1);
        b2dr.SHAPE_AWAKE.set(0,0,0,1);

        new B2World(this);
        // OUr player xD

        controller = new Controller();
        player = new Mario(this.world, this);
        //System.out.println("My Screen : "+  gamePort.getWorldWidth() +" %%% "+ gamePort.getWorldHeight() );
        world.setContactListener(new WorlContactListener());


    }

    public TextureAtlas getAtlas() {
        return atlas;
    }

    @Override
    public void show() {

    }

    // This method listen to event on keybord or..
    // and it s called in update method
    public void handleInput(float dt){

        position = new Vector2(0,0);
        Vector2 touchPosition = new Vector2(0,0);

        touchPosition.x = Math.abs( Gdx.input.getX()) ;
        touchPosition.y = Math.abs( Gdx.input.getY());


        //System.out.println("Position of Player : "+ player.body.getPosition().x +" %%% "+player.body.getPosition().y);
        // TOUCHED
       /* if(Gdx.input.isTouched()){
            System.out.println("Velcity of Player : "+player.body.getLinearVelocity().x +" %%% "+ player.body.getLinearVelocity().y);
            System.out.println("Position of Player : "+ player.body.getPosition().x +" %%% "+player.body.getPosition().y);
            System.out.println("Position of Touch : "+  touchPosition.x +" %%% "+ touchPosition.y );

        }*/

        // UP
        //if((Gdx.input.isTouched() && touchPosition.y > player.body.getPosition().y) && player.body.getLinearVelocity().x <= 3 && player.body.getLinearVelocity().y <= 3){
        if(controller.isUpPressed()){
            System.out.println("En Haut");
            //gamecam.position.x += 20*dt;
            player.body.applyLinearImpulse(new Vector2(0,4f),player.body.getWorldCenter(),true);
        }

        // RIGHT
        // || (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)
        //if( ( Gdx.input.isTouched() && touchPosition.x > player.body.getPosition().x) && player.body.getLinearVelocity().x <= 3 ) {
        if( controller.isRightPressed() && player.body.getLinearVelocity().x <= 2 ) {
            System.out.println("En Avant");
            //position.x += 2f;
            //gamecam.position.x += 20*dt;
            player.body.applyLinearImpulse(new Vector2(0.4f,0),player.body.getWorldCenter(),true);
        }

        // LEFT
        //if ( (Gdx.input.isTouched() && touchPosition.x < player.body.getPosition().x) && player.body.getLinearVelocity().x >= -3 ){
        if (controller.isLeftPressed() && player.body.getLinearVelocity().x >= -2 ){
            System.out.println("En Arriere");
            //position.x -= 2f;
            //gamecam.position.x -= 20*dt;
            player.body.applyLinearImpulse(new Vector2(-0.4f,0),player.body.getWorldCenter(),true);
        }
/*
        if(touchPosition.y > player.getY()){
            position.y += 2f;
            //gamecam.position.y += 100*dt;
            player.body.applyLinearImpulse(new Vector2(0,2f),player.body.getWorldCenter(),true);
        }

        if (touchPosition.y <player.getY()){
            position.y -= 2f;
            //gamecam.position.y -= 100*dt;
        }*/

    }

    // dt or delta time
    // This method is used to listen the events on the screen or keyboard
    // exple : any key is pressed or screeen is touched
    public void update(float dt){
        handleInput(dt);

       // Apres combien de temps souhaite t on actualiser
        world.step(1/5f , 6, 2);

        // On veut suivre mario avec  la camera
        gamecam.position.x = player.body.getPosition().x;

        // On met a jour mario
        player.update(dt);

        // time update
        hud.update(dt);

        //System.out.println(" Update called :" + dt);
        gamecam.update();
        renderer.setView(gamecam);
    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(145,120,120 ,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        /*
        game.batch.begin();
        game.batch.draw(texture, 0, 0);
        game.batch.end();*/

        renderer.render();
        b2dr.render(world,gamecam.combined);

        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();
        player.draw(game.batch);
        game.batch.end();

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

        if (Gdx.app.getType() == Application.ApplicationType.Android)
            controller.draw();

    }

    @Override
    public void resize(int width, int height) {
       gamePort.update(width,height);
       controller.resize(width,height);
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
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
        hud.dispose();
    }

    public World getWorld() {
        return world;
    }

    public TiledMap getMap() {
        return map;
    }
}
