/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.messaging;

import org.simpleframework.xml.Root;

/**
 *
 * @author tomas.buk
 */
@Root
public class ClientInitResponseMessage extends AndroidMessage<ClientInitResponseMessage> {
    
    private String context;

    
    
    @Override
    public String getXml() {
        return super.getXml(this);
    }
}
