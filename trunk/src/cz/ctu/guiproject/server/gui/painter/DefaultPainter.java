/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.gui.painter;

import cz.ctu.guiproject.server.gui.entity.Component;
import cz.ctu.guiproject.server.gui.entity.DefaultComboBox;
import cz.ctu.guiproject.server.gui.entity.DefaultRadioButton;
import cz.ctu.guiproject.server.gui.entity.Layout;
import cz.ctu.guiproject.server.gui.entity.LayoutManager;
import cz.ctu.guiproject.server.gui.entity.LayoutObserver;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.logging.Logger;

/**
 *
 * @author tomas.buk
 */
public class DefaultPainter implements Painter, LayoutObserver {

    private Layout layout;
    private BufferedImage context;
    private Graphics2D g2d;
    private static final Logger logger = Logger.getLogger(DefaultPainter.class.getName());

    @SuppressWarnings("LeakingThisInConstructor")
    public DefaultPainter() {
        LayoutManager layoutManager = LayoutManager.getInstance();
        layoutManager.registerObserver(this);
        layout = layoutManager.getInitialLayout();
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
     * Paints rectangular border with given parameters into current context
     *
     * @param x
     * @param y
     * @param width
     * @param height
     * @param color
     */
    private void drawRect(int x, int y, int width, int height, int stroke, String color) {
        g2d.setColor(toColor(color));
        g2d.setStroke(new BasicStroke(stroke));
        g2d.drawRect(x, y, width, height);
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

    private void fillTriangle(int[] xCoords, int[] yCoords, String color) {
        // TODO predelat na neco mene narocneho na entite (2 pole??)
        g2d.setColor(toColor(color));
        g2d.fillPolygon(xCoords, yCoords, 3);
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
                if (((DefaultRadioButton) comp).isSelected()) {
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
                } else {
                    // draw elements
                    fillCircle(r.getPosX(), r.getPosY(), r.getOuterDiameter(), r.getOuterColor());
                    drawCircle(r.getPosX(), r.getPosY(), r.getOuterDiameter(), r.getBorder(), r.getBorderColor());

                    // draw label
                    int textX = r.getPosX() + (int) (r.getOuterDiameter() * 1.2);
                    int textY = r.getPosY() + (r.getOuterDiameter() / 2) + (int) (r.getLabelSize() / 2.6);
                    drawString(textX, textY, r.getLabel(), r.getLabelSize(), r.getLabelColor());
                }
            } else if (comp instanceof DefaultComboBox) {

                DefaultComboBox c = (DefaultComboBox) comp;
                // draw outer border
                fillRect(c.getPosX(), c.getPosY(), c.getOuterWidth(), c.getOuterHeight(), c.getOuterColor());
                drawRect(c.getPosX(), c.getPosY(), c.getOuterWidth(), c.getOuterHeight(), c.getBorder(), c.getBorderColor());

                // draw arrow 10 pixels from the right border
                int[] arrow = c.getArrowCoords();
                int diffX = c.getPosX() + c.getOuterWidth() - 10 - (arrow[0] + arrow[4]);
                int diffY = c.getPosY() + c.getOuterHeight() - 10 - (arrow[1] + arrow[3]);
                int[] xCoords = {arrow[0] + diffX, arrow[2] + diffX, arrow[4] + diffX};
                int[] yCoords = {arrow[1] + diffY, arrow[3] + diffY, arrow[5] + diffY};
                fillTriangle(xCoords, yCoords, c.getArrowColor());
                if (c.getSelectedValue() != null) {
                    drawString(c.getPosX() + 10, c.getPosY() + c.getValueSize() + 5, c.getSelectedValue(), c.getValueSize(), c.getValueColor());
                }

                if (((DefaultComboBox) comp).isSelected()) {
                    String[] values = ((DefaultComboBox) comp).getValues();

                    int rowHeight = c.getOuterHeight();
                    int rowBeginY = c.getPosY() + c.getOuterHeight();
                    int valBeginX = c.getPosX() + 10;

                    for (int i = 0; i < values.length; i++) {
                        if (i % 2 == 0) {
                            fillRect(c.getPosX(), rowBeginY, c.getOuterWidth(), c.getOuterHeight(), c.getDownEvenColor());

                        } else {
                            fillRect(c.getPosX(), rowBeginY, c.getOuterWidth(), c.getOuterHeight(), c.getDownOddColor());
                        }
                        drawString(valBeginX, rowBeginY + c.getValueSize() + 5, values[i], c.getValueSize(), c.getValueColor());
                        rowBeginY += rowHeight;
                    }
//                    // TODO remove when not DEBUGGING
//                    drawRect(c.getPosX(), c.getPosY(), c.getOuterWidth(), (c.getValues().length + 1) * c.getOuterHeight(), 3, "#0000ff");
                } else {
                }
            }
        }
    }

    @Override
    public void update(Layout layout) {
        this.layout = layout;
    }
}
