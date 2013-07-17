/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_manager;

import entity.Resolution;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;
import main.ActionArea;
import main.Component;
import parser.ComponentParser;
import renderer.SimpleRenderer;

/**
 * Singleton
 *
 * @author tomique
 */
public class GUIManager {

    private static GUIManager instance;
    // TODO predelat na variantu, ktera povoluje vice komponent!
    private Component component;
    private BufferedImage renderedComponent;
    private ComponentParser componentParser;

    private GUIManager() {
        // setup available GUI components
        this.componentParser = ComponentParser.getInstance();
        this.component = componentParser.getComponent();
//        // TODO zajistit, aby byl vzdy predavan aktualni objekt renderedComponent
//        renderedComponent = SimpleRenderer.getRenderedComponent(component);
    }

    public static GUIManager getInstance() {
        if (instance == null) {
            instance = new GUIManager();
        }
        return instance;
    }

    public BufferedImage getImage(Resolution res) {
        renderedComponent = SimpleRenderer.getRenderedComponent(component, res);
        return renderedComponent;
    }

    // TODO predelat na variantu, ktera bere v potaz vice existujicich komponent
    public ActionArea getEventActionArea(int[] point) {

        ActionArea iArea = null;
        List<ActionArea> list = component.getActionAreas();
        for(ActionArea area : list) {
            if(area.getMinx() <= point[0] && 
               area.getMaxx() >= point[0] && 
               area.getMiny() <= point[1] && 
               area.getMaxy() >= point[1]) {
                iArea = area;
                break;
            }
        }
        
        // if no action area is event area, then return null
        return iArea;
    }
}
