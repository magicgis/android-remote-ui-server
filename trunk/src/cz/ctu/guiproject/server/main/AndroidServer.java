/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.main;

import cz.ctu.guiproject.server.observers.ClickObserver;
import cz.ctu.guiproject.server.observers.DragObserver;
import cz.ctu.guiproject.server.observers.LongClickObserver;
import cz.ctu.guiproject.server.observers.TouchObserver;

/**
 *
 * @author tomas.buk
 */
public interface AndroidServer {

    /**
     * Registers new LongClickObserver instance
     *
     * @param o new LongClickObserver instance
     */
    public void registerLongClickObserver(LongClickObserver o);

    /**
     * Removes LongClickObserver instance
     *
     * @param o new LongClickObserver instance
     */
    public void removeLongClickObserver(LongClickObserver o);

    /**
     * Notify all registered observers, that new LongClickEvent occured
     */
    public void notifyLongClickObservers();

    /**
     * Registers new DragObserver instance
     *
     * @param o new DragObserver instance
     */
    public void registerDragObserver(DragObserver o);

    /**
     * Removes DragObserver instance
     *
     * @param o new DragObserver instance
     */
    public void removeDragObserver(DragObserver o);

    /**
     * Notify all registered observers, that new DragEvent occured
     */
    public void notifyDragObservers();

    /**
     * Registers new TouchObserver instance
     *
     * @param o new TouchObserver instance
     */
    public void registerTouchObserver(TouchObserver o);

    /**
     * Removes TouchObserver instance
     *
     * @param o new TouchObserver instance
     */
    public void removeTouchObserver(TouchObserver o);

    /**
     * Notify all registered observers, that new TouchEvent occured
     */
    public void notifyTouchObservers();

    /**
     * Registers new ClickObserver instance
     *
     * @param o new ClickObserver instance
     */
    public void registerClickObserver(ClickObserver o);

    /**
     * Removes ClickObserver instance from the list of registered observers
     *
     * @param o ClickObserver instance to be removed
     */
    public void removeClickObserver(ClickObserver o);

    /**
     * Notify all registered observers, that the ClickEvent Occured
     */
    public void notifyClickObserver();
}
