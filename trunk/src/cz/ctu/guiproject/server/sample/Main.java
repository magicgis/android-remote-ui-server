/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.sample;

import cz.ctu.guiproject.server.events.ClickEvent;
import cz.ctu.guiproject.server.events.TouchEvent;
import cz.ctu.guiproject.server.main.AndroidServer;
import cz.ctu.guiproject.server.main.AndroidServerImpl;
import cz.ctu.guiproject.server.observers.ClickObserver;
import cz.ctu.guiproject.server.observers.TouchObserver;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tomas.buk
 */
public class Main implements ClickObserver, TouchObserver {

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public Main() {
        init();
    }

    private void init() {
        AndroidServer server = new AndroidServerImpl();
        server.registerClickObserver(this);
        server.registerTouchObserver(this);
    }

    public static void main(String[] args) {
        new Main();
    }

    @Override
    public void update(ClickEvent e) {
        logger.log(Level.INFO, "ClickEvent occured: " + e);
    }

    @Override
    public void update(TouchEvent e) {
        logger.log(Level.INFO, "TouchEvent occured: " + e);
    }
}
