package xml;

import handler.ServerHandler;
import messaging.Message;
import networking.ServerAgentSender;

/**
 *
 * @author tomique
 */
public class XMLHandler {

    private ServerHandler serverHandler;
    private ServerAgentSender agentSender;
    private static XMLHandler instance;

    /**
     * Default constructor
     */
    private XMLHandler() {}

    public static XMLHandler getInstance() {

        if (instance == null) {
            instance = new XMLHandler();
            instance.agentSender = ServerAgentSender.getInstance();
            instance.serverHandler = ServerHandler.getInstance();
        }
        return instance;
    }

    public void receive(String xmlMessage) {

        Message message = XMLToolkit.decodeXML(xmlMessage);
        // pass it further to server
        serverHandler.receive(message);
    }

    public void send(Message message) {
        
        String xmlMessage = message.getXML();
        // to be sent to client
        agentSender.send(xmlMessage);
    }
}