/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.gui.renderer;

import cz.ctu.guiproject.server.gui.device.ClientDevice;
import cz.ctu.guiproject.server.gui.device.RendererObserver;

/**
 *
 * @author tomas.buk
 */
public interface Renderer {
    
    public void registerObserver(RendererObserver o);
    
    public void removeObserver(RendererObserver o);
    
    public void notifyObservers();
    
    public String getContext(ClientDevice clientDevice);

}
