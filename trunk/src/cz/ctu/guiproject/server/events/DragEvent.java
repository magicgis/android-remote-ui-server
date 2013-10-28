/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.events;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Occurs, when the user touches, drags and releases. Resulting point list is
 * intended to be an array of even length(every odd element is x coordinate and
 * every even element is y coordinate).
 *
 * @author tomas.buk
 */
@XmlRootElement
public class DragEvent extends AndroidEvent {

    /**
     * Default constructor
     */
    public DragEvent() {
    }
}
