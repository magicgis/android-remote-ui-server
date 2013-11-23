/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.gui.painter;

import cz.ctu.guiproject.server.gui.entity.Component;
import cz.ctu.guiproject.server.gui.entity.DefaultRadioButton;
import cz.ctu.guiproject.server.gui.entity.Layout;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
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

    public DefaultPainter(Layout layout) {
        this.layout = layout;
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

    /**
     * Paints rectangle with given parameters into current context
     *
     * @param x x coordinate of upper left corner
     * @param y y coordinate of upper left corner
     * @param width width of the rectangle
     * @param height height of the rectangle
     * @param color color of the rectangle
     */
    private void fillRect(int x, int y, int width, int height, String color) {
        g2d.setColor(toColor(color));
        g2d.fillRect(x, y, width, height);
    }

    /**
     * Paints solid circle with given parameters into current context
     *
     * @param x x coordinate of the upper left corner of aabb of the circle
     * @param y y coordinate of the upper right corner of aabb of the circle
     * @param diameter diameter of the circle
     * @param color color of the circle
     */
    private void fillCircle(int x, int y, int diameter, String color) {
        g2d.setColor(toColor(color));
        g2d.fillOval(x, y, diameter, diameter);
    }

    /**
     * Paints border of the circle with given parameters into current context
     *
     * @param x x coordinate of the upper left corner of aabb of the circle
     * @param y y coordinate of the upper right corner of aabb of the circle
     * @param diameter diameter of the circle
     * @param stroke border width
     * @param color color of the circle
     */
    private void drawCircle(int x, int y, int diameter, int stroke, String color) {
        g2d.setColor(toColor(color));
        g2d.setStroke(new BasicStroke(stroke));
        g2d.drawOval(x, y, diameter, diameter);
    }

    /**
     * Paints string with given parameters into current context
     *
     * @param x x coordinate of the lower left corner of the text
     * @param y y coordinate of the lower left corner of the text
     * @param str painted text
     * @param size font size
     * @param color font color
     */
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

    /**
     * Paints current layout with its corresponding components into current
     * context
     */
    private void paint() {
        // paint background color
        fillRect(0, 0, context.getWidth(), context.getHeight(), layout.getBackground());

        for (Component comp : layout.getComponents()) {
            if (comp instanceof DefaultRadioButton) {
                DefaultRadioButton r = (DefaultRadioButton) comp;
                int diff = (r.getOuterDiameter() / 2) - r.getInnerDiameter() / 2;
                int innerX = r.getPosX() + diff;
                int innerY = r.getPosY() + diff;

                // draw elements
                fillCircle(r.getPosX(), r.getPosY(), r.getOuterDiameter(), r.getOuterColor());
                drawCircle(r.getPosX(), r.getPosY(), r.getOuterDiameter(), r.getBorder(), r.getBorderColor());
                fillCircle(innerX, innerY, r.getInnerDiameter(), r.getInnerColor());

                // draw label
                int textX = r.getPosX() + (int) (r.getOuterDiameter() * 1.2);
                int textY = r.getPosY() + (r.getOuterDiameter() / 2) + (int) (r.getLabelSize() / 2.6);
                drawString(textX, textY, r.getLabel(), r.getLabelSize(), r.getLabelColor());
                continue;
            }
        }
    }
}
