/*
 * Copyright (c) 2018. This code has been Written by V@ldes Che.
 * Date : 18-06-19 13:53 ...........
 */

package com.jumbo.game.Tools;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.jumbo.game.Sprites.InterractiveTileObject;

public class WorlContactListener implements ContactListener {
    // Debut de la collision
    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        if (fixA.getUserData() == "head" || fixB.getUserData() == "head"){
            Fixture head = (fixA.getUserData() == "head" )? fixA : fixB;
            Fixture object = (head == fixA ) ? fixB : fixA;

            if (object.getUserData() instanceof InterractiveTileObject){
                ((InterractiveTileObject) object.getUserData()).OnHeadHit();
            }
        }
    }

    // Fin de la collision
    @Override
    public void endContact(Contact contact) {
        System.out.println("End");
    }

    // Changer les caracteristiques d'1 object after collision
    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    // Changer les Consequences d'1 collision
    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
