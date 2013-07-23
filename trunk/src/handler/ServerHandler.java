/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package handler;

import android_toolkit.AndroidDevice;
import event.Event;
import event.OnClickEvent;
import event.OnLongClickEvent;
import event.OnTouchEvent;
import java.awt.image.BufferedImage;
import main.ActionArea;
import gui_manager.GUIManager;
import java.util.ArrayList;
import messaging.EventCMessage;
import messaging.GUIInitSMessage;
import messaging.InitCMessage;
import messaging.InitSMessage;
import messaging.Message;
import networking.Session;
import networking.SessionManager;
import user.test.GUIServer;
import xml.XMLHandler;

/**
 *
 * @author tomique
 */
public class ServerHandler {

    private static ServerHandler instance;
    private SessionManager sessionManager;
    private XMLHandler xmlHandler;
    private GUIManager guiManager;
    private EventHandler eventHandler;
    private GUIServer guiServer;

    private ServerHandler() {
        sessionManager = SessionManager.getInstance();
    }

    public static ServerHandler getInstance() {
        if (instance == null) {
            instance = new ServerHandler();
            instance.xmlHandler = XMLHandler.getInstance();
            instance.guiManager = GUIManager.getInstance();
            instance.eventHandler = EventHandler.getInstance();
            instance.guiServer = GUIServer.getInstance();
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
                xmlHandler.send(resMessage);
                return;
            }

            System.out.println("InitCMessage received.");
            xmlHandler.send(resMessage);
            System.out.println("InitSMessage sent.");

            // comunication almost set, lets send some GUI!
            AndroidDevice android = ((InitCMessage) message).getDevice();

            Message guiInitSMessage = new GUIInitSMessage();

            BufferedImage img = guiManager.getImage(android.getResolution());

            ((GUIInitSMessage) guiInitSMessage).setRenderedComponent(img);
            ((GUIInitSMessage) guiInitSMessage).setSession(session);
            // TODO tohle se asi bude nastavovat nekde v xml...
            ((GUIInitSMessage) guiInitSMessage).setRenderedComponentFormat("png");

            xmlHandler.send(guiInitSMessage);
            System.out.println("GUIInitSMessage sent.");
            
            // update GUIServer
            guiServer.setDevice(android);
            guiServer.setStatus(GUIServer.Status.CONNECTED);

            return;
        }

        if (message instanceof EventCMessage) {

            EventCMessage myMessage = (EventCMessage) message;
            Event event;
            
            // zjistit, jake action area se event tyka
            ActionArea eventArea;
            if(myMessage.getPoint() != null) {
                eventArea = guiManager.getEventActionArea(myMessage.getPoint());
            } else {
                ArrayList<int[]> list = myMessage.getPointList();
                eventArea = guiManager.getEventActionArea(list.get(list.size() - 1));
            }
            

            switch (myMessage.getEventType()) {
                case "onClick":
//                    System.out.println("onClick");
                    
                    event = new OnClickEvent(myMessage.getPoint());
                    eventHandler.notifyObservers(event, eventArea);
                    
                    break;
                case "onLongClick":
                    
                    event = new OnLongClickEvent(myMessage.getPoint());
                    eventHandler.notifyObservers(event, eventArea);

                    break;
                case "onTouch":

                    switch (myMessage.getAction()) {
                        case "ACTION_UP":
//                            System.out.println("ACTION_UP");

                            // TODO predavat null asi neni zrovna nejvhodnejsi, i kdyz se pravdepodobne uz s touto promennou pracovat nebude
                            event = new OnTouchEvent(null);
                            ((OnTouchEvent) event).setAction("ACTION_UP");
                            ((OnTouchEvent) event).setPointList(myMessage.getPointList());
                            eventHandler.notifyObservers(event, eventArea);

                            break;
                        case "ACTION_MOVE":
//                            System.out.println("ACTION_MOVE" + Arrays.toString(myMessage.getPoint()));
                            
                            event = new OnTouchEvent(myMessage.getPoint());
                            ((OnTouchEvent) event).setAction("ACTION_MOVE");
                            
                            eventHandler.notifyObservers(event, eventArea);
                            
                            break;
                        case "ACTION_DOWN":
//                            System.out.println("ACTION_DOWN" + Arrays.toString(myMessage.getPoint()));
                            
                            event = new OnTouchEvent(myMessage.getPoint());
                            ((OnTouchEvent) event).setAction("ACTION_DOWN");
                            
                            eventHandler.notifyObservers(event, eventArea);
                            
                            break;
                    }
                    break;
            }
            return;
        }

        throw new RuntimeException("Accepted message not recognized!");

        // TODO not always xmlHandler is called, sometimes only one-way messages are accepted
//        xmlHandler.send(resMessage);
    }
}
