/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.events;

import org.simpleframework.xml.Root;

/**
 *
 * @author tomas.buk
 */
@Root
public class ClickEvent extends AndroidEvent<ClickEvent> {

    /**
     * Default constructor
     */
    public ClickEvent() {
    }

    @Override
    public String getXml() {
        return super.getXml(this);
    }
}
