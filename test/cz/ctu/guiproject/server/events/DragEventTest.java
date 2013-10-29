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
public class DragEventTest {

    public DragEventTest() {
    }

    /**
     * Test of getXml method, of class DragEvent.
     */
    @Test
    public void testGetXml() {
        System.out.println("getXml");
        DragEvent expResult = new DragEvent();
        int[] points = {1, 2, 3, 45, 5};
        expResult.setPoint(points);
        String xml = expResult.getXml();
        DragEvent result = new DragEvent();
        result = result.getEventInstance(xml);
        assertEquals(expResult, result);
    }

    /**
     * Test of getEventInstance method, of class DragEvent.
     */
    @Test
    public void testGetEventInstance() {
        System.out.println("getEventInstance");
        DragEvent expResult = new DragEvent();
        int[] points = {1, 2, 3, 4, 6};
        expResult.setPoint(points);
        String xml = expResult.getXml();
        DragEvent result = new DragEvent();
        result = result.getEventInstance(xml);
        assertEquals(expResult, result);
    }
}