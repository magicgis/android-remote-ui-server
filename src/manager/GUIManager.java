/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import bitmap.Codec;
import java.awt.image.BufferedImage;
import main.Component;
import parser.ComponentParser;
import renderer.SimpleRenderer;

/**
 *
 * @author tomique
 */
public class GUIManager {

    private static GUIManager instance;
    private Component component;
    private BufferedImage renderedComponent;
    private ComponentParser componentParser;

    private GUIManager() {
        // setup available GUI components
        this.componentParser = ComponentParser.getInstance();
        this.component = componentParser.getComponent();
        // TODO zajistit, aby byl vzdy predavan aktualni objekt renderedComponent
        renderedComponent = SimpleRenderer.getRenderedComponent(component);
    }

    public static GUIManager getInstance() {
        if (instance == null) {
            instance = new GUIManager();
        }
        return instance;
    }
    
    public BufferedImage getImage() {
        return renderedComponent;
    }

    public String getEncodedImage() {
        String codedImage = Codec.encodeToString(renderedComponent, "png");
        return codedImage;
    }
}
