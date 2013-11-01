/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.messaging;

import cz.ctu.guiproject.server.events.ClickEvent;
import cz.ctu.guiproject.server.events.DragEvent;
import cz.ctu.guiproject.server.events.LongClickEvent;
import cz.ctu.guiproject.server.events.TouchEvent;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author tomas.buk
 */
public class AndroidMessageFactory {

    private static final Logger logger = Logger.getLogger(AndroidMessageFactory.class.getName());

    /**
     * Returns the instance of requested class (based on input xml file).
     *
     * @param xml
     * @return
     */
    public static AndroidMessage createAndroidMessage(String xml) {
        // based on xml root tag decide the target class
        Class target = getTarget(xml);

        AndroidMessage eventInstance = null;

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(target);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            StringReader reader = new StringReader(xml);
            eventInstance = (AndroidMessage) jaxbUnmarshaller.unmarshal(reader);

        } catch (JAXBException ex) {
            logger.log(Level.SEVERE, ex.getMessage());
            throw new RuntimeException("Unable to create instance of AndroidMessage in AndroidMessageFactory!");
        }

        return eventInstance;
    }

    /**
     * Based on input xml it returns appropriate class
     *
     * @param xml input xml file
     * @return class
     */
    private static Class getTarget(String xml) {

        // locate the root tag name
        int i1 = xml.indexOf(">", 1);
        xml = xml.substring(i1 + 1, xml.length());
        int i0 = xml.indexOf("<", 0);
        i1 = xml.indexOf(">", 1);
        xml = xml.substring(i0 + 1, i1);
        xml = xml.trim();

        // based on root tag decide target class
        switch (xml) {
            case "clickEvent":
                return ClickEvent.class;
            case "dragEvent":
                return DragEvent.class;
            case "longClickEvent":
                return LongClickEvent.class;
            case "touchEvent":
                return TouchEvent.class;
            default:
                throw new RuntimeException("Unsupported target class, new case scenario must be added!");
        }
    }
}
