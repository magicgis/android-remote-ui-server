/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.events;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tomas.buk
 */
public class DragEventTest {
    
    private static final Logger logger = Logger.getLogger(DragEvent.class.getName());
    
    public DragEventTest() {
    }
    
    @Test
    public void testSomeMethod() {
        int[] arr = {1, 2, 3, 4, 5, 10};
        DragEvent event = new DragEvent();
        event.setPoint(arr);

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(DragEvent.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(event, System.out);

        } catch (JAXBException ex) {
            logger.log(Level.SEVERE, ex.getMessage());
            fail("Failed to convert to XML.");
        }
    }
}