/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.events;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Occurs, when user either touches the screen, or releases. Appropriate action
 * is generated.
 *
 * @author tomas.buk
 */
@XmlRootElement
public class TouchEvent extends AndroidEvent {

    // TODO predelat na enum??
    private String mask;

    /**
     * Default constructor
     *
     */
    public TouchEvent() {
    }

    // TODO implement particular masking action (ACTION_DOWN, ACTION_UP)
    public String getMask() {
        return mask;
    }

    public void setMask(String mask) {
        this.mask = mask;
    }
}
