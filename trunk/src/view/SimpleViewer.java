/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author tomique
 */
public class SimpleViewer extends JPanel {
    
    private BufferedImage image;
    
    public SimpleViewer(BufferedImage image) {
        this.image = image;
        this.setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
        init();
    }
    
    private void init() {
        JFrame frame = new JFrame("Simple viewer");
        frame.getContentPane().add(this);        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
        frame.pack();
        frame.setVisible(true);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }
}
