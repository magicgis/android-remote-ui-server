/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.business;

import cz.ctu.guiproject.server.gui.device.ClientDevice;

/**
 *
 * @author tomas.buk
 */
public interface ContextObserver {
    public void update(String context, ClientDevice device);
    public void update(String context, ClientDevice device, int[] updateArea);
}
