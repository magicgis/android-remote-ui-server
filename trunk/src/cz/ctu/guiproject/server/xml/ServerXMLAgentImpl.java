/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.xml;

import cz.ctu.guiproject.server.messaging.AndroidMessage;
import cz.ctu.guiproject.server.messaging.AndroidMessageFactory;
import cz.ctu.guiproject.server.networking.ServerNetworkAgent;
import cz.ctu.guiproject.server.networking.ServerNetworkAgentImpl;
import cz.ctu.guiproject.server.networking.ServerNetworkObserver;
import java.util.ArrayList;

/**
 *
 * @author tomas.buk
 */
public class ServerXMLAgentImpl implements ServerNetworkObserver, ServerXMLAgent {

    // handles the network communication with client(s)
    private ServerNetworkAgent serverNetworkAgent;
    private ArrayList<ServerXMLObserver> observers;
    private AndroidMessage currentMessage;

    /**
     * Default constructor of the ServerXMLAgentImpl.
     */
    @SuppressWarnings("LeakingThisInConstructor")
    public ServerXMLAgentImpl() {
        // TODO parse port number from xml config file
        // TODO set port number from network layer!!
        observers = new ArrayList<>();
        serverNetworkAgent = new ServerNetworkAgentImpl(6789);
        serverNetworkAgent.registerObserver(this);
    }

    @Override
    public void update(String message) {
        currentMessage = AndroidMessageFactory.createAndroidMessage(message);
        notifyObservers();
    }

    @Override
    public void registerObserver(ServerXMLObserver o) {
        // TODO zajistit, aby observer prepsal equals a hashcode
        if (!observers.contains(o)) {
            observers.add(o);
        }
    }

    @Override
    public void removeObserver(ServerXMLObserver o) {
        // TODO zajistit, aby observer prepsal equals a hashcode
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        // TODO zajistit, aby observer prepsal equals a hashcode
        for (ServerXMLObserver observer : observers) {
            observer.update(currentMessage);
        }
    }

    @Override
    public void broadcast(AndroidMessage message) {
        String xmlMessage = message.getXml();
        serverNetworkAgent.broadcast(xmlMessage);
    }

    @Override
    public void send(int networkId, AndroidMessage message) {
        String xmlMessage = message.getXml();
        serverNetworkAgent.send(networkId, xmlMessage);
    }
}
