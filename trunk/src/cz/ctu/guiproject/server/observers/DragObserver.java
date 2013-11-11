/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.observers;

import cz.ctu.guiproject.server.events.DragEvent;

/**
 *
 * @author tomas.buk
 */
public interface DragObserver {
    // TODO somehow inherit from EventObserver??
    // TODO move to common package
    /**
     * When new DragEvent occurs, all registered DragObservers are notified
     * within the update method
     *
     * @param event DragEvent, that currently occured
     */
    public void update(DragEvent event);
}
