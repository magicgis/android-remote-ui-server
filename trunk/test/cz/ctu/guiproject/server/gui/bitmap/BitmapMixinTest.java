/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.gui.bitmap;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tomas.buk
 */
public class BitmapMixinTest {
    
    public BitmapMixinTest() {
    }
    
    /**
     * Test of toByteArray method, of class BitmapMixin.
     */
    @Test
    public void testToByteArray() {
        System.out.println("toByteArray");
        
        BufferedImage image = new BufferedImage(640, 480, BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D g2d = image.createGraphics();

        g2d.setPaint(new Color(255, 0, 0));
        g2d.fillRect(0, 0, image.getWidth(), image.getHeight());
        String format = "png";
        byte[] result = BitmapMixin.toByteArray(image, format);
        
        byte[] expResult = BitmapMixin.toByteArray(image, format);
        
        assertArrayEquals(expResult, result);
    }

//    /**
//     * Test of toBufferedImage method, of class BitmapMixin.
//     */
//    @Test
//    public void testToBufferedImage() {
//        System.out.println("toBufferedImage");
//        byte[] buffer = null;
//        BufferedImage expResult = null;
//        BufferedImage result = BitmapMixin.toBufferedImage(buffer);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
}