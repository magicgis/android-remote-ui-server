/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import java.awt.image.BufferedImage;
import main.Component;

/**
 *
 * @author tomique
 */
public class GUIManager {

    private static GUIManager instance;
    private Component component;
    private BufferedImage renderedComponent;

    private GUIManager() {
        // setup available GUI components
    }

    public static GUIManager getInstance() {
        if (instance == null) {
            instance = new GUIManager();
        }
        return instance;
    }
    
    public BufferedImage getImage() {
        
        // read xml config file
        // based on xml, create component
        // render component into an image
        // encode image into String
        // return string
        
        
        return null;
    }
}
