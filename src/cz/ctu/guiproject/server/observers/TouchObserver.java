/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.observers;

import cz.ctu.guiproject.server.events.TouchEvent;

/**
 *
 * @author tomas.buk
 */
public interface TouchObserver {
    // TODO somehow inherit from EventObserver??
    /**
     * When new TouchEvent occurs, all registered TouchObservers are notified
     * within the update method
     *
     * @param e TouchEvent, that currently occured
     */
    public void update(TouchEvent event);
}
