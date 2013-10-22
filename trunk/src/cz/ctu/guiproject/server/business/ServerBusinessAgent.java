/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.business;

import cz.ctu.guiproject.server.observers.EventObserver;

/**
 *
 * @author tomas.buk
 */
public interface ServerBusinessAgent {

    /**
     * Registers new EventObserver instance
     *
     * @param o new EventObserver instance
     */
    public void registerObserver(EventObserver o);

    /**
     * Removes particular EventObserver instance
     *
     * @param o EventObserver instance to be removed
     */
    public void removeObserver(EventObserver o);

    /**
     * Notifies all registered observers, that particular event occured
     */
    public void notifyEventObservers();
}
