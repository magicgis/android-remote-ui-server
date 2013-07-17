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

    public EventTest() {
        init();
    }

    private void init() {
        Server server = new Server();
        server.getEventHandler().registerObserver(this);
    }

    @Override
    public void eventOccurred(Event event, ActionArea area) {
        if (event instanceof OnTouchEvent) {
            if (area == null) {
                System.out.println(((OnTouchEvent) event).getAction() + ", area NEBUDE!!");
            } else {
                System.out.println(((OnTouchEvent) event).getAction() + ", area " + area.getId());
            }
        }
    }

    public static void main(String[] args) {
        new EventTest();
    }
}
