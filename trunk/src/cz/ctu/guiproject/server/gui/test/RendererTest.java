/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.gui.test;

import cz.ctu.guiproject.server.gui.bitmap.Codec;
import cz.ctu.guiproject.server.gui.device.ClientDevice;
import cz.ctu.guiproject.server.gui.renderer.DefaultRenderer;
import cz.ctu.guiproject.server.gui.renderer.Renderer;

/**
 *
 * @author tomas.buk
 */
public class RendererTest {

    public RendererTest() {
        init();
    }

    private void init() {

        Renderer renderer = DefaultRenderer.getInstance();

        ClientDevice device = new ClientDevice();
        device.setScreenWidth(640);
        device.setScreenHeight(480);
        Viewer viewer = Viewer.getInstance();
//        viewer.display(Codec.decodeBase64(renderer.getContext(device.getScreenWidth(), device.getScreenHeight())));
    }

    public static void main(String[] args) {
        new RendererTest();
    }
}
