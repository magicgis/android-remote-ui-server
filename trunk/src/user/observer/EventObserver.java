/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package user.observer;

import event.Event;
import main.ActionArea;

/**
 *
 * @author tomique
 */
public interface EventObserver {
    
    /**
     *
     * @param event
     * @param area
     */
    public void eventOccurred(Event event, ActionArea area);
}
