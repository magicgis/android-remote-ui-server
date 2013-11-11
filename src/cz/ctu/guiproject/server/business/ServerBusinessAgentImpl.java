/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.business;

import cz.ctu.guiproject.server.events.AndroidEvent;
import cz.ctu.guiproject.server.gui.device.ClientDevice;
import cz.ctu.guiproject.server.gui.manager.DeviceManager;
import cz.ctu.guiproject.server.gui.renderer.DefaultRenderer;
import cz.ctu.guiproject.server.gui.renderer.Renderer;
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
public class ServerBusinessAgentImpl implements ServerBusinessAgent, ServerXMLObserver {

    // singleton related instance
    private static ServerBusinessAgent instance;
    private static ServerXMLAgent serverXMLAgent;
    private DeviceManager deviceManager;
    // TODO nemelo by tohle tak nahodou byt obecnejsi a prijimat typ AndroidMessage??
    private ArrayList<EventObserver<AndroidEvent>> eventObservers;
    // currently received event from android device
    private AndroidEvent currentEvent;
    private Renderer renderer;
    private static final Logger logger = Logger.getLogger(ServerBusinessAgentImpl.class.getName());

    /**
     * Private constructor, used in ServerBusinessAgentImpl singleton design
     * pattern
     */
    private ServerBusinessAgentImpl() {
        serverXMLAgent = new ServerXMLAgentImpl();
        deviceManager = DeviceManager.getInstance();
        renderer = DefaultRenderer.getInstance();
        eventObservers = new ArrayList<>();
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
        // decide between regular message and event message
        if (message instanceof AndroidEvent) {
            eventReceived((AndroidEvent) message);
            return;
        }

        // handle regular message
        messageReceived(message);
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

    private void initNewClient(ClientInitRequestMessage initMessage) {
        // get required GUI layout based on device resolution
        ClientDevice newDevice = new ClientDevice();
        newDevice.setId(initMessage.getSessionId());
        newDevice.setScreenHeight(initMessage.getScreenHeight());
        newDevice.setScreenWidth(initMessage.getScreenWidth());
        newDevice.setName(initMessage.getName());
        // register new device with the deviceMapper
        deviceManager.getDeviceMapper().addDevice(newDevice);

        // inform renderer about the init event
        renderer.initMessageReceived();

        if (newDevice.getContext() == null) {
            throw new RuntimeException("Context of the device should be never null!");
        }
        
        // form response message and send it to client
        ClientInitResponseMessage responseMessage = new ClientInitResponseMessage();
        responseMessage.setSessionId(initMessage.getSessionId());
        // TODO ... context to Base64, ...
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
            System.out.println("Message received: " + initMessage.getScreenWidth() + "x" + initMessage.getScreenHeight() + ", " + initMessage.getName());
            initNewClient(initMessage);
            return;
        }
        throw new RuntimeException("Unknown message received: " + message);
    }

    /**
     * Indicates, that new AndroidEvent occured and causes all EventObservers to
     * be informed
     *
     * @param e
     */
    private void eventReceived(AndroidEvent event) {
        // call business logic, some actions to take(ie. redraw connected devices??)
        currentEvent = event;
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
        for (EventObserver<AndroidEvent> o : eventObservers) {
            o.update(currentEvent);
        }
    }
}
