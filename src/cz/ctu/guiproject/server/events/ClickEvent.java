/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.events;

import java.util.logging.Logger;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tomas.buk
 */
@XmlRootElement
public class ClickEvent extends AndroidEvent<ClickEvent> {

    private static final Logger logger = Logger.getLogger(ClickEvent.class.getName());

    /**
     * Default constructor
     */
    public ClickEvent() {
    }

    @Override
    public String getXml() {
        return super.getXml(this);
    }

    @Override
    public ClickEvent getEventInstance(String xml) {
        return super.getEventInstance(xml, this);
    }
}