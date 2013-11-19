/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.gui.painter;

import java.awt.image.BufferedImage;

/**
 *
 * @author tomas.buk
 */
public interface Painter {

    public void setContext(int width, int height);

    public BufferedImage getContext();
}
