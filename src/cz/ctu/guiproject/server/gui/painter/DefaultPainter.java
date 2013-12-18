/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.gui.painter;

import cz.ctu.guiproject.server.gui.device.ClientDevice;
import cz.ctu.guiproject.server.gui.device.StateLayoutManager;
import cz.ctu.guiproject.server.gui.entity.Component;
import cz.ctu.guiproject.server.gui.entity.DefaultButton;
import cz.ctu.guiproject.server.gui.entity.DefaultComboBox;
import cz.ctu.guiproject.server.gui.entity.DefaultFader;
import cz.ctu.guiproject.server.gui.entity.DefaultLabel;
import cz.ctu.guiproject.server.gui.entity.DefaultRadioButton;
import cz.ctu.guiproject.server.gui.entity.DefaultRadioGroup;
import cz.ctu.guiproject.server.gui.entity.DefaultToggleButton;
import cz.ctu.guiproject.server.gui.entity.Layout;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author tomas.buk
 */
public class DefaultPainter implements Painter {

    private static DefaultPainter instance;
    private Layout metricLayout;
    private BufferedImage context;
    private Graphics2D g2d;
    private Layout stateLayout;

    private DefaultPainter() {
        stateLayout = StateLayoutManager.getInstance().getStateLayout();
    }

    public static DefaultPainter getInstance() {
        if (instance == null) {
            instance = new DefaultPainter();
        }
        return instance;
    }

    @Override
    public BufferedImage getContext(ClientDevice clientDevice, Layout metricLayout) {
        this.metricLayout = metricLayout;
        context = new BufferedImage(clientDevice.getScreenWidth(), clientDevice.getScreenHeight(), BufferedImage.TYPE_3BYTE_BGR);
        g2d = context.createGraphics();
        
        paint(null, null);
        return context;
    }

