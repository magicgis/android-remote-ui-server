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
        System.out.println(xml);
        ClickEvent result = (ClickEvent) AndroidMessageFactory.createAndroidMessage(xml);
        assertEquals(expResult, result);
    }
}