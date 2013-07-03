/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package handler;

import messaging.GUIInitSMessage;
import messaging.InitCMessage;
import messaging.InitSMessage;
import messaging.Message;
import networking.Session;
import networking.SessionManager;
import xml.XMLHandler;

/**
 *
 * @author tomique
 */
public class ServerHandler {

    private static ServerHandler instance;
    private SessionManager sessionManager;
    private XMLHandler xmlHandler;

    private ServerHandler() {
        sessionManager = SessionManager.getInstance();
    }

    public static ServerHandler getInstance() {
        if (instance == null) {
            instance = new ServerHandler();
            instance.xmlHandler = XMLHandler.getInstance();
        }
        return instance;
    }

    public void receive(Message message) {

        Message resMessage = null;

        // attempt to establish connection from client side
        if (message instanceof InitCMessage) {

            resMessage = new InitSMessage();

            // create new session
            Session session = sessionManager.getNewSession(((InitCMessage) message).getDevice());


            if (session != null) {
                ((InitSMessage) resMessage).setServerAnswer("200");
                ((InitSMessage) resMessage).setSession(session);
            } else {
                // TODO session je null, proto pokud bude sezeni vice, bude vyhazovat nullPointerException
                ((InitSMessage) resMessage).setServerAnswer("400");
                ((InitSMessage) resMessage).setSession(session);
            }
            
            xmlHandler.send(resMessage);
            
            // comunication almost set, lets send some GUI!
            Message guiInitSMessage = new GUIInitSMessage();
            
            return;
        }
        // TODO not always xmlHandler is called, sometimes only one-way messages are accepted
        xmlHandler.send(resMessage);
    }
}