    @Override
    public BufferedImage getContext(ClientDevice clientDevice, Layout layout, Component statusComp, Component metricComp) {
        this.metricLayout = layout;
        int[] actionArea = metricComp.getActionArea();
        context = new BufferedImage(actionArea[2] - actionArea[0], actionArea[3] - actionArea[1], BufferedImage.TYPE_3BYTE_BGR);
        g2d = context.createGraphics();

        paint(statusComp, metricComp);
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
    private void paint(Component stateComponent, Component metricComponent) {
        // paint background color
        fillRect(0, 0, context.getWidth(), context.getHeight(), metricLayout.getBackground());
        Queue<DefaultComboBox> comboQueue = new LinkedList<>();

        List<Component> metricQueue;
        List<Component> stateQueue;
        boolean update;
        if (stateComponent == null) {
            metricQueue = metricLayout.getComponents();
            stateQueue = stateLayout.getComponents();
            update = false;
        } else {
            metricQueue = new ArrayList<>();
            metricQueue.add(metricComponent);
            stateQueue = new ArrayList<>();
            stateQueue.add(stateComponent);
            // !!TODO with stateQueue
            update = true;
        }
        
        Iterator<Component> itState = stateQueue.iterator();
        Iterator<Component> itMetric = metricQueue.iterator();
        
        while(itState.hasNext()) {
            Component stateComp = itState.next();
            Component metricComp = itMetric.next();
            
            if (stateComp instanceof DefaultRadioButton) {

                paintRadioButton((DefaultRadioButton) stateComp, (DefaultRadioButton) metricComp, update, null);

            } else if (stateComp instanceof DefaultComboBox) {

                comboQueue.add((DefaultComboBox) stateComp);
                comboQueue.add((DefaultComboBox) metricComp);

            } else if (stateComp instanceof DefaultToggleButton) {

                paintToggleButton((DefaultToggleButton) stateComp, (DefaultToggleButton) metricComp, update);

            } else if (stateComp instanceof DefaultButton) {

                paintButton((DefaultButton) stateComp, (DefaultButton) metricComp, update);

            } else if (stateComp instanceof DefaultFader) {

                paintFader((DefaultFader) stateComp, (DefaultFader) metricComp, update);

            } else if (stateComp instanceof DefaultRadioGroup) {

                paintRadioGroup((DefaultRadioGroup) stateComp, (DefaultRadioGroup) metricComp, update);
            } else if(stateComp instanceof DefaultLabel) {
                
                paintLabel((DefaultLabel) stateComp, (DefaultLabel) metricComp, update);
            }
        }
        // paint combo boxes at the end (they might overlay other components)
        while (!comboQueue.isEmpty()) {
            paintComboBox(comboQueue.remove(), comboQueue.remove(), update);
        }
    }

    private void paintComboBox(DefaultComboBox stateC, DefaultComboBox metricC, boolean update) {

        int x = 0, y = 0;
        if (!update) {
            x = metricC.getPosX();
            y = metricC.getPosY();
        }

        // draw outer border
        fillRect(x, y, metricC.getOuterWidth(), metricC.getOuterHeight(), stateC.getOuterColor());
        drawRect(x, y, metricC.getOuterWidth(), metricC.getOuterHeight(), metricC.getBorder(), stateC.getBorderColor());

        // draw arrow 10 pixels from the right border
        int[] arrow = metricC.getArrowCoords();
        int diffX = x + metricC.getOuterWidth() - 10 - (arrow[0] + arrow[4]);
        int diffY = y + metricC.getOuterHeight() - 10 - (arrow[1] + arrow[3]);
        int[] xCoords = {arrow[0] + diffX, arrow[2] + diffX, arrow[4] + diffX};
        int[] yCoords = {arrow[1] + diffY, arrow[3] + diffY, arrow[5] + diffY};
        fillTriangle(xCoords, yCoords, stateC.getArrowColor());
        if (stateC.getSelectedValue() != null) {
            drawString(x + 10, y + metricC.getValueSize() + 5, stateC.getSelectedValue(), metricC.getValueSize(), stateC.getValueColor());
        }

        if (stateC.isSelected()) {
            String[] values = stateC.getValues();

            int rowHeight = metricC.getOuterHeight();
            int rowBeginY = y + metricC.getOuterHeight();
            int valBeginX = x + 10;

            for (int i = 0; i < values.length; i++) {
                if (i % 2 == 0) {
                    fillRect(x, rowBeginY, metricC.getOuterWidth(), metricC.getOuterHeight(), stateC.getDownEvenColor());

                } else {
                    fillRect(x, rowBeginY, metricC.getOuterWidth(), metricC.getOuterHeight(), stateC.getDownOddColor());
                }
                drawString(valBeginX, rowBeginY + metricC.getValueSize() + 5, values[i], metricC.getValueSize(), stateC.getValueColor());
                rowBeginY += rowHeight;
            }
//                    // TODO remove when not DEBUGGING
//                    drawRect(c.getPosX(), c.getPosY(), c.getOuterWidth(), (c.getValues().length + 1) * c.getOuterHeight(), 3, "#0000ff");
        } else {
        }
    }

    private void paintRadioButton(DefaultRadioButton stateR, DefaultRadioButton metricR, boolean update, int[] groupArea) {

        int outerDiameter = metricR.getOuterDiameter();
        int innerDiameter = metricR.getInnerDiameter();
        int x, y;

        if (update) {
            if (groupArea == null) {
                x = (update) ? 0 : metricR.getPosX();
                y = (update) ? 0 : metricR.getPosY();
            } else {
                x = metricR.getPosX() - groupArea[0];
                y = metricR.getPosY() - groupArea[1];
            }
        } else {
            x = metricR.getPosX();
            y = metricR.getPosY();
        }

        int border = metricR.getBorder();
        int labelSize = metricR.getLabelSize();

        int diff = (outerDiameter / 2) - innerDiameter / 2;
        if (stateR.isSelected()) {
            int innerX = x + diff;
            int innerY = y + diff;

            // draw elements
            fillCircle(x, y, outerDiameter, stateR.getOuterColor());
            drawCircle(x, y, outerDiameter, border, stateR.getBorderColor());
            fillCircle(innerX, innerY, innerDiameter, stateR.getInnerColor());

            // draw label
            int textX = x + (int) (outerDiameter * 1.2);
            int textY = y + (outerDiameter / 2) + (int) (labelSize / 2.6);
            drawString(textX, textY, stateR.getLabel(), labelSize, stateR.getLabelColor());

        } else {
            // draw elements
            fillCircle(x, y, outerDiameter, stateR.getOuterColor());
            drawCircle(x, y, outerDiameter, border, stateR.getBorderColor());

            // draw label
            int textX = x + (int) (outerDiameter * 1.2);
            int textY = y + (outerDiameter / 2) + (int) (labelSize / 2.6);
            drawString(textX, textY, stateR.getLabel(), labelSize, stateR.getLabelColor());
        }
    }

    private void paintToggleButton(DefaultToggleButton stateT, DefaultToggleButton metricT, boolean update) {

        int x = (update) ? 0 : metricT.getPosX();
        int y = (update) ? 0 : metricT.getPosY();

        int textY = y + (metricT.getOuterHeight() / 2) + (metricT.getLabelSize() / 2) - 5;

        if (stateT.isPressed()) {
            fillRect(x, y, metricT.getOuterWidth(), metricT.getOuterHeight(), stateT.getInnerColor());
            drawRect(x, y, metricT.getOuterWidth(), metricT.getOuterHeight(), metricT.getBorder(), stateT.getBorderColor());
            drawString(x + 10, textY, stateT.getLabel(), metricT.getLabelSize(), stateT.getLabelColor());
        } else {
            fillRect(x, y, metricT.getOuterWidth(), metricT.getOuterHeight(), stateT.getInnerColorPressed());
            drawRect(x, y, metricT.getOuterWidth(), metricT.getOuterHeight(), metricT.getBorder(), stateT.getBorderColorPressed());
            drawString(x + 10, textY, stateT.getLabel(), metricT.getLabelSize(), stateT.getLabelColorPressed());
        }
    }

    private void paintButton(DefaultButton stateB, DefaultButton metricB, boolean update) {

        int x = (update) ? 0 : metricB.getPosX();
        int y = (update) ? 0 : metricB.getPosY();

        int textY = y + (metricB.getOuterHeight() / 2) + (metricB.getLabelSize() / 2) - 5;

        if (stateB.isPressed()) {
            fillRect(x, y, metricB.getOuterWidth(), metricB.getOuterHeight(), stateB.getOuterColorPressed());
            drawRect(x, y, metricB.getOuterWidth(), metricB.getOuterHeight(), metricB.getBorder(), stateB.getBorderColorPressed());
            drawString(x + 10, textY, stateB.getLabel(), metricB.getLabelSize(), stateB.getLabelColorPressed());
        } else {
            fillRect(x, y, metricB.getOuterWidth(), metricB.getOuterHeight(), stateB.getOuterColor());
            drawRect(x, y, metricB.getOuterWidth(), metricB.getOuterHeight(), metricB.getBorder(), stateB.getBorderColor());
            drawString(x + 10, textY, stateB.getLabel(), metricB.getLabelSize(), stateB.getLabelColor());
        }
    }

    private void paintFader(DefaultFader stateF, DefaultFader metricF, boolean update) {

        int x = (update) ? 0 : metricF.getPosX();
        int y = (update) ? 0 : metricF.getPosY();

        fillRect(x, y, metricF.getOuterWidth(), metricF.getOuterHeight(), stateF.getOuterColor());
        drawRect(x, y, metricF.getOuterWidth(), metricF.getOuterHeight(), metricF.getBorder(), stateF.getBorderColor());
        // paint start stopper
        fillRect(x, y, metricF.getStopperWidth(), metricF.getStopperHeight(), stateF.getStopperColor());
        drawRect(x, y, metricF.getStopperWidth(), metricF.getStopperHeight(), metricF.getStopperBorder(), stateF.getStopperBorderColor());
        // paint end stopper
        int endPosX = x + metricF.getOuterWidth() - metricF.getStopperWidth();
        fillRect(endPosX, y, metricF.getStopperWidth(), metricF.getStopperHeight(), stateF.getStopperColor());
        drawRect(endPosX, y, metricF.getStopperWidth(), metricF.getStopperHeight(), metricF.getStopperBorder(), stateF.getStopperBorderColor());
        // paint caret on specified position
        int caretPath = metricF.getOuterWidth() - (2 * metricF.getStopperWidth()) - metricF.getCaretWidth();
        int caret0 = x + metricF.getStopperWidth();
        int caretPosX = caret0 + (int) ((stateF.getCaretPosition() / 100.f) * caretPath);

        fillRect(caretPosX, y, metricF.getCaretWidth(), metricF.getCaretHeight(), stateF.getCaretColor());
        drawRect(caretPosX, y, metricF.getCaretWidth(), metricF.getCaretHeight(), metricF.getCaretBorder(), stateF.getCaretBorderColor());
    }

    private void paintRadioGroup(DefaultRadioGroup stateG, DefaultRadioGroup metricG, boolean update) {
        
        Iterator<DefaultRadioButton> stateIt = stateG.getRadios().iterator();
        Iterator<DefaultRadioButton> metricIt = metricG.getRadios().iterator();
        
        while(stateIt.hasNext()) {
            paintRadioButton(stateIt.next(), metricIt.next(), update, metricG.getActionArea());
        }
    }
    
    private void paintLabel(DefaultLabel stateL, DefaultLabel metricL, boolean update) {
        int x = (update) ? 0 : metricL.getPosX();
        int y = (update) ? 0 : metricL.getPosY();
        
        int textY = metricL.getLabelSize() + y;
        drawString(x, textY, stateL.getLabel(), metricL.getLabelSize(), stateL.getLabelColor());
    }
}
