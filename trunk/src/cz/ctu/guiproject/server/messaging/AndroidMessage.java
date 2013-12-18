/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.messaging;

import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.stream.Format;

/**
 * @author tomas.buk
 */
public abstract class AndroidMessage<T> {

    private static final Logger logger = Logger.getLogger(AndroidMessage.class
            .getName());
    // Uniquely identifies client session
    @Element
    private String sessionId;
    @Element(required = false)
    private String messageId;

    /**
     * Returns unique message id
     *
     * @return
     */
    public String getMessageId() {
        return messageId;
    }

    /**
     * Sets the unique message id
     *
     * @param messageId
     */
    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

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

        Serializer serializer = new Persister(new Format(
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"));

        try {
            serializer.write(instance, sw);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage());
            throw new RuntimeException(
                    "Unable to marshall object into XML String!");
        }

        return sw.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((sessionId == null) ? 0 : sessionId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        AndroidMessage<?> other = (AndroidMessage<?>) obj;
        if (sessionId == null) {
            if (other.sessionId != null) {
                return false;
            }
        } else if (!sessionId.equals(other.sessionId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(this.getClass().getName());
        sb.append("\n-----------------\n");
        sb.append("sesionId: ").append(sessionId);

        return sb.toString();
    }
}