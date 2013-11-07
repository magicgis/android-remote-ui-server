/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.events;

import org.simpleframework.xml.Root;

/**
 * Occurs, when the user touches, drags and releases. Resulting point list is
 * intended to be an array of even length(every odd element is x coordinate and
 * every even element is y coordinate).
 *
 * @author tomas.buk
 */
@Root
public class DragEvent extends AndroidEvent<DragEvent> {

    /**
     * Default constructor
     */
    public DragEvent() {
    }

    @Override
    public String getXml() {
        return super.getXml(this);
    }
}
