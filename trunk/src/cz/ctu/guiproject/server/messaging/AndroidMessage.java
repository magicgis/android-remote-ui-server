/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.messaging;

import java.io.StringWriter;
import org.simpleframework.xml.stream.Format;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

/**
 *
 * @author tomas.buk
 */
public abstract class AndroidMessage<T> {

    private static final Logger logger = Logger.getLogger(AndroidMessage.class.getName());
    // Uniquely identifies client session
    @Element
    private String sessionId;

//    public abstract void setMessageType();
    /**
     * Returns sessionId of particular server-client connection
     *
     * @return sessionId of particular server-client connection
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * Sets the sessionId of particular server-client connection
     *
     * @param sessionId of particular server-client connection to be set
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    /**
     * Current instance is converted into its corresponding XML representation.
     *
     * @return XML representation of current instance
     */
    public abstract String getXml();

    /**
     * Called from direct descendants only. Current instance is converted into
     * its corresponding XML representation.
     *
     * @param instance Instance of marshaled class
     * @return Xml representation of current instance
     */
    public final String getXml(T instance) {

        StringWriter sw = new StringWriter();

        Serializer serializer = new Persister(
                new Format("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"));
        try {
            serializer.write(instance, sw);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage());
            throw new RuntimeException("Unable to marshall object into XML String!");
        }

        return sw.toString();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.sessionId);
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
        final AndroidMessage<T> other = (AndroidMessage<T>) obj;
        if (!Objects.equals(this.sessionId, other.sessionId)) {
            return false;
        }
        return true;
    }
}