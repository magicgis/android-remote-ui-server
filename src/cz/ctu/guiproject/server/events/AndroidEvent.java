/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.events;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

/**
 *
 * @author tomas.buk
 */
public abstract class AndroidEvent<T> {

    private static final Logger logger = Logger.getLogger(AndroidEvent.class.getName());
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

    public abstract String getXml();

//    public abstract <T extends AndroidEvent> getEvent(String xml);
    public abstract T getEventInstance(String xml);

    String getXml(T instance) {

        StringWriter sw = new StringWriter();

        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(instance.getClass());
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(instance, sw);

        } catch (JAXBException ex) {
            logger.log(Level.SEVERE, ex.getMessage());
            return null;
        }

        return sw.toString();
    }

    T getEventInstance(String xml, T instance) {

        T eventInstance = null;

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(instance.getClass());
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            StringReader reader = new StringReader(xml);
            eventInstance = (T) jaxbUnmarshaller.unmarshal(reader);

        } catch (JAXBException ex) {
            logger.log(Level.SEVERE, ex.getMessage());
            return eventInstance;
        }

        return eventInstance;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Arrays.hashCode(this.point);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AndroidEvent<T> other = (AndroidEvent<T>) obj;
        if (!Arrays.equals(this.point, other.point)) {
            return false;
        }
        return true;
    }
}