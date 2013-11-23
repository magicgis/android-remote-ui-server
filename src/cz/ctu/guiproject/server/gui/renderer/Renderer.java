/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.gui.renderer;

import cz.ctu.guiproject.server.gui.device.ClientDevice;
import cz.ctu.guiproject.server.gui.entity.Layout;

/**
 *
 * @author tomas.buk
 */
public interface Renderer {

    public Layout getLayout();

    public void registerObserver(ClientDevice device);

    public void removeObserver(ClientDevice device);

    public void notifyObservers();
}
