/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.events;

import cz.ctu.guiproject.server.messaging.AndroidMessage;
import org.simpleframework.xml.Element;

/**
 *
 * @author tomas.buk
 */
public abstract class AndroidEvent<T> extends AndroidMessage<T> {

    // x and y coordinates of particular event
    // TODO always even length, if touch happenes, longer array than size 2 is returned
    @Element
    private int[] point;

    /**
     * Returns the x and y coordinates of particular event
     *
     * @return the x and y coordinates of particular event
     */
    public int[] getPoint() {
        return point;
    }

    /**
     * Sets the x and y coordinates of particular event
     *
     * @param point x and y coordinates of particular event
     */
    public void setPoint(int[] point) {
        this.point = point;
    }
    
    // TODO override equals and hashcode??
}