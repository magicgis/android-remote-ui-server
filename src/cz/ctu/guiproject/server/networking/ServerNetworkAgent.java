/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.networking;

/**
 *
 * @author tomas.buk
 */
public interface ServerNetworkAgent {

    /**
     * State, that indicates, whether the server is running or not.
     */
    enum State {

        STOPPED, RUNNING;
    };

    /**
     * Sends a message to all connected clients
     */
    public void broadcast(String message);

    /**
     * Sends a message to the specified client
     *
     * @param message
     */
    public void send(int networkId, String message);

    /**
     * Adds new observer to the list of observers
     *
     * @param o
     */
    public void registerObserver(ServerNetworkObserver o);

    /**
     * Removes specified observer from the list of observers
     *
     * @param o
     */
    public void removeObserver(ServerNetworkObserver o);

    /**
     * Informs all registered observer, that an event occured.
     */
    public void notifyObservers();
}