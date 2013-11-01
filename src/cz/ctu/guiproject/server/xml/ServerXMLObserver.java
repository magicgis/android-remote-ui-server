/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.xml;

import cz.ctu.guiproject.server.messaging.AndroidMessage;

/**
 *
 * @author tomas.buk
 */
public interface ServerXMLObserver {
    
    public void update(AndroidMessage message);
}
