/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.gui.entity;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 *
 * @author tomas.buk
 */
@Root
public class DefaultButton extends Component {

    @Element(required = false)
    private boolean pressed;
    // outer look definition
    @Element(required = false)
    private int outerWidth;
    @Element(required = false)
    private int outerHeight;
    @Element(required = false)
    private int border;
    @Element(required = false)
    private String outerColor;
    @Element(required = false)
    private String borderColor;
    // label definition
    @Element(required = false)
    private String label;
    @Element(required = false)
    private int labelSize;
    @Element(required = false)
    private String labelColor;
    // pressed outer look definition
    @Element(required = false)
    private String outerColorPressed;
    @Element(required = false)
    private String borderColorPressed;
    // pressed label definition
    @Element(required = false)
    private String labelColorPressed;
    private int[] actionArea;

    public boolean isPressed() {
        return pressed;
    }

    public void setPressed(boolean selected) {
        this.pressed = selected;
    }

    public int getOuterWidth() {
        return outerWidth;
    }

    public void setOuterWidth(int outerWidth) {
        this.outerWidth = outerWidth;
    }

    public int getOuterHeight() {
        return outerHeight;
    }

    public void setOuterHeight(int outerHeight) {
        this.outerHeight = outerHeight;
    }

    public int getBorder() {
        return border;
    }

    public void setBorder(int border) {
        this.border = border;
    }

    public String getOuterColor() {
        return outerColor;
    }

    public void setOuterColor(String outerColor) {
        this.outerColor = outerColor;
    }

    public String getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(String borderColor) {
        this.borderColor = borderColor;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getLabelSize() {
        return labelSize;
    }

    public void setLabelSize(int labelSize) {
        this.labelSize = labelSize;
    }

    public String getLabelColor() {
        return labelColor;
    }

    public void setLabelColor(String labelColor) {
        this.labelColor = labelColor;
    }

    public String getOuterColorPressed() {
        return outerColorPressed;
    }

    public void setOuterColorPressed(String outerColorPressed) {
        this.outerColorPressed = outerColorPressed;
    }

    public String getBorderColorPressed() {
        return borderColorPressed;
    }

    public void setBorderColorPressed(String borderColorPressed) {
        this.borderColorPressed = borderColorPressed;
    }

    public String getLabelColorPressed() {
        return labelColorPressed;
    }

    public void setLabelColorPressed(String labelColorPressed) {
        this.labelColorPressed = labelColorPressed;
    }

    @Override
    public int[] getActionArea() {

        if (actionArea == null) {
            int xMin = getPosX();
            int yMin = getPosY();
            int xMax = xMin + outerWidth;
            int yMax = yMin + outerHeight;

            actionArea = new int[]{xMin, yMin, xMax, yMax};
        }
        return actionArea;
    }
}
