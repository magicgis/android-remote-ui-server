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
        System.out.println(xml);
        LongClickEvent result = (LongClickEvent) AndroidMessageFactory.createAndroidMessage(xml);
        assertEquals(expResult, result);
    }
}