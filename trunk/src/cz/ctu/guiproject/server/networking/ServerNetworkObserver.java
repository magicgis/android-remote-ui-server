/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.networking;

/**
 *
 * @author tomas.buk
 */
public interface ServerNetworkObserver {

    /**
     * When particular event occurs, all registered ServerObservers are notified
     * via update method.
     *
     * @param message Message event
     */
    public void update(String message);
}
