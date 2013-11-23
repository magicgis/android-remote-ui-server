/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.business;

import cz.ctu.guiproject.server.events.AndroidEvent;
import cz.ctu.guiproject.server.gui.bitmap.BitmapMixin;
import cz.ctu.guiproject.server.gui.device.ClientDevice;
import cz.ctu.guiproject.server.gui.entity.Component;
import cz.ctu.guiproject.server.gui.entity.Layout;
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
    private Renderer renderer;

    /**
     * Private constructor, used in ServerBusinessAgentImpl singleton design
     * pattern
     */
    private ServerBusinessAgentImpl() {
        serverXMLAgent = new ServerXMLAgentImpl();
        deviceManager = DeviceManager.getInstance();
        eventObservers = new ArrayList<>();
        renderer = DefaultRenderer.getInstance();
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
     * Indicates, that new AndroidEvent occured and causes all EventObservers to
     * be informed
     *
     * @param e
     */
    private void eventReceived(AndroidEvent event) {
        // call business logic, some actions to take(ie. redraw connected devices??)
        Layout layout = renderer.getLayout();
        int[] coord = event.getPoint();
        for(Component comp : layout.getComponents()) {
            if(BitmapMixin.intersects(coord[0], coord[1], comp.getActionArea())) {
                // update particular layout component
                break;
            }
        }
        
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
            logger.log(Level.INFO, "Message received: " + initMessage.getScreenWidth() + "x" + initMessage.getScreenHeight() + ", " + initMessage.getName());
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
        ClientDevice newDevice = new ClientDevice();
        newDevice.setId(initMessage.getSessionId());
        newDevice.setScreenHeight(initMessage.getScreenHeight());
        newDevice.setScreenWidth(initMessage.getScreenWidth());
        newDevice.setName(initMessage.getName());
        // register new device with the deviceMapper
        deviceManager.getDeviceMapper().addDevice(newDevice);
        
        newDevice.registerObserver(this);
        renderer.registerObserver(newDevice);

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
        logger.log(Level.INFO, "ServerBusinessAgentImpl just UPDATED!");
        // form response message and send it to client
        ClientInitResponseMessage responseMessage = new ClientInitResponseMessage();
        responseMessage.setSessionId(device.getId());
        // TODO how to choose format
        responseMessage.setFormat("png");
        responseMessage.setContext(device.getContext());
        send(responseMessage);
    }
}
