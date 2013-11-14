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
public class ClientInitResponseMessageTest {

    public ClientInitResponseMessageTest() {
    }

    /**
     * Test of getXml method, of class ClientInitResponseMessage.
     */
    @Test
    public void testGetXml() {
        System.out.println("getXml");
        ClientInitResponseMessage expResult = new ClientInitResponseMessage();
        String context = "pepa";
        expResult.setContext(context);
        expResult.setSessionId("Android1");
        expResult.setFormat("png");

        String xml = expResult.getXml();
        System.out.println(xml);
        ClientInitResponseMessage result = (ClientInitResponseMessage) AndroidMessageFactory.createAndroidMessage(xml);
        assertEquals(expResult, result);
    }
}