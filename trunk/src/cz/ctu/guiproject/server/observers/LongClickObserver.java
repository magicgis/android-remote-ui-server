/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.observers;

import cz.ctu.guiproject.server.events.LongClickEvent;

/**
 *
 * @author tomas.buk
 */
public interface LongClickObserver {
    // TODO somehow inherit from EventObserver??
    // TODO move to common package
    /**
     * When new LongClickEvent occurs, all registered LongClickObservers are notified
     * within the update method
     *
     * @param event LongClickEvent, that currently occured
     */
    public void update(LongClickEvent event);
}
