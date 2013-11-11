/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.helper;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tomas.buk
 */
public class IdParserTest {

    public IdParserTest() {
    }

    /**
     * Test of getNetworkId method, of class NetworkIdParser.
     */
    @Test
    public void testGetNetworkId() {
        System.out.println("getNetworkId");
        String xml = "[999]adsjfvbajhdsfadshkfjsdfh khhasfkjdhadfs";
        int result = IdParser.getNetworkId(xml);
        int expResult = 999;
        assertEquals(expResult, result);
    }

    /**
     * Test of getSessionId method, of class NetworkIdParser.
     */
    @Test
    public void testGetSessionId() {
        System.out.println("getSessionId");
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                + "<clickEvent>\n"
                + "    <sessionId>BAndroidDeviceSHA9990001B</sessionId>\n"
                + "    <points>\n"
                + "        <coord>1</coord>\n"
                + "        <coord>2</coord>\n"
                + "        <coord>3</coord>\n"
                + "        <coord>4</coord>\n"
                + "        <coord>57</coord>\n"
                + "        <coord>8</coord>\n"
                + "        <coord>9</coord>\n"
                + "        <coord>0</coord>\n"
                + "    </points>\n"
                + "</clickEvent>";
        String result = IdParser.getSessionId(xml);
        String expResult = "BAndroidDeviceSHA9990001B";
        assertEquals(expResult, result);
    }
}