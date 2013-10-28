/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.events;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

/**
 *
 * @author tomas.buk
 */
public abstract class AndroidEvent {

    // x and y coordinates of particular event
    // TODO always even length, if touch happenes, longer array than size 2 is returned
    private int[] point;

    /**
     * Returns the x and y coordinates of particular event
     *
     * @return the x and y coordinates of particular event
     */
    @XmlElement(name = "coord")
    @XmlElementWrapper(name = "points")
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
}
