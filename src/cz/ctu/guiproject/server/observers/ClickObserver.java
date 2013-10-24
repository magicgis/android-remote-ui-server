/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.observers;

import cz.ctu.guiproject.server.events.ClickEvent;

/**
 *
 * @author tomas.buk
 */
public interface ClickObserver extends EventObserver {
    /**
     * When new ClickEvent occurs, all registered ClickObservers are notified
     * within the update method
     *
     * @param e ClickEvent, that currently occured
     */
    public void update(ClickEvent e);
}
