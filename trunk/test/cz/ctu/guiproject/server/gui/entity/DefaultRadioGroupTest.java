/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.gui.entity;

import java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.*;

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
        group.getRadios().add(radio1);
        DefaultRadioButton radio2 = new DefaultRadioButton();
        radio2.setPosX(30);
        radio2.setPosY(90);
        radio2.setOuterDiameter(5);
        group.getRadios().add(radio2);
        System.out.println(Arrays.toString(group.getActionArea()));
        
        
//        DefaultRadioGroup instance = new DefaultRadioGroup();
//        int[] expResult = null;
//        int[] result = instance.getActionArea();
//        assertArrayEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
}