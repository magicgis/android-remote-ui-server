/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package handler;

import event.Event;
import java.util.ArrayList;
import main.ActionArea;
import user.observer.EventObserver;

/**
 *
 * @author tomique
 */
public class EventHandler {
    
    private static EventHandler instance;
    private ArrayList<EventObserver> observers;
    
    private EventHandler() {
        observers = new ArrayList<>();
    }
    
    public static EventHandler getInstance() {
        if(instance == null) {
            instance = new EventHandler();
        }
        return instance;
    }
    
    public void registerObserver(EventObserver o) {
        if(observers.contains(o)) {
            throw new RuntimeException("Observer " + o + " is already contained in the observer list.");
        }
        observers.add(o);
    }
    
    public void removeObserver(EventObserver o) {
        if(!observers.remove(o)) {
            throw new RuntimeException("Observer " + o + " is not contained in the observer list!");
        }
    }
    
    public void notifyObservers(Event event, ActionArea eventArea) {
        for(EventObserver observer : observers) {
            observer.eventOccurred(event, eventArea);
        }
    }
}
