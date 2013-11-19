/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.gui.loader;

import cz.ctu.guiproject.server.gui.entity.Layout;
import cz.ctu.guiproject.server.gui.entity.DefaultRadioButton;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.stream.Format;

/**
 *
 * @author tomas.buk
 */
public class LoaderTest {
    
    private static final Logger logger = Logger.getLogger(LoaderTest.class.getName());

    public LoaderTest() {
    }

    /**
     * Test of loadComponent method, of class Loader.
     */
    @Test
    public void testLoadLayout() {
        System.out.println("loadLayout");
        Layout result = Loader.loadLayout();
        
        System.out.println(result.getBackground());
        
    }

    /**
     * Test of loadDefaultRadioButton method, of class Loader.
     */
    @Test
    public void testLoadDefaultRadioButton() {
        System.out.println("loadDefaultRadioButton");
        DefaultRadioButton instance = new DefaultRadioButton();
        instance.setBorder(4);
        instance.setOuterDiameter(70);
        instance.setInnerDiameter(50);
        instance.setLabel("Radio button");
        instance.setPosX(20);
        instance.setPosY(20);
        instance.setBorderColor("#000000");
        instance.setOuterColor("#ffffff");
        instance.setInnerColor("#ff0000");
        instance.setLabelColor("#ffff00");
        instance.setLabelSize(30);
//        RadioButton result = Loader.loadDefaultRadioButton();
        
        
        StringWriter sw = new StringWriter();

        Serializer serializer = new Persister(
                new Format("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"));
        try {
            serializer.write(instance, sw);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage());
            throw new RuntimeException("Unable to marshall object into XML String!");
        }

        String xml = sw.toString();
        System.out.println(xml);
        
        
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
//
//    /**
//     * Test of load method, of class Loader.
//     */
//    @Test
//    public void testLoad() {
//        System.out.println("load");
//        String path = "";
//        Object expResult = null;
//        Object result = Loader.load(path);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
}