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

    // XML representation of the message
    protected String xmlRep;
    // flag, that indicates change of attribute value
    protected boolean altered = true;

    public String getXML() {
        if (altered) {
            setXML();
        }
        return xmlRep;
    }

    public abstract void setXML();
    
    public abstract void decodeXML(String xml);
}
