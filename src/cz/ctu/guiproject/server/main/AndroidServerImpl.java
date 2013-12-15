/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.main;

import cz.ctu.guiproject.server.business.ServerBusinessAgent;
import cz.ctu.guiproject.server.business.ServerBusinessAgentImpl;
import cz.ctu.guiproject.server.events.AndroidEvent;
import cz.ctu.guiproject.server.events.ClickEvent;
import cz.ctu.guiproject.server.events.DragEvent;
import cz.ctu.guiproject.server.events.LongClickEvent;
import cz.ctu.guiproject.server.events.TouchEvent;
import cz.ctu.guiproject.server.gui.entity.Component;
import cz.ctu.guiproject.server.gui.entity.DefaultRadioButton;
import cz.ctu.guiproject.server.observers.ClickObserver;
import cz.ctu.guiproject.server.observers.DragObserver;
import cz.ctu.guiproject.server.observers.EventObserver;
import cz.ctu.guiproject.server.observers.LongClickObserver;
import cz.ctu.guiproject.server.observers.TouchObserver;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tomas.buk
 */
public class AndroidServerImpl implements AndroidServer, EventObserver<AndroidEvent> {

    private ServerBusinessAgent serverBusinessAgent;
    private List<TouchObserver> touchObservers;
    private List<ClickObserver> clickObservers;
    private List<DragObserver> dragObservers;
    private List<LongClickObserver> longClickObservers;
    private TouchEvent currentTouchEvent;
    private ClickEvent currentClickEvent;
    private DragEvent currentDragEvent;
    private LongClickEvent currentLongClickEvent;

    @SuppressWarnings("LeakingThisInConstructor")
    public AndroidServerImpl() {
        touchObservers = new ArrayList<>();
        clickObservers = new ArrayList<>();
        dragObservers = new ArrayList<>();
        longClickObservers = new ArrayList<>();
        serverBusinessAgent = ServerBusinessAgentImpl.getInstance();
        // must be the last thing to execute
        serverBusinessAgent.registerObserver(this);
        // TODO sdelit serveru, zda se zpravy, ktere zpusobi vykresleni 
        // prvku GUI na strane klienta budou posilat ve formatu Base64, 
        // nebo formou prikazu pro vykresleni konkretnich primitiv 
        // (pokud bude vykreslovani v zavislosti na uzivatelske akce 
        // ridit sam uzivatel, bude vzdy pouzito formatu Base64!)
    }

    /**
     * Called when new TouchEvent occurs and appropriate observers are to be
     * notified
     *
     * @param currentTouchEvent Current TouchEvent, that just occured
     */
    private void touchEventOccured(TouchEvent currentTouchEvent) {
        this.currentTouchEvent = currentTouchEvent;
        notifyTouchObservers();
    }

    /**
     * Called when new ClickEvent occured and appropriate observers are to be
     * notified
     *
     * @param currentClickEvent Current ClickEvent, that just occured
     */
    private void clickEventOccured(ClickEvent currentClickEvent) {
        this.currentClickEvent = currentClickEvent;
        notifyClickObservers();
    }

    /**
     * Called when new DragEvent occurs and appropriate observers are to be
     * notified
     *
     * @param currentDragEvent Current DragEvent, that just occured
     */
    private void dragEventOccured(DragEvent currentDragEvent) {
        this.currentDragEvent = currentDragEvent;
        notifyDragObservers();
    }

    /**
     * Called when new LongClickEvent occurs and appropriate observers are to be
     * notified
     *
     * @param currentLongClickEvent Current LongClickEvent, that just occured
     */
    private void longClickEventOccured(LongClickEvent currentLongClickEvent) {
        this.currentLongClickEvent = currentLongClickEvent;
        notifyLongClickObservers();
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
    public void notifyClickObservers() {
        for (ClickObserver o : clickObservers) {
            o.update(currentClickEvent);
        }
    }

    @Override
    public void update(AndroidEvent event) {
        if (event instanceof ClickEvent) {
            clickEventOccured((ClickEvent) event);
        } else if (event instanceof TouchEvent) {
            touchEventOccured((TouchEvent) event);
        } else if (event instanceof DragEvent) {
            dragEventOccured((DragEvent) event);
        } else if (event instanceof LongClickEvent) {
            longClickEventOccured((LongClickEvent) event);
        } else {
            throw new RuntimeException("Unsupported event type, add another instanceof case!");
        }
    }

    @Override
    public void registerLongClickObserver(LongClickObserver o) {
        if (!longClickObservers.contains(o)) {
            longClickObservers.add(o);
        }
    }

    @Override
    public void removeLongClickObserver(LongClickObserver o) {
        longClickObservers.remove(o);
    }

    @Override
    public void notifyLongClickObservers() {
        for (LongClickObserver o : longClickObservers) {
            o.update(currentLongClickEvent);
        }
    }

    @Override
    public void registerDragObserver(DragObserver o) {
        if (!dragObservers.contains(o)) {
            dragObservers.add(o);
        }
    }

    @Override
    public void removeDragObserver(DragObserver o) {
        dragObservers.remove(o);
    }

    @Override
    public void notifyDragObservers() {
        for (DragObserver o : dragObservers) {
            o.update(currentDragEvent);
        }
    }
}
