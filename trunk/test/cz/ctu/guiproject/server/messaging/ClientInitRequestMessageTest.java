/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.messaging;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tomas.buk
 */
public class ClientInitRequestMessageTest {
    
    public ClientInitRequestMessageTest() {
    }
    
    /**
     * Test of getXml method, of class ClientInitRequestMessage.
     */
    @Test
    public void testGetXml() {
        System.out.println("getXml");
        ClientInitRequestMessage expResult = new ClientInitRequestMessage();
        expResult.setScreenWidth(800);
        expResult.setScreenHeight(600);
        expResult.setSessionId("AndroidRulez!!!");
        String xml = expResult.getXml();
        System.out.println(xml);
        ClientInitRequestMessage result = (ClientInitRequestMessage) AndroidMessageFactory.createAndroidMessage(xml);
        assertEquals(expResult, result);
    }
}