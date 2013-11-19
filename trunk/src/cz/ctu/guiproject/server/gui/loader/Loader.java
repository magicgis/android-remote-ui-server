/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.gui.loader;

import cz.ctu.guiproject.server.gui.entity.Layout;
import cz.ctu.guiproject.server.gui.entity.DefaultRadioButton;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

/**
 *
 * @author tomas.buk
 */
public class Loader {

    private static final Logger logger = Logger.getLogger(Loader.class.getName());
    private static final String CONFIG_PATH = "src/config/layout.xml";
    private static final String DEFAULT_RADIO_PATH = "src/config/defaultRadioButton.xml";

    public static Layout loadLayout() {

        // load from disc
        String xml = null;
        try {

            BufferedReader br = new BufferedReader(new FileReader(CONFIG_PATH));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            xml = sb.toString();

        } catch (FileNotFoundException ex) {
            logger.log(Level.SEVERE, ex.getMessage());
        } catch (IOException ex) {
            logger.log(Level.SEVERE, ex.getMessage());
        }

        // now its time to unmarshall it
        Layout layout = null;
        Serializer serializer = new Persister();
        try {
            layout = serializer.read(Layout.class, xml);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage());
        }
        return layout;
    }

    public static DefaultRadioButton loadDefaultRadioButton() {

        // load from disc
        String xml = null;
        try {

            BufferedReader br = new BufferedReader(new FileReader(DEFAULT_RADIO_PATH));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            xml = sb.toString();

        } catch (FileNotFoundException ex) {
            logger.log(Level.SEVERE, ex.getMessage());
        } catch (IOException ex) {
            logger.log(Level.SEVERE, ex.getMessage());
        }

        // now its time to unmarshall it
        DefaultRadioButton layout = null;
        Serializer serializer = new Persister();
        try {
            layout = serializer.read(DefaultRadioButton.class, xml);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage());
        }
        return layout;
    }
}
