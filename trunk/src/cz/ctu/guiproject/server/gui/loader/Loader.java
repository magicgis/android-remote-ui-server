/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.gui.loader;

import cz.ctu.guiproject.server.gui.entity.DefaultButton;
import cz.ctu.guiproject.server.gui.entity.DefaultToggleButton;
import cz.ctu.guiproject.server.gui.entity.DefaultComboBox;
import cz.ctu.guiproject.server.gui.entity.DefaultFader;
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
    private static final String DEFAULT_COMBO_PATH = "src/config/defaultComboBox.xml";
    private static final String DEFAULT_TOGGLE_PATH = "src/config/defaultToggleButton.xml";
    private static final String DEFAULT_BUTTON_PATH = "src/config/defaultButton.xml";
    private static final String DEFAULT_FADER_PATH = "src/config/defaultFader.xml";

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

    public static DefaultComboBox loadDefaultComboBox() {

        // load from disc
        String xml = null;
        try {

            BufferedReader br = new BufferedReader(new FileReader(DEFAULT_COMBO_PATH));
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
        DefaultComboBox combo = null;
        Serializer serializer = new Persister();
        try {
            combo = serializer.read(DefaultComboBox.class, xml);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage());
        }
        return combo;
    }

    public static DefaultToggleButton loadDefaultToggleButton() {
        // load from disc
        String xml = null;
        try {

            BufferedReader br = new BufferedReader(new FileReader(DEFAULT_TOGGLE_PATH));
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
        DefaultToggleButton button = null;
        Serializer serializer = new Persister();
        try {
            button = serializer.read(DefaultToggleButton.class, xml);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage());
        }
        return button;
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
        DefaultRadioButton radio = null;
        Serializer serializer = new Persister();
        try {
            radio = serializer.read(DefaultRadioButton.class, xml);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage());
        }
        return radio;
    }

    public static DefaultButton loadDefaultButton() {
        // load from disc
        String xml = null;
        try {

            BufferedReader br = new BufferedReader(new FileReader(DEFAULT_BUTTON_PATH));
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
        DefaultButton button = null;
        Serializer serializer = new Persister();
        try {
            button = serializer.read(DefaultButton.class, xml);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage());
        }
        return button;
    }

    public static DefaultFader loadDefaultFader() {
        // load from disc
        String xml = null;
        try {

            BufferedReader br = new BufferedReader(new FileReader(DEFAULT_FADER_PATH));
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
        DefaultFader fader = null;
        Serializer serializer = new Persister();
        try {
            fader = serializer.read(DefaultFader.class, xml);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage());
        }
        return fader;
    }
}