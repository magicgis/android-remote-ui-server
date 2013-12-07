/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.gui.entity;

import org.simpleframework.xml.Element;

/**
 *
 * @author tomas.buk
 */
public class DefaultToggleButton extends Component {

    @Element(required = false)
    private boolean pressed;
    @Element(required = false)
    private int outerWidth;
    @Element(required = false)
    private int outerHeight;
    @Element(required = false)
    private int border;
    @Element(required = false)
    private String label;
    @Element(required = false)
    private int labelSize;
    @Element(required = false)
    private String borderColor;
    @Element(required = false)
    private String innerColor;
    @Element(required = false)
    private String labelColor;
    ////////////////////////////////////
    @Element(required = false)
    private String borderColorPressed;
    @Element(required = false)
    private String innerColorPressed;
    @Element(required = false)
    private String labelColorPressed;

    public int getLabelSize() {
        return labelSize;
    }

    public void setLabelSize(int labelSize) {
        this.labelSize = labelSize;
    }

    public boolean isPressed() {
        return pressed;
    }

    public void setPressed(boolean pressed) {
        this.pressed = pressed;
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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(String borderColor) {
        this.borderColor = borderColor;
    }

    public String getInnerColor() {
        return innerColor;
    }

    public void setInnerColor(String innerColor) {
        this.innerColor = innerColor;
    }

    public String getLabelColor() {
        return labelColor;
    }

    public void setLabelColor(String labelColor) {
        this.labelColor = labelColor;
    }

    public String getBorderColorPressed() {
        return borderColorPressed;
    }

    public void setBorderColorPressed(String borderColorPressed) {
        this.borderColorPressed = borderColorPressed;
    }

    public String getInnerColorPressed() {
        return innerColorPressed;
    }

    public void setInnerColorPressed(String innerColorPressed) {
        this.innerColorPressed = innerColorPressed;
    }

    public String getLabelColorPressed() {
        return labelColorPressed;
    }

    public void setLabelColorPressed(String labelColorPressed) {
        this.labelColorPressed = labelColorPressed;
    }

    @Override
    public int[] getActionArea() {
        int xMin = getPosX();
        int yMin = getPosY();
        int xMax = xMin + outerWidth;
        int yMax = yMin + outerHeight;

        int[] actionArea = {xMin, yMin, xMax, yMax};
        return actionArea;
    }
}
