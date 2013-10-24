/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.main;

import cz.ctu.guiproject.server.business.ServerBusinessAgent;
import cz.ctu.guiproject.server.business.ServerBusinessAgentImpl;
import cz.ctu.guiproject.server.events.ClickEvent;
import cz.ctu.guiproject.server.events.TouchEvent;
import cz.ctu.guiproject.server.observers.ClickObserver;
import cz.ctu.guiproject.server.observers.TouchObserver;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tomas.buk
 */
public class AndroidServerImpl implements AndroidServer, ClickObserver, TouchObserver {

    private ServerBusinessAgent serverBusinessAgent;
    private List<TouchObserver> touchObservers;
    private List<ClickObserver> clickObservers;
    private TouchEvent currentTouchEvent;
    private ClickEvent currentClickEvent;

    @SuppressWarnings("LeakingThisInConstructor")
    public AndroidServerImpl() {
        touchObservers = new ArrayList<>();
        clickObservers = new ArrayList<>();
        serverBusinessAgent = ServerBusinessAgentImpl.getInstance();
        // must be the last thing to execute
        serverBusinessAgent.registerObserver(this);
    }

    /**
     * Called when new TouchEvent occurs and appropriate observers are to be
     * notified
     *
     * @param currentTouchEvent Current TouchEvent, that just occured
     */
    public void touchEventOccured(TouchEvent currentTouchEvent) {
        this.currentTouchEvent = currentTouchEvent;
        notifyTouchObservers();
    }

    /**
     * Called when new ClickEvent occured and appropriate observers are to be
     * notified
     *
     * @param currentClickEvent Current ClickEvent, that just occured
     */
    public void clickEventOccured(ClickEvent currentClickEvent) {
        this.currentClickEvent = currentClickEvent;
        notifyClickObserver();
    }

    @Override
    public void registerTouchObserver(TouchObserver o) {
        if (!touchObservers.contains(o)) {
            touchObservers.add(o);
        }
    }

    @Override
    public void removeTouchObserver(TouchObserver o) {
        touchObservers.remove(o);
    }

    @Override
    public void notifyTouchObservers() {
        for (TouchObserver o : touchObservers) {
            o.update(currentTouchEvent);
        }
    }

    @Override
    public void registerClickObserver(ClickObserver o) {
        if (!clickObservers.contains(o)) {
            clickObservers.add(o);
        }
    }

    @Override
    public void removeClickObserver(ClickObserver o) {
        clickObservers.remove(o);
    }

    @Override
    public void notifyClickObserver() {
        for (ClickObserver o : clickObservers) {
            o.update(currentClickEvent);
        }
    }

    @Override
    public void update(TouchEvent e) {
        touchEventOccured(e);
    }

    @Override
    public void update(ClickEvent e) {
        clickEventOccured(e);
    }
}
