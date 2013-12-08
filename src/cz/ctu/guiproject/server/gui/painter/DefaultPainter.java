/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.gui.painter;

import cz.ctu.guiproject.server.gui.device.ClientDevice;
import cz.ctu.guiproject.server.gui.entity.Component;
import cz.ctu.guiproject.server.gui.entity.DefaultButton;
import cz.ctu.guiproject.server.gui.entity.DefaultComboBox;
import cz.ctu.guiproject.server.gui.entity.DefaultFader;
import cz.ctu.guiproject.server.gui.entity.DefaultRadioButton;
import cz.ctu.guiproject.server.gui.entity.DefaultToggleButton;
import cz.ctu.guiproject.server.gui.entity.Layout;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author tomas.buk
 */
public class DefaultPainter implements Painter {

    private static DefaultPainter instance;
    private Layout layout;
    private BufferedImage context;
    private Graphics2D g2d;

    private DefaultPainter() {
    }

    public static DefaultPainter getInstance() {
        if (instance == null) {
            instance = new DefaultPainter();
        }
        return instance;
    }

    @Override
    public BufferedImage getContext(ClientDevice clientDevice, Layout layout) {
        this.layout = layout;
        context = new BufferedImage(clientDevice.getScreenWidth(), clientDevice.getScreenHeight(), BufferedImage.TYPE_3BYTE_BGR);
        g2d = context.createGraphics();
        paint();
        return context;
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

    /**
     * Paints current layout with its corresponding components into current
     * context
     */
    private void paint() {
        // paint background color
        fillRect(0, 0, context.getWidth(), context.getHeight(), layout.getBackground());
        Queue<DefaultComboBox> comboQueue = new LinkedList<>();

        for (Component comp : layout.getComponents()) {
            if (comp instanceof DefaultRadioButton) {

                paintRadioButton((DefaultRadioButton) comp);

            } else if (comp instanceof DefaultComboBox) {

                comboQueue.add((DefaultComboBox) comp);

            } else if (comp instanceof DefaultToggleButton) {

                paintToggleButton((DefaultToggleButton) comp);

            } else if (comp instanceof DefaultButton) {

                paintButton((DefaultButton) comp);

            } else if (comp instanceof DefaultFader) {

                paintFader((DefaultFader) comp);

            }
        }
        // paint combo boxes at the end (they might overlay other components)
        while (!comboQueue.isEmpty()) {
            paintComboBox(comboQueue.remove());
        }
    }

    private void paintComboBox(DefaultComboBox c) {
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

        if (c.isSelected()) {
            String[] values = c.getValues();

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

    private void paintRadioButton(DefaultRadioButton r) {

        int outerDiameter = r.getOuterDiameter();
        int innerDiameter = r.getInnerDiameter();
        int posX = r.getPosX();
        int posY = r.getPosY();
        int border = r.getBorder();
        int labelSize = r.getLabelSize();

        int diff = (outerDiameter / 2) - innerDiameter / 2;
        if (r.isSelected()) {
            int innerX = posX + diff;
            int innerY = posY + diff;

            // draw elements
            fillCircle(posX, posY, outerDiameter, r.getOuterColor());
            drawCircle(posX, posY, outerDiameter, border, r.getBorderColor());
            fillCircle(innerX, innerY, innerDiameter, r.getInnerColor());

            // draw label
            int textX = posX + (int) (outerDiameter * 1.2);
            int textY = posY + (outerDiameter / 2) + (int) (labelSize / 2.6);
            drawString(textX, textY, r.getLabel(), labelSize, r.getLabelColor());

        } else {
            // draw elements
            fillCircle(posX, posY, outerDiameter, r.getOuterColor());
            drawCircle(posX, posY, outerDiameter, border, r.getBorderColor());

            // draw label
            int textX = posX + (int) (outerDiameter * 1.2);
            int textY = posY + (outerDiameter / 2) + (int) (labelSize / 2.6);
            drawString(textX, textY, r.getLabel(), labelSize, r.getLabelColor());
        }
    }

    private void paintToggleButton(DefaultToggleButton t) {

        int textY = t.getPosY() + (t.getOuterHeight() / 2) + (t.getLabelSize() / 2) - 5;

        if (t.isPressed()) {
            fillRect(t.getPosX(), t.getPosY(), t.getOuterWidth(), t.getOuterHeight(), t.getInnerColor());
            drawRect(t.getPosX(), t.getPosY(), t.getOuterWidth(), t.getOuterHeight(), t.getBorder(), t.getBorderColor());
            drawString(t.getPosX() + 10, textY, t.getLabel(), t.getLabelSize(), t.getLabelColor());
        } else {
            fillRect(t.getPosX(), t.getPosY(), t.getOuterWidth(), t.getOuterHeight(), t.getInnerColorPressed());
            drawRect(t.getPosX(), t.getPosY(), t.getOuterWidth(), t.getOuterHeight(), t.getBorder(), t.getBorderColorPressed());
            drawString(t.getPosX() + 10, textY, t.getLabel(), t.getLabelSize(), t.getLabelColorPressed());
        }
    }

    private void paintButton(DefaultButton b) {

        int textY = b.getPosY() + (b.getOuterHeight() / 2) + (b.getLabelSize() / 2) - 5;

        if (b.isPressed()) {
            fillRect(b.getPosX(), b.getPosY(), b.getOuterWidth(), b.getOuterHeight(), b.getOuterColorPressed());
            drawRect(b.getPosX(), b.getPosY(), b.getOuterWidth(), b.getOuterHeight(), b.getBorder(), b.getBorderColorPressed());
            drawString(b.getPosX() + 10, textY, b.getLabel(), b.getLabelSize(), b.getLabelColorPressed());
        } else {
            fillRect(b.getPosX(), b.getPosY(), b.getOuterWidth(), b.getOuterHeight(), b.getOuterColor());
            drawRect(b.getPosX(), b.getPosY(), b.getOuterWidth(), b.getOuterHeight(), b.getBorder(), b.getBorderColor());
            drawString(b.getPosX() + 10, textY, b.getLabel(), b.getLabelSize(), b.getLabelColor());
        }
    }

    private void paintFader(DefaultFader f) {

        fillRect(f.getPosX(), f.getPosY(), f.getOuterWidth(), f.getOuterHeight(), f.getOuterColor());
        drawRect(f.getPosX(), f.getPosY(), f.getOuterWidth(), f.getOuterHeight(), f.getBorder(), f.getBorderColor());
        // paint start stopper
        fillRect(f.getPosX(), f.getPosY(), f.getStopperWidth(), f.getStopperHeight(), f.getStopperColor());
        drawRect(f.getPosX(), f.getPosY(), f.getStopperWidth(), f.getStopperHeight(), f.getStopperBorder(), f.getStopperBorderColor());
        // paint end stopper
        int endPosX = f.getPosX() + f.getOuterWidth() - f.getStopperWidth();
        fillRect(endPosX, f.getPosY(), f.getStopperWidth(), f.getStopperHeight(), f.getStopperColor());
        drawRect(endPosX, f.getPosY(), f.getStopperWidth(), f.getStopperHeight(), f.getStopperBorder(), f.getStopperBorderColor());
        // paint caret on specified position
        int caretPath = f.getOuterWidth() - (2 * f.getStopperWidth()) - f.getCaretWidth();
        int caret0 = f.getPosX() + f.getStopperWidth();
        int caretPosX = caret0 + (int) ((f.getCaretPosition() / 100.f) * caretPath);

        fillRect(caretPosX, f.getPosY(), f.getCaretWidth(), f.getCaretHeight(), f.getCaretColor());
        drawRect(caretPosX, f.getPosY(), f.getCaretWidth(), f.getCaretHeight(), f.getCaretBorder(), f.getCaretBorderColor());
    }
}
