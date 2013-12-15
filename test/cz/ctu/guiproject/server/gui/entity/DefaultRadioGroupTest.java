/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.gui.entity;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Test;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.stream.Format;

/**
 *
 * @author tomas.buk
 */
public class DefaultRadioGroupTest {

    public DefaultRadioGroupTest() {
    }

    /**
     * Test of getActionArea method, of class DefaultRadioGroup.
     */
    @Test
    public void testGetActionArea() {
        System.out.println("getActionArea");
        
        DefaultRadioGroup group = new DefaultRadioGroup();
        DefaultRadioButton radio1 = new DefaultRadioButton();
        radio1.setPosX(10);
        radio1.setPosY(10);
        radio1.setOuterDiameter(10);
        radio1.setName("radio_1");
        radio1.setRenderable(true);
        radio1.setLabel("karel");
        group.setRadios(new ArrayList<DefaultRadioButton>());
//        group.getRadios().add(radio1);
        DefaultRadioButton radio2 = new DefaultRadioButton();
        radio2.setPosX(30);
        radio2.setPosY(90);
        radio2.setOuterDiameter(5);
        radio2.setName("radio_2");
        radio2.setRenderable(true);
        radio2.setLabel("larva");
//        group.getRadios().add(radio2);
//        System.out.println(Arrays.toString(group.getActionArea()));
        
        group.setName("group_01");
        group.setPosX(0);
        group.setPosY(0);
        group.setRenderable(false);
        
        // convert to xml
        StringWriter sw = new StringWriter();

        Serializer serializer = new Persister(
                new Format("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"));
        try {
            serializer.write(group, sw);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        String xml = sw.toString();

        System.out.println(xml);
        
        
//        DefaultRadioGroup instance = new DefaultRadioGroup();
//        int[] expResult = null;
//        int[] result = instance.getActionArea();
//        assertArrayEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
}