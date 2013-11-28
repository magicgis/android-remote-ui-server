/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.gui.renderer;

import cz.ctu.guiproject.server.gui.bitmap.Codec;
import cz.ctu.guiproject.server.gui.device.ClientDevice;
import cz.ctu.guiproject.server.gui.painter.DefaultPainter;
import cz.ctu.guiproject.server.gui.painter.Painter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tomas.buk
 */
public class DefaultRenderer implements Renderer {

    private static DefaultRenderer instance;
    private Painter painter;
    private List<ClientDevice> observers;
    private String currentContext;

    /**
     * Private constructor, that fulfills the needs of singleton design pattern
     */
    private DefaultRenderer() {
        painter = new DefaultPainter();
        observers = new ArrayList<>();
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
    public void registerObserver(ClientDevice o) {
        if (!observers.contains(o)) {
            observers.add(o);
            // create initial context for particular client
            painter.setContext(o.getScreenWidth(), o.getScreenHeight());
            currentContext = Codec.encodeToBase64(painter.getContext(), "png");
            notifyObserver(o);
        }
    }

    @Override
    public void removeObserver(ClientDevice o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (ClientDevice device : observers) {
            painter.setContext(device.getScreenWidth(), device.getScreenHeight());
            currentContext = Codec.encodeToBase64(painter.getContext(), "png");
            device.update(currentContext);
        }
    }

    /**
     * Notifies specified observer, that init layout has been created.
     *
     * @param device
     */
    private void notifyObserver(ClientDevice device) {
        device.update(currentContext);
    }
}
