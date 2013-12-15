/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.business;

import cz.ctu.guiproject.server.events.AndroidEvent;
import cz.ctu.guiproject.server.gui.device.ClientDevice;
import cz.ctu.guiproject.server.gui.manager.DeviceManager;
import cz.ctu.guiproject.server.gui.manager.EventManager;
import cz.ctu.guiproject.server.messaging.AndroidMessage;
import cz.ctu.guiproject.server.messaging.ClientInitRequestMessage;
import cz.ctu.guiproject.server.messaging.ClientInitResponseMessage;
import cz.ctu.guiproject.server.observers.EventObserver;
import cz.ctu.guiproject.server.xml.ServerXMLAgent;
import cz.ctu.guiproject.server.xml.ServerXMLAgentImpl;
import cz.ctu.guiproject.server.xml.ServerXMLObserver;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tomas.buk
 */
public class ServerBusinessAgentImpl implements ServerBusinessAgent, ServerXMLObserver, ContextObserver {

    // singleton related instance
    private static ServerBusinessAgent instance;
    private static ServerXMLAgent serverXMLAgent;
    private DeviceManager deviceManager;
    // TODO nemelo by tohle tak nahodou byt obecnejsi a prijimat typ AndroidMessage??
    private ArrayList<EventObserver<AndroidEvent>> eventObservers;
    // currently received event from android device
    private AndroidEvent currentEvent;
    private static final Logger logger = Logger.getLogger(ServerBusinessAgentImpl.class.getName());
    private EventManager eventManager;

    /**
     * Private constructor, used in ServerBusinessAgentImpl singleton design
     * pattern
     */
    private ServerBusinessAgentImpl() {
        serverXMLAgent = new ServerXMLAgentImpl();
        deviceManager = DeviceManager.getInstance();
        eventObservers = new ArrayList<>();
        eventManager = EventManager.getInstance();
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
            // TODO inside new thread??
            initMainLoop();
        }
        return instance;
    }

    /**
     * Main execution logic goes here
     */
    private static void initMainLoop() {
        // main execution logic goes here
        // after client introduces itself, initial layout is sent to him
        // then events on client causes appropriate action on server app
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                }
            }
        }).start();
    }

    @Override
    public void update(AndroidMessage message) {
//        logger.log(Level.INFO, "message received!!!!!!");
        // decide between regular message and event message
        if (message instanceof AndroidEvent) {
            eventReceived((AndroidEvent) message);
            return;
        }

        // handle regular message
        messageReceived(message);
    }

    /**
     * Indicates, that new AndroidEvent occured and causes all EventObservers to
     * be informed. Also, EventManager is called to update all connected client
     * devices.
     *
     * @param e
     */
    private void eventReceived(AndroidEvent event) {

        eventManager.eventOccured(event);
        currentEvent = event;
        notifyEventObservers();
    }

    /**
     * Indicates, that new AndroidMessage has been received and necessary cast
     * operation is performed.
     *
     * @param message received message
     */
    private void messageReceived(AndroidMessage message) {
        if (message instanceof ClientInitRequestMessage) {
            ClientInitRequestMessage initMessage = (ClientInitRequestMessage) message;
//            logger.log(Level.INFO, "Message received: " + initMessage.getScreenWidth() + "x" + initMessage.getScreenHeight() + ", " + initMessage.getName());
            initNewClient(initMessage);
            return;
        }
        throw new RuntimeException("Unknown message received: " + message);
    }

    /**
     * Creates new client device based on information from initMessage, adds
     * this device to deviceManager, sets its context based on informations from
     * renderer, then forms responseMessage and sends it back to client
     *
     * @param initMessage
     */
    private void initNewClient(ClientInitRequestMessage initMessage) {
        // get required GUI layout based on device resolution
        ClientDevice newDevice = new ClientDevice(initMessage.getSessionId(),
                initMessage.getName(),
                initMessage.getScreenWidth(),
                initMessage.getScreenHeight(),
                initMessage.getDpi());

        // register new device with the deviceMapper
        deviceManager.getDeviceMapper().addDevice(newDevice);

        newDevice.registerObserver(this);
        // dodelat metodu pro ziskani cele stranky, ne jen vyrezu
        newDevice.updateContext(null);
//        renderer.registerObserver(newDevice);
    }

    /**
     * Based on the information of the message "header" decides to send the
     * message to particular client or broadcasts to all clients.
     *
     * @param message Message to be sent to a particular client
     */
    private void send(AndroidMessage message) {
        String sessionId = message.getSessionId();
        switch (sessionId) {
            case "BROADCAST":
                serverXMLAgent.broadcast(message);
                break;
            default:
                // map sessionId to networkId
                int networkId = deviceManager.getIdMapper().getNetworkId(sessionId);
                serverXMLAgent.send(networkId, message);
        }
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
        for (EventObserver<AndroidEvent> o : eventObservers) {
            o.update(currentEvent);
        }
    }

    @Override
    public void update(String context, ClientDevice device) {
        logger.log(Level.INFO, "ServerBusinessAgentImpl just INITIALIZED!");
        // form response message and send it to client
        ClientInitResponseMessage responseMessage = new ClientInitResponseMessage();
        responseMessage.setSessionId(device.getId());
        // TODO how to choose format
        responseMessage.setFormat("png");
        responseMessage.setContext(device.getContext());
        send(responseMessage);
    }
    
    @Override
    public void update(String context, ClientDevice device, int[] updateArea) {
        logger.log(Level.INFO, "ServerBusinessAgentImpl just UPDATED!");
        ClientInitResponseMessage responseMessage = new ClientInitResponseMessage();
        responseMessage.setSessionId(device.getId());
        
        responseMessage.setFormat("png");
        responseMessage.setContext(device.getContext());
        responseMessage.setUpdateArea(updateArea);
        send(responseMessage);
    }
}
