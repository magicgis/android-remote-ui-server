/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.gui.entity;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.stream.Format;

/**
 *
 * @author tomas.buk
 */
public class LayoutTest {

    private static final Logger logger = Logger.getLogger(LayoutTest.class.getName());

    public LayoutTest() {
    }

//    @BeforeClass
//    public static void setUpClass() {
//    }
//    
//    @AfterClass
//    public static void tearDownClass() {
//    }
//    
//    @Before
//    public void setUp() {
//    }
//    
//    @After
//    public void tearDown() {
//    }
    /**
     * Test of getBackground method, of class Layout.
     */
    @Test
    public void testGetBackground() {
        System.out.println("getBackground");
        Layout instance = new Layout();
        instance.setBackground("#ff00ff");
        instance.setComponents(new ArrayList<Component>());
        DefaultRadioButton radio = new DefaultRadioButton();
//        radio.setPosX(20);
//        radio.setPosY(20);
//        radio.setBorder(1);
//        radio.setOuterDiameter(40);
//        radio.setInnerDiameter(10);
//        radio.setLabel("Radio button");
//        radio.set
        radio.setLabel("RadioButton");
        instance.getComponents().add(radio);

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

        // convert back to object
        Layout unmarshalled = null;

        xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<layout>\n"
                + "   <background>#ff00ff</background>\n"
                + "   <components class=\"java.util.ArrayList\">\n"
                + "      <component class=\"cz.ctu.guiproject.server.gui.entity.DefaultRadioButton\">\n"
                + "         <label>RadioButton</label>\n"
                + "      </component>\n"
                + "   </components>\n"
                + "</layout>";

        serializer = new Persister();
        try {
            unmarshalled = (Layout) serializer.read(Layout.class, xml);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage());
        }
        System.out.println("ok");
    }
//    /**
//     * Test of setBackground method, of class Layout.
//     */
//    @Test
//    public void testSetBackground() {
//        System.out.println("setBackground");
//        String background = "";
//        Layout instance = new Layout();
//        instance.setBackground(background);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getComponents method, of class Layout.
//     */
//    @Test
//    public void testGetComponents() {
//        System.out.println("getComponents");
//        Layout instance = new Layout();
//        List expResult = null;
//        List result = instance.getComponents();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setComponents method, of class Layout.
//     */
//    @Test
//    public void testSetComponents() {
//        System.out.println("setComponents");
//        List<Component> components = null;
//        Layout instance = new Layout();
//        instance.setComponents(components);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of toString method, of class Layout.
//     */
//    @Test
//    public void testToString() {
//        System.out.println("toString");
//        Layout instance = new Layout();
//        String expResult = "";
//        String result = instance.toString();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
}