/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.gui.entity;

import java.io.StringWriter;
import java.util.logging.Level;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.stream.Format;

/**
 *
 * @author tomas.buk
 */
public class DefaultButtonTest {

    public DefaultButtonTest() {
    }

    /**
     * Test of getActionArea method, of class DefaultButton.
     */
    @Test
    public void testDefaultButton() {

        DefaultToggleButton button = new DefaultToggleButton();
        button.setBorder(2);
        button.setBorderColor("#000000");
        button.setBorderColorPressed("#ff0000");
        button.setInnerColor("#555555");
        button.setInnerColorPressed("#aaaaaa");
        button.setLabel("button_1");
        button.setLabelColor("#000000");
        button.setLabelColorPressed("#ff0000");
        button.setName("button01");
        button.setOuterWidth(200);
        button.setOuterHeight(50);
        button.setPosX(0);
        button.setPosY(0);
        button.setPressed(false);

        StringWriter sw = new StringWriter();

        Serializer serializer = new Persister(
                new Format("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"));
        try {
            serializer.write(button, sw);
        } catch (Exception ex) {
            throw new RuntimeException("Unable to marshall object into XML String!");
        }
        
        String xml = sw.toString();

        System.out.println(xml);

//        String xml = sw.toString();
//        System.out.println(xml);
//        
//        System.out.println("getActionArea");
//        DefaultButton instance = new DefaultButton();
//        int[] expResult = null;
//        int[] result = instance.getActionArea();
//        assertArrayEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
}