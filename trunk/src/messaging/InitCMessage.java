/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package messaging;

import android_toolkit.AndroidDevice;
import entity.Resolution;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Iterator;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 *
 * @author tomique
 */
public class InitCMessage extends Message {

    /*
     * filled by client
     */
    private AndroidDevice device;

    /**
     * Default constructor
     */
    public InitCMessage() {
    }

    /*
     * SETTERS
     */
    /**
     *
     * @param device
     */
    public void setDevice(AndroidDevice device) {
        this.device = device;
    }

    /*
     * GETTERS
     */
    /**
     *
     * @return
     */
    public AndroidDevice getDevice() {
        return device;
    }

    /**
     * Converts message to XML message with given attribute values and sets its
     * output as String "xmlRep" from parent class.
     */
    @Override
    public void setXML() {
        throw new RuntimeException("There is no need to setXML at server side!");
    }
    
    @Override
    public void decodeXML(String xml) {
        
        try {

            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            byte[] byteArray = xml.getBytes("UTF-8");
            ByteArrayInputStream in = new ByteArrayInputStream(byteArray);
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
            Resolution resolution = null;

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                // start tag
                if (event.isStartElement()) {

                    StartElement startElement = event.asStartElement();

                    switch (startElement.getName().getLocalPart()) {

                        case "device":
                            device = new AndroidDevice();

                            Iterator<Attribute> attributes = startElement.getAttributes();
                            while (attributes.hasNext()) {
                                Attribute attribute = attributes.next();

                                switch (attribute.getName().toString()) {
                                    case "name":
                                        if (this != null) {
                                            device.setModel(attribute.getValue());
                                            setDevice(device);
                                        } else {
                                            throw new RuntimeException("Invalid format of XML init file - message has not been initialized yet!");
                                        }
                                        break;
                                }
                            }


                            break;
                        case "resolution":
                            resolution = new Resolution();
                            break;
                        case "width":
                            event = eventReader.nextEvent();
                            resolution.setWidth(Integer.parseInt(event.asCharacters().getData()));
                            break;
                        case "height":
                            event = eventReader.nextEvent();
                            resolution.setHeight(Integer.parseInt(event.asCharacters().getData()));
                            break;
                    }
                }
                // close tag
                if (event.isEndElement()) {

                    EndElement endElement = event.asEndElement();

                    switch (endElement.getName().getLocalPart()) {
                        case "cln":
                            break;
                        case "device":
                            break;
                        case "resolution":
                            device.setResolution(resolution);
                            break;
                        case "width":
                            break;
                        case "height":
                            break;
                    }
                }
            }

        } catch (IOException | XMLStreamException ex) {
            ex.printStackTrace();
        }
    } 
}
