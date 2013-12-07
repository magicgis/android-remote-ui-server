/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.gui.test;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author tomas.buk
 */
public class Viewer extends JPanel {

    private static Viewer instance;
    private BufferedImage image;
    private JFrame frame;

    private Viewer() {
        super(new GridLayout(1, 1));
    }

    public static Viewer getInstance() {
        if (instance == null) {
            instance = new Viewer();
        }
        return instance;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }

    public void display(BufferedImage image) {
        this.image = image;

        frame = new JFrame("Viewer");
        frame.getContentPane().add(this);

        setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        repaint();
    }
}
