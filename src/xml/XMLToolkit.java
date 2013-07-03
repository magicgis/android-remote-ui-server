/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xml;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import messaging.InitCMessage;
import messaging.Message;

/**
 *
 * @author tomique
 */
public class XMLToolkit {
    
    public static Message decodeXML(String xml) {
        
        Message message = null;

        try {

            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            byte[] byteArray = xml.getBytes("UTF-8");
            ByteArrayInputStream in = new ByteArrayInputStream(byteArray);
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                // start tag
                if (event.isStartElement()) {

                    StartElement startElement = event.asStartElement();

                    switch (startElement.getName().getLocalPart()) {
                        case "cln": {

                            Iterator<Attribute> attributes = startElement.getAttributes();
                            while (attributes.hasNext()) {

                                Attribute attribute = attributes.next();

                                switch (attribute.getName().toString()) {
                                    case "state": {
                                        if (attribute.getValue().equals("connection_init")) {
                                            message = new InitCMessage();
                                            message.decodeXML(xml);
                                        }
                                        break;
                                    }
                                }
                            }
                            break;
                        }
                    }
                }
            }

        } catch (IOException | XMLStreamException ex) {
            Logger.getLogger(XMLHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return message;
    }
}
