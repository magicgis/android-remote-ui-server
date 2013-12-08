/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.gui.painter;

import cz.ctu.guiproject.server.gui.device.ClientDevice;
import cz.ctu.guiproject.server.gui.entity.Layout;
import java.awt.image.BufferedImage;

/**
 *
 * @author tomas.buk
 */
public interface Painter {

    public BufferedImage getContext(ClientDevice clientDevice, Layout layout);
}
