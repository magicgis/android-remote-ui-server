/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.observers;

/**
 *
 * @author tomas.buk
 */
public interface EventObserver<T> {

    /**
     * When new Event occurs, all registered Observers are notified within the
     * update method
     *
     * @param event Event, that currently occured
     */
    public void update(T event);
}
