/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package messaging;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import networking.Session;

/**
 * Send from server side, used to confirm the connection request from client.
 *
 * @author tomique
 *
 */
public class InitSMessage extends Message {

    private Session session;
    private String serverAnswer;

    public InitSMessage() {
    }

    /*
     * SETTERS
     */
    /**
     * Sets the current session
     *
     * @param session Session, that need to be set
     */
    public void setSession(Session session) {
        this.session = session;
        altered = true;
    }

    /**
     * Sets the code answer of the server (e.g. 200 - OK)
     *
     * @param serverAnswer Code answer of the server
     */
    public void setServerAnswer(String serverAnswer) {
        this.serverAnswer = serverAnswer;
        altered = true;
    }

    /*
     * GETTERS
     */
    /**
     * Returns the session with the current client
     *
     * @return The session with the current client
     */
    public Session getSession() {
        return session;
    }

    /**
     * Returns the coded server answer
     *
     * @return The coded server answer
     */
    public String getServerAnswer() {
        return serverAnswer;
    }

    @Override
    public void setXML() {

        if (!altered) {
            return;
        }

        String xml = null;
        XMLOutputFactory factory = XMLOutputFactory.newInstance();

        try {

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            XMLStreamWriter writer = factory.createXMLStreamWriter(baos);

            writer.writeStartDocument();
            writer.writeDTD("\n");
            writer.writeStartElement("svr");
            writer.writeAttribute("state", "connection_init");
            writer.writeStartElement("response");
            writer.writeAttribute("state", serverAnswer);
            if (session != null) {
                writer.writeStartElement("session");
                writer.writeAttribute("id", session.getId());

                writer.writeEndElement();
            }
            writer.writeEndElement();
            writer.writeEndElement();
            writer.writeEndDocument();

            writer.flush();
            writer.close();

            xml = baos.toString(System.getProperty("file.encoding"));

        } catch (XMLStreamException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        xmlRep = xml;
        altered = false;
    }

    @Override
    public void decodeXML(String xml) {
        throw new RuntimeException("There is no need to use decodeXML at server side!");
    }
}
