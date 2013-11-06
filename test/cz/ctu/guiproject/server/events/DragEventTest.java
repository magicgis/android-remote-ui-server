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
        expResult.setSessionId("AndroidDeviceAABBB00001");
        String xml = expResult.getXml();
        System.out.println(xml);
        DragEvent result = (DragEvent) AndroidMessageFactory.createAndroidMessage(xml);
        assertEquals(expResult, result);
    }
}