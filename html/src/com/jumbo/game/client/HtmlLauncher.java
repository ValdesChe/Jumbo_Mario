/*
 * Copyright (c) 2018. This code has been Written by V@ldes Che.
 * Date : 18-06-19 13:53 ...........
 */

package com.jumbo.game.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.jumbo.game.Jumbo;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                return new GwtApplicationConfiguration(480, 320);
        }

        @Override
        public ApplicationListener createApplicationListener () {
                return new Jumbo();
        }
}