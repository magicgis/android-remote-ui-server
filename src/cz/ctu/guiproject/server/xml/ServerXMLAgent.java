/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.xml;

import cz.ctu.guiproject.server.messaging.AndroidMessage;

/**
 *
 * @author tomas.buk
 */
public interface ServerXMLAgent {

    /**
     * Sends the message to all registered client machines
     *
     * @param message
     */
    public void broadcast(AndroidMessage message);

    /**
     * Sends the message to particular client identified by unique networkId
     *
     * @param networkId networkId of the client
     * @param message Message object for the client
     */
    public void send(int networkId, AndroidMessage message);

    /**
     * Adds new ServerXMLAgentObserver into the list of observers.
     *
     * @param o New observer
     */
    public void registerObserver(ServerXMLObserver o);

    /**
     * Removes specified ServerXMLAgentObserver from the list of observers.
     *
     * @param o Observer to be removed.
     */
    public void removeObserver(ServerXMLObserver o);

    /**
     * When new event occurs, all registered observers are notified.
     */
    public void notifyObservers();
}
