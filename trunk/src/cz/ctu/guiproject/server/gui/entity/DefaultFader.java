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
public class DefaultFader extends Component {

    // outer space definition
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
    // stopper definition
    @Element(required = false)
    private int stopperWidth;
    @Element(required = false)
    private int stopperHeight;
    @Element(required = false)
    private int stopperBorder;
    @Element(required = false)
    private String stopperColor;
    @Element(required = false)
    private String stopperBorderColor;
    // caret definition
    @Element(required = false)
    private int caretWidth;
    @Element(required = false)
    private int caretHeight;
    @Element(required = false)
    private int caretBorder;
    @Element(required = false)
    private String caretColor;
    @Element(required = false)
    private String caretBorderColor;
    // 0 - 100
    @Element(required = false)
    private int caretPosition;
    @Element(required = false)
    private boolean verticalOrientation;
    //
    private int[] actionArea;

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

    public int getStopperWidth() {
        return stopperWidth;
    }

    public void setStopperWidth(int stopperWidth) {
        this.stopperWidth = stopperWidth;
    }

    public int getStopperHeight() {
        return stopperHeight;
    }

    public void setStopperHeight(int stopperHeight) {
        this.stopperHeight = stopperHeight;
    }

    public int getStopperBorder() {
        return stopperBorder;
    }

    public void setStopperBorder(int stopperBorder) {
        this.stopperBorder = stopperBorder;
    }

    public String getStopperColor() {
        return stopperColor;
    }

    public void setStopperColor(String stopperColor) {
        this.stopperColor = stopperColor;
    }

    public String getStopperBorderColor() {
        return stopperBorderColor;
    }

    public void setStopperBorderColor(String stopperBorderColor) {
        this.stopperBorderColor = stopperBorderColor;
    }

    public int getCaretWidth() {
        return caretWidth;
    }

    public void setCaretWidth(int caretWidth) {
        this.caretWidth = caretWidth;
    }

    public int getCaretHeight() {
        return caretHeight;
    }

    public void setCaretHeight(int caretHeight) {
        this.caretHeight = caretHeight;
    }

    public int getCaretBorder() {
        return caretBorder;
    }

    public void setCaretBorder(int caretBorder) {
        this.caretBorder = caretBorder;
    }

    public String getCaretColor() {
        return caretColor;
    }

    public void setCaretColor(String caretColor) {
        this.caretColor = caretColor;
    }

    public String getCaretBorderColor() {
        return caretBorderColor;
    }

    public void setCaretBorderColor(String caretBorderColor) {
        this.caretBorderColor = caretBorderColor;
    }

    public boolean isVerticalOrientation() {
        return verticalOrientation;
    }

    public void setVerticalOrientation(boolean verticalOrientation) {
        this.verticalOrientation = verticalOrientation;
    }

    public int getCaretPosition() {
        return caretPosition;
    }

    public void setCaretPosition(int caretPosition) {
        this.caretPosition = caretPosition;
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
