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
public class ClickEventTest {

    public ClickEventTest() {
    }

    /**
     * Test of getXml method, of class ClickEvent.
     */
    @Test
    public void testGetXml() {
        System.out.println("getXml");
        ClickEvent expResult = new ClickEvent();
        int[] points = {1, 2, 3, 4, 57, 8, 9, 0};
        expResult.setPoint(points);
        String xml = expResult.getXml();
        ClickEvent result = new ClickEvent();
        result = result.getEventInstance(xml);
        assertEquals(expResult, result);
    }

    /**
     * Test of getEventInstance method, of class ClickEvent.
     */
    @Test
    public void testGetEventInstance() {
        System.out.println("getEventInstance");
        ClickEvent expResult = new ClickEvent();
        int[] points = {1, 2, 3, 45, 0};
        expResult.setPoint(points);
        String xml = expResult.getXml();

        ClickEvent result = expResult.getEventInstance(xml);
        assertEquals(expResult, result);
    }
}