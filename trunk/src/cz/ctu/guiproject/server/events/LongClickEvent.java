/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.events;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Occurs, when user touches, pauses and then releases. The whole event must be
 * at least 1 second in length.
 *
 * @author tomas.buk
 */
@XmlRootElement
public class LongClickEvent extends AndroidEvent {

    /**
     * Default constructor
     *
     */
    public LongClickEvent() {
    }
}
