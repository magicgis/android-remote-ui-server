/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package messaging;

import bitmap.Codec;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import networking.Session;

/**
 *
 * @author tomique
 */
public class GUIInitSMessage extends Message {

    private BufferedImage renderedComponent;
    private String renderedComponentXml;
    private String renderedComponentFormat;
    private Session session;

    public GUIInitSMessage() {
    }

    public void setRenderedComponent(BufferedImage renderedComponent) {
        this.renderedComponent = renderedComponent;
        renderedComponentXml = Codec.encodeToBase64(renderedComponent, "png");
        altered = true;
    }

    public void setSession(Session session) {
        this.session = session;
        altered = true;
    }

    public void setRenderedComponentFormat(String renderedComponentFormat) {
        this.renderedComponentFormat = renderedComponentFormat;
    }

    public BufferedImage getRenderedComponent() {
        return renderedComponent;
    }

    public Session getSession() {
        return session;
    }

    public String getRenderedComponentFormat() {
        return renderedComponentFormat;
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
            writer.writeAttribute("state", "gui_init");
            writer.writeStartElement("session");
            writer.writeAttribute("id", session.getId());
            writer.writeEndElement();

            writer.writeStartElement("image");
            writer.writeAttribute("format", renderedComponentFormat);
            writer.writeCharacters(renderedComponentXml);

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
