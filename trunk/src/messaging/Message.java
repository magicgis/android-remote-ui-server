/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package messaging;

/**
 *
 * @author tomique
 */
public abstract class Message {

    // TODO add sessionId info to ?all? messages (probably yes! Broadcast might be unused)
    
    // sessionId safely identifies client device
    protected String sessionId;
    // XML representation of the message
    protected String xmlRep;
    // flag, that indicates change of attribute value
    protected boolean altered = true;

    /**
     * Returns XML representation of the given object.
     *
     * @return XML representation of the given object
     */
    public String getXML() {
        if (altered) {
            setXML();
        }
        return xmlRep;
    }

    /**
     * Converts object into corresponding XML representation.
     */
    public abstract void setXML();

    /**
     * Converts given XML file into object.
     *
     * @param xml XML file to be converted into object
     */
    public abstract void decodeXML(String xml);

    /**
     * Returns sessionId connected with appropriate client device.
     *
     * @return sessionId connected with appropriate client device
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * Sets sessionId, that uniquely identifies particular client device.
     *
     * @param sessionId String, that uniquely identifies particular client
     * device
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
