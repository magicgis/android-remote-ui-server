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
public class LongClickEventTest {

    public LongClickEventTest() {
    }

    /**
     * Test of getXml method, of class LongClickEvent.
     */
    @Test
    public void testGetXml() {
        System.out.println("getXml");
        LongClickEvent expResult = new LongClickEvent();
        int[] points = {1, 2, 3, 4, 9, 0};
        expResult.setPoint(points);
        String xml = expResult.getXml();
        LongClickEvent result = new LongClickEvent();
        result = result.getEventInstance(xml);
        assertEquals(expResult, result);
    }

    /**
     * Test of getEventInstance method, of class LongClickEvent.
     */
    @Test
    public void testGetEventInstance() {
        System.out.println("getEventInstance");
        LongClickEvent expResult = new LongClickEvent();
        int[] points = {1, 2, 3, 4, 88};
        expResult.setPoint(points);
        String xml = expResult.getXml();
        LongClickEvent result = new LongClickEvent();
        result = result.getEventInstance(xml);
        assertEquals(expResult, result);
    }
}