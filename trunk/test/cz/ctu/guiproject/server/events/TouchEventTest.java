/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.events;

import cz.ctu.guiproject.server.messaging.AndroidMessageFactory;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tomas.buk
 */
public class TouchEventTest {

    public TouchEventTest() {
    }

    /**
     * Test of getMask method, of class TouchEvent.
     */
    @Test
    public void testGetMask() {
    }

    /**
     * Test of setMask method, of class TouchEvent.
     */
    @Test
    public void testSetMask() {
    }

    /**
     * Test of getXml method, of class TouchEvent.
     */
    @Test
    public void testGetXml() {
        System.out.println("getXml");
        TouchEvent expResult = new TouchEvent();
        int[] points = {1, 2, 3, 4, 5, 1};
        expResult.setPoint(points);
        expResult.setMask("USER_UP");
        expResult.setSessionId("DeviceAndroid999000AAA");
        String xml = expResult.getXml();
        System.out.println(xml);
        TouchEvent result = (TouchEvent) AndroidMessageFactory.createAndroidMessage(xml);
        assertEquals(expResult, result);
    }
}