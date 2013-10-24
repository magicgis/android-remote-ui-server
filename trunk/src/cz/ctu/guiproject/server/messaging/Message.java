/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.messaging;

/**
 *
 * @author tomas.buk
 */
public abstract class Message {

    // sessionId uniquely identifies client device
    private String sessionId;
    // XML representation of the message
    private String xmlRep;
    // flag, that indicates change of attribute value
    protected boolean altered = true;

    /**
     * Returns sessionId, that uniquely identifies connected client machine
     *
     * @return sessionId, that uniquely identifies connected client machine
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * Sets the sessionId, that uniquely identifies connected client machine
     *
     * @param sessionId sessionId uniquely identifies connected client machine
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    /**
     * Returns current XML representation of the message
     *
     * @return current XML representation of the message
     */
    public String getXML() {
        if (altered) {
            setXML();
        }
        return xmlRep;
    }

    /**
     * Converts message object into its corresponding XML representation
     */
    public abstract void setXML();

    /**
     * Converts given XML file into its corresponding message object
     *
     * @param xml Input XML file
     */
    public void decodeXML(String xml) {
        altered = true;
        // TODO every subclass must override this method, but at first it has to call this method to set the "altered" flag
    }
}
