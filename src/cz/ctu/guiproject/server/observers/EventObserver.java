/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.observers;

import cz.ctu.guiproject.server.gui.entity.Component;

/**
 *
 * @author tomas.buk
 */
public interface EventObserver<T> {
    // TODO move to common package
    /**
     * When new Event occurs, all registered Observers are notified within the
     * update method
     *
     * @param event Event, that currently occured
     */
    public void update(T event);
}
