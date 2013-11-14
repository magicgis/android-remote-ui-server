/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.gui.renderer;

import cz.ctu.guiproject.server.gui.bitmap.Codec;
import cz.ctu.guiproject.server.gui.device.ClientDevice;
import cz.ctu.guiproject.server.gui.device.RendererObserver;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author tomas.buk
 */
public class DefaultRenderer implements Renderer {

    private static DefaultRenderer instance;
    // list of all registered observers
    private ArrayList<RendererObserver> observers;

    /**
     * Private constructor, that fulfills the needs of singleton design pattern
     */
    private DefaultRenderer() {
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
        // TODO replace with non dummy data
        BufferedImage image = new BufferedImage(clientDevice.getScreenWidth(), clientDevice.getScreenHeight(), BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D g2d = image.createGraphics();
        g2d.setPaint(new Color(255, 0, 0));
        g2d.fillRect(0, 0, image.getWidth(), image.getHeight());
        
        return Codec.encodeToBase64(image, "png");
    }
}
