/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.business;

import cz.ctu.guiproject.server.xml.ServerXMLAgent;
import cz.ctu.guiproject.server.xml.ServerXMLAgentImpl;
import cz.ctu.guiproject.server.xml.ServerXMLObserver;
import messaging.Message;

/**
 *
 * @author tomas.buk
 */
public class ServerBusinessAgentImpl implements ServerBusinessAgent, ServerXMLObserver {

    // singleton related instance
    private static ServerBusinessAgent instance;
    private ServerXMLAgent serverXMLAgent;

    /**
     * Private constructor, used in ServerBusinessAgentImpl singleton design
     * pattern
     */
    private ServerBusinessAgentImpl() {
        serverXMLAgent = new ServerXMLAgentImpl();
        serverXMLAgent.registerObserver(this);
    }

    /**
     * Returns the only existing instance of class ServerBusinessAgentImpl
     *
     * @return The only existing instance of class ServerBusinessAgentImpl
     */
    public static ServerBusinessAgent getInstance() {
        if (instance == null) {
            instance = new ServerBusinessAgentImpl();
        }
        return instance;
    }

    @Override
    public void update(Message message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Based on the information of the message header decides to send the
     * message to particular client or broadcasts to all clients.
     *
     * @param message Message to be sent to a particular client
     */
    private void send(Message message) {
        // TODO broadcast or not??
    }
}
