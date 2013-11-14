/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.gui.device;

import cz.ctu.guiproject.server.gui.renderer.DefaultRenderer;
import cz.ctu.guiproject.server.gui.renderer.Renderer;
import java.util.Objects;

/**
 *
 * @author tomas.buk
 */
public class ClientDevice implements RendererObserver {

    private String id;
    private String name;
    private int screenWidth;
    private int screenHeight;
    // graphic context of the device
    private String context;
    private Renderer renderer;

    @SuppressWarnings("LeakingThisInConstructor")
    public ClientDevice() {
        context = null;
        renderer = DefaultRenderer.getInstance();
        renderer.registerObserver(this);
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

    public String getContext() {
        return context;
    }
    
    public void setContext(String context) {
        this.context = context;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
        hash = 67 * hash + Objects.hashCode(this.name);
        hash = 67 * hash + this.screenWidth;
        hash = 67 * hash + this.screenHeight;
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
        if (this.screenWidth != other.screenWidth) {
            return false;
        }
        if (this.screenHeight != other.screenHeight) {
            return false;
        }
        return true;
    }

    @Override
    public void update() {
        // TODO call renderer and request gui with adequate resolution
        context = renderer.getContext(this);
    }

    
}
