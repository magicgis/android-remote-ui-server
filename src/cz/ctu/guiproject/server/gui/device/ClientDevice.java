/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.gui.device;

import cz.ctu.guiproject.server.business.ContextObserver;
import cz.ctu.guiproject.server.gui.bitmap.Codec;
import cz.ctu.guiproject.server.gui.painter.DefaultPainter;
import cz.ctu.guiproject.server.gui.painter.Painter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tomas.buk
 */
public class ClientDevice {

    private final static Logger logger = Logger.getLogger(ClientDevice.class.getName());
    private String id;
    private String name;
    // graphic context of the device
    private String context;
    private int screenWidth;
    private int screenHeight;
    private int dpi;
    private ClientLayout clientLayout;
    private List<ContextObserver> observers;
    private Painter painter;
    
    public ClientDevice(String id, String name, int screenWidth, int screenHeight, int dpi) {
        this.id = id;
        this.name = name;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.dpi = dpi;
        observers = new ArrayList<>();
        clientLayout = new ClientLayout(dpi);
        painter = DefaultPainter.getInstance();
    }

    public ClientLayout getClientLayout() {
        return clientLayout;
    }

    public int getDpi() {
        return dpi;
    }

    public void setDpi(int dpi) {
        this.dpi = dpi;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContext() {
        logger.log(Level.INFO, "getContext() called");
        return context;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    public void updateContext() {
        this.context = Codec.encodeToBase64(painter.getContext(this, clientLayout.getLayout()), "png");
        notifyObservers();
    }

    public void update(String context) {
        // TODO maybe remove setContext(context), because they perform the same operation 
        this.context = context;
        notifyObservers();
    }

    public void registerObserver(ContextObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void removeObserver(ContextObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (ContextObserver observer : observers) {
            observer.update(context, this);
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
        hash = 67 * hash + Objects.hashCode(this.name);
        hash = 67 * hash + screenWidth;
        hash = 67 * hash + screenHeight;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ClientDevice other = (ClientDevice) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (screenWidth != other.screenWidth) {
            return false;
        }
        if (this.screenHeight != other.screenHeight) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("ClientDevice: [id: ").append(id);
        sb.append(", name: ").append(name);
        sb.append(", screenWidth: ").append(screenWidth);
        sb.append(", screenHeight: ").append(screenHeight);
        sb.append(", dpi: ").append(dpi).append("]");

        return sb.toString();
    }
}
