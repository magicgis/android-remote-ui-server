/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.gui.painter;

import cz.ctu.guiproject.server.gui.entity.Component;
import cz.ctu.guiproject.server.gui.entity.Layout;
import cz.ctu.guiproject.server.gui.entity.DefaultRadioButton;
import cz.ctu.guiproject.server.gui.loader.Loader;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author tomas.buk
 */
public class DefaultPainter implements Painter {

    private Layout layout;
    private BufferedImage context;
    private Graphics2D g2d;
    private DefaultRadioButton defaultRadioButton;

    public DefaultPainter(Layout layout) {
        this.layout = layout;
        defaultRadioButton = Loader.loadDefaultRadioButton();
    }

    /**
     * Converts hexadecimal String into its corresponding color
     *
     * @param hex
     * @return
     */
    private Color toColor(String hex) {
        Integer value = Integer.parseInt(hex.substring(1), 16);
        return new Color(value);
    }

    private void fillRect(int x, int y, int width, int height, String color) {
        g2d.setColor(toColor(color));
        g2d.fillRect(x, y, width, height);
    }

    private void fillCircle(int x, int y, int diameter, String color) {
        g2d.setColor(toColor(color));
        g2d.fillOval(x, y, diameter, diameter);
    }

    private void drawCircle(int x, int y, int diameter, int stroke, String color) {
        g2d.setColor(toColor(color));
        g2d.setStroke(new BasicStroke(stroke));
        g2d.drawOval(x, y, diameter, diameter);
    }

    private void drawString(int x, int y, String str, int size, String color) {
        g2d.setColor(toColor(color));
        g2d.setFont(new Font(null, Font.PLAIN, size));
        g2d.drawString(str, x, y);
    }

    @Override
    public BufferedImage getContext() {
        return context;
    }

    @Override
    public void setContext(int width, int height) {
        context = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        g2d = context.createGraphics();
        paint();
    }

    private void paint() {
        // paint background color
        fillRect(0, 0, context.getWidth(), context.getHeight(), layout.getBackground());

        int diff = (defaultRadioButton.getOuterDiameter() / 2) - defaultRadioButton.getInnerDiameter() / 2;
        int innerX = defaultRadioButton.getPosX() + diff;
        int innerY = defaultRadioButton.getPosY() + diff;

        // display default radio button
        fillCircle(defaultRadioButton.getPosX(), defaultRadioButton.getPosY(), defaultRadioButton.getOuterDiameter(), defaultRadioButton.getOuterColor());
        drawCircle(defaultRadioButton.getPosX(), defaultRadioButton.getPosY(), defaultRadioButton.getOuterDiameter(), defaultRadioButton.getBorder(), defaultRadioButton.getBorderColor());
        fillCircle(innerX, innerY, defaultRadioButton.getInnerDiameter(), defaultRadioButton.getInnerColor());
        // paint label
        int textX = defaultRadioButton.getPosX() + (int) (defaultRadioButton.getOuterDiameter() * 1.2);
        int textY = defaultRadioButton.getPosY() + (defaultRadioButton.getOuterDiameter() / 2) + (int)(defaultRadioButton.getLabelSize() / 2.6) ;
        drawString(textX, textY, defaultRadioButton.getLabel(), defaultRadioButton.getLabelSize(), defaultRadioButton.getLabelColor());




//        // iterate over all components
//        for(Component comp : layout.getComponents()) {
//            
//            if(comp instanceof RadioButton) {
//                
//                continue;
//            }
//        }
    }
}
