/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import com.sun.xml.internal.txw2.output.XmlSerializer;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 *
 * @author tomique
 */
public class SimpleXMLWriter {

    public SimpleXMLWriter() {
        writeSTAX();
    }

    private String writeSTAX() {

        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        String output = null;

        try {

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            XMLStreamWriter writer = factory.createXMLStreamWriter(baos);

            writer.writeStartDocument();
            writer.writeDTD("\n");
            writer.writeStartElement("document");
            writer.writeDTD("\n");
            writer.writeStartElement("data");
            writer.writeAttribute("name", "value");
            writer.writeDTD("\n");
            writer.writeEndElement();
            writer.writeDTD("\n");
            writer.writeEndElement();
            writer.writeEndDocument();

            writer.flush();
            writer.close();

            output = baos.toString(System.getProperty("file.encoding"));

        } catch (XMLStreamException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return output;
    }

//    private void createNode(XMLEventWriter eventWriter, String name, String value) {
//
//        XMLEventFactory eventFactory = XMLEventFactory.newInstance();
//        XMLEvent end = eventFactory.createDTD("\n");
//        XMLEvent tab = eventFactory.createDTD("\t");
//
//        try {
//            // create start node
//            StartElement sElement = eventFactory.createStartElement("", "", name);
//            eventWriter.add(tab);
//            eventWriter.add(sElement);
//            eventWriter.
//
//            // create content
//            Characters characters = eventFactory.createCharacters(value);
//            eventWriter.add(characters);
//
//            // create end node
//            EndElement eElement = eventFactory.createEndElement("", "", name);
//            eventWriter.add(eElement);
//            eventWriter.add(end);
//
//        } catch (XMLStreamException ex) {
//            Logger.getLogger(SimpleXMLWriter.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    public static void main(String[] args) {
        new SimpleXMLWriter();
    }
}
