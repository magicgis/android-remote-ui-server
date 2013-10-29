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
public class LongClickEvent extends AndroidEvent<LongClickEvent> {

    /**
     * Default constructor
     *
     */
    public LongClickEvent() {
    }

    @Override
    public String getXml() {
        return super.getXml(this);
    }

    @Override
    public LongClickEvent getEventInstance(String xml) {
        return super.getEventInstance(xml, this);
    }
}
