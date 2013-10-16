/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.xml;

import messaging.Message;

/**
 *
 * @author tomas.buk
 */
public interface ServerXMLObserver {
    
    public void update(Message message);
}
