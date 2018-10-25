/*
 * Copyright (c) 2018. This code has been Written by V@ldes Che.
 * Date : 18-06-19 13:53 ...........
 */

package com.jumbo.game.Sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.jumbo.game.Jumbo;
import com.jumbo.game.Screens.PlayScreen;


public class Mario extends Sprite {

    public State getState() {
        if (body.getLinearVelocity().y > 0  || (body.getLinearVelocity().y < 0 && prevouisState == State.JUMPING ))
            return State.JUMPING;

        else if (body.getLinearVelocity().y < 0 )
            return State.FALLING;

        else if (body.getLinearVelocity().x != 0 )
            return State.RUNNING;

        else
            return State.STANDING;
    }

    public enum State {FALLING , JUMPING ,  STANDING , RUNNING};
    public State currentState , prevouisState;

    public World world;
    public Body body;

    private TextureRegion marioStand ;

    private Animation marioRun;
    private Animation marioJump;
    private Animation marioDying;

    private float stateTimer;
    private boolean runningRight;

    public Mario(World world, PlayScreen screen){
        super(screen.getAtlas().findRegion("little_mario"));
        this.world = world;
        currentState = State.STANDING;
        stateTimer = 0;
        runningRight = true;

        Array<TextureRegion>  frames = new Array<TextureRegion>();

        // First anim for Mario Run
        for (int i = 1 ; i < 4; i++)
            frames.add(new TextureRegion(getTexture(),16*i,11,16,16));
        marioRun = new Animation(0.1f,frames);
        frames.clear();

        // First anim for Mario Jump
        for (int i = 4 ; i < 6; i++)
            frames.add(new TextureRegion(getTexture(),16*i,11,16,16));
        marioJump = new Animation(0.1f,frames);
        frames.clear();
/*
        // First anim for Mario Jump
        for (int i = 4 ; i < 6; i++)
            frames.add(new TextureRegion(getTexture(),16*i,11,16,16));
        marioJump = new Animation(0.1f,frames);*/


        // Fonction qui crée Mario
        defineMario();

        marioStand = new TextureRegion(getTexture(),0,11,16,16);

        // defining


        setBounds(0,0,16/Jumbo.PPM , 16/Jumbo.PPM);
        setRegion(marioStand);

    }

    // Mettre à jour la position de Mario
    public void update(float dt){
        setPosition(body.getPosition().x - getWidth()/2,body.getPosition().y - getHeight()/2);


        setRegion(getFrame(dt));
    }

    private TextureRegion getFrame(float dt) {
        currentState = getState();
        TextureRegion region;
        switch (currentState){
            case JUMPING :
                region = (TextureRegion) marioJump.getKeyFrame(stateTimer);
                break;
            case RUNNING:
                region = (TextureRegion) marioRun.getKeyFrame(stateTimer,true);
                break;
            case FALLING:
            case STANDING:
                default:
                  region = marioStand;
                  break;
        }

        if ((body.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX()){
            region.flip(true,false);
            runningRight = false;
        }

        else if ((body.getLinearVelocity().x > 0 || runningRight) && region.isFlipX()){
            region.flip(true,false);
            runningRight = true;
        }

        stateTimer =  (currentState == prevouisState) ? stateTimer+dt : 0;
        prevouisState = currentState;

        return region;


    }

    private void defineMario() {
        BodyDef bodyDef = new BodyDef();
        // Donc il  est toujours à 32 x 32 au depart
        bodyDef.position.set(100/Jumbo.PPM , 10/Jumbo.PPM );

        //bodyDef.position.set(12 , 5 );

        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodyDef);

        FixtureDef fdef = new FixtureDef();
        CircleShape circleShape = new CircleShape();
        // raduis definis aussi le Diamètre
        circleShape.setRadius(8/Jumbo.PPM );

        // category du mask et elts pouvnt collider avec Mario
        fdef.filter.categoryBits = Jumbo.MARIO_BIT;
        fdef.filter.maskBits = Jumbo.DEFAULT_BIT | Jumbo.COIN_BIT | Jumbo.BRICK_BIT;

        fdef.shape = circleShape;
        body.createFixture(fdef);

        // EdgeShape c est juste une ligne entre deux points
        EdgeShape head = new EdgeShape();
        head.set(new Vector2(-3 / Jumbo.PPM ,8/ Jumbo.PPM) , new Vector2(3 / Jumbo.PPM ,8/ Jumbo.PPM));

        fdef.shape = head;

        fdef.isSensor = true;
        body.createFixture(fdef).setUserData("head");

    }


}
