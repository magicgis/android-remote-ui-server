/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.gui.bitmap;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tomas.buk
 */
public class CodecTest {
    
    public CodecTest() {
    }
    
    /**
     * Test of encodeToBase64 method, of class Codec.
     */
    @Test
    public void testEncodeToBase64() {
        System.out.println("encodeToBase64");
        
        BufferedImage expResult = new BufferedImage(640, 480, BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D g2d = expResult.createGraphics();

        g2d.setPaint(new Color(255, 0, 0));
        g2d.fillRect(0, 0, expResult.getWidth(), expResult.getHeight());
        
        String base64 = Codec.encodeToBase64(expResult, "png");
        
        BufferedImage result = Codec.decodeBase64(base64);
        
        System.out.println(base64);
        
        assertEquals(expResult, result);
    }

//    /**
//     * Test of decodeBase64 method, of class Codec.
//     */
//    @Test
//    public void testDecodeBase64() {
//        System.out.println("decodeBase64");
//        String imageString = "";
//        BufferedImage expResult = null;
//        BufferedImage result = Codec.decodeBase64(imageString);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
}