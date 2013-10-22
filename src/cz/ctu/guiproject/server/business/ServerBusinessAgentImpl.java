/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.business;

import cz.ctu.guiproject.server.events.AndroidEvent;
import cz.ctu.guiproject.server.helper.SessionNetworkIdMapper;
import cz.ctu.guiproject.server.observers.EventObserver;
import cz.ctu.guiproject.server.xml.ServerXMLAgent;
import cz.ctu.guiproject.server.xml.ServerXMLAgentImpl;
import cz.ctu.guiproject.server.xml.ServerXMLObserver;
import java.util.ArrayList;
import messaging.Message;

/**
 *
 * @author tomas.buk
 */
public class ServerBusinessAgentImpl implements ServerBusinessAgent, ServerXMLObserver {

    // singleton related instance
    private static ServerBusinessAgent instance;
    private static ServerXMLAgent serverXMLAgent;
    private SessionNetworkIdMapper sessionNetworkIdMapper;
    private ArrayList<EventObserver> eventObservers;
    // currently received event from android device
    private AndroidEvent currentEvent;

    /**
     * Private constructor, used in ServerBusinessAgentImpl singleton design
     * pattern
     */
    private ServerBusinessAgentImpl() {
        serverXMLAgent = new ServerXMLAgentImpl();
        sessionNetworkIdMapper = SessionNetworkIdMapper.getInstance();
        eventObservers = new ArrayList<>();
    }

    /**
     * Main execution logic goes here
     */
    private static void initMainLoop() {
        // main execution logic goes here
        // after client introduces itself, initial layout is sent to him
        // then events on client causes appropriate action on server app
    }

    /**
     * Returns the only existing instance of class ServerBusinessAgentImpl
     *
     * @return The only existing instance of class ServerBusinessAgentImpl
     */
    public static ServerBusinessAgent getInstance() {
        if (instance == null) {
            instance = new ServerBusinessAgentImpl();
            serverXMLAgent.registerObserver((ServerXMLObserver) instance);
            initMainLoop();
        }
        return instance;
    }

    @Override
    public void update(Message message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Based on the information of the message "header" decides to send the
     * message to particular client or broadcasts to all clients.
     *
     * @param message Message to be sent to a particular client
     */
    private void send(Message message) {
        String sessionId = message.getSessionId();
        switch (sessionId) {
            case "BROADCAST":
                serverXMLAgent.broadcast(message);
                break;
            default:
                // map sessionId to networkId
                int networkId = sessionNetworkIdMapper.getNetworkId(sessionId);
                serverXMLAgent.send(networkId, message);
        }
    }

    /**
     * Indicates, that new AndroidEvent occured and causes all EventObservers to
     * be informed
     *
     * @param e
     */
    private void eventOccured(AndroidEvent e) {
        currentEvent = e;
        notifyEventObservers();
    }

    @Override
    public void registerObserver(EventObserver o) {
        if (!eventObservers.contains(o)) {
            eventObservers.add(o);
        }
    }

    @Override
    public void removeObserver(EventObserver o) {
        eventObservers.remove(o);
    }

    @Override
    public void notifyEventObservers() {
        for (EventObserver o : eventObservers) {
            o.update(currentEvent);
        }
    }
}
