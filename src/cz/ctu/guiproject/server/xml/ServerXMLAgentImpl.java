/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.xml;

import cz.ctu.guiproject.server.networking.ServerNetworkAgent;
import cz.ctu.guiproject.server.networking.ServerNetworkAgentImpl;
import cz.ctu.guiproject.server.networking.ServerNetworkObserver;
import java.util.ArrayList;
import messaging.Message;
import xml.XMLToolkit;

/**
 *
 * @author tomas.buk
 */
public class ServerXMLAgentImpl implements ServerNetworkObserver, ServerXMLAgent {

    // handles the network communication with client(s)
    private ServerNetworkAgent serverNetworkAgent;
    private ArrayList<ServerXMLObserver> observers;
    private Message currentMessage;

    /**
     * Default constructor of the ServerXMLAgentImpl.
     */
    public ServerXMLAgentImpl() {
        // TODO parse port number from xml config file
        // TODO set port number from network layer!!
        serverNetworkAgent = new ServerNetworkAgentImpl(6789);
        serverNetworkAgent.registerObserver(this);
        observers = new ArrayList<>();
    }

    @Override
    public void update(String message) {
        // TODO parse message, get message object and send it to the superior layer
        currentMessage = XMLToolkit.decodeXML(message);
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
        for (ServerXMLObserver observer : observers) {
            observer.update(currentMessage);
        }
    }

    @Override
    public void broadcast(Message message) {
        // TODO encode the message to plain String
        String xmlMessage = message.getXML();
        serverNetworkAgent.broadcast(xmlMessage);
    }

    @Override
    public void send(int networkId, Message message) {
        // TODO encode the message to plain String        
        String xmlMessage = message.getXML();
        serverNetworkAgent.send(networkId, xmlMessage);
    }
}
