/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package messaging;

import java.awt.image.BufferedImage;
import networking.Session;

/**
 *
 * @author tomique
 */
public class GUIInitSMessage extends Message {
    
    private BufferedImage image;
    private Session session;

    @Override
    public void setXML() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void decodeXML(String xml) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
