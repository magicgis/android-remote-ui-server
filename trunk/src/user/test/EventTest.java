/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package user.test;

import event.Event;
import event.OnTouchEvent;
import loader.Server;
import main.ActionArea;
import user.observer.EventObserver;

/**
 *
 * @author tomique
 */
public class EventTest implements EventObserver {

    private GUIServer guiServer;
    
    public EventTest() {
        init();
    }

    private void init() {
        guiServer = GUIServer.getInstance();
        Server server = new Server();
        server.getEventHandler().registerObserver(this);
    }

    @Override
    public void eventOccurred(Event event, ActionArea area) {
        if (event instanceof OnTouchEvent) {
            if (area == null) {
                System.out.println(((OnTouchEvent) event).getAction() + ", area NEBUDE!!");
            } else {
                switch(area.getId()) {
                    case "area1":
                        guiServer.setAction1(((OnTouchEvent) event).getAction());
                        guiServer.setEvent1("OnTouchEvent");
                        break;
                    case "area2":
                        guiServer.setAction2(((OnTouchEvent) event).getAction());
                        guiServer.setEvent2("OnTouchEvent");
                        break;
                    default:
                        throw new RuntimeException("Impossible state!");
                        
                }
//                System.out.println(((OnTouchEvent) event).getAction() + ", area " + area.getId());
            }
        }
    }

    public static void main(String[] args) {
        new EventTest();
    }
}
