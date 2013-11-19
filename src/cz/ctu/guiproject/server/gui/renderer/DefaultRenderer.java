/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.gui.renderer;

import cz.ctu.guiproject.server.gui.bitmap.Codec;
import cz.ctu.guiproject.server.gui.device.ClientDevice;
import cz.ctu.guiproject.server.gui.device.RendererObserver;
import cz.ctu.guiproject.server.gui.entity.Component;
import cz.ctu.guiproject.server.gui.entity.DefaultRadioButton;
import cz.ctu.guiproject.server.gui.entity.Layout;
import cz.ctu.guiproject.server.gui.loader.Loader;
import cz.ctu.guiproject.server.gui.painter.DefaultPainter;
import cz.ctu.guiproject.server.gui.painter.Painter;
import java.util.ArrayList;

/**
 *
 * @author tomas.buk
 */
public class DefaultRenderer implements Renderer {

    private static DefaultRenderer instance;
    // list of all registered observers
    private ArrayList<RendererObserver> observers;
    private Painter painter;

    /**
     * Private constructor, that fulfills the needs of singleton design pattern
     */
    private DefaultRenderer() {
        observers = new ArrayList<>();
        
        painter = new DefaultPainter(initLayout());
    }
    
    private Layout initLayout() {
        Layout layout = Loader.loadLayout();
        DefaultRadioButton defaultRadio = Loader.loadDefaultRadioButton();
        // add default components
        for(Component comp : layout.getComponents()) {
            if(comp instanceof DefaultRadioButton) {
                String label = ((DefaultRadioButton) comp).getLabel();
                comp = defaultRadio;
                ((DefaultRadioButton) comp).setLabel(label);
                continue;
            }
        }
        
        return layout;
    }

    /**
     * Returns the only existing instance of class DefaultRenderer
     *
     * @return the only existing instance of class DefaultRenderer
     */
    public static DefaultRenderer getInstance() {
        if (instance == null) {
            instance = new DefaultRenderer();
        }
        return instance;
    }

    @Override
    public void registerObserver(RendererObserver o) {
        if (!observers.contains(o)) {
            observers.add(o);
        }
    }

    @Override
    public void removeObserver(RendererObserver o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (RendererObserver observer : observers) {
            observer.update();
        }
    }

    @Override
    public String getContext(ClientDevice clientDevice) {
        // based on screen dimensions, new context with given properties is created
        // TODO choice of image format!
        painter.setContext(clientDevice.getScreenWidth(), clientDevice.getScreenHeight());
        return Codec.encodeToBase64(painter.getContext(), "png");
    }
}
