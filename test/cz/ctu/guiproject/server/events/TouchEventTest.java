/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.events;

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
        String xml = expResult.getXml();
        TouchEvent result = new TouchEvent();
        result = result.getEventInstance(xml);
        assertEquals(expResult, result);
    }

    /**
     * Test of getEventInstance method, of class TouchEvent.
     */
    @Test
    public void testGetEventInstance() {
        System.out.println("getEventInstance");
        TouchEvent expResult = new TouchEvent();
        int[] points = {1, 2, 3, 4};
        expResult.setPoint(points);
        expResult.setMask("USER_DOWN");
        String xml = expResult.getXml();
        TouchEvent result = expResult.getEventInstance(xml);
        assertEquals(expResult, result);
    }
}