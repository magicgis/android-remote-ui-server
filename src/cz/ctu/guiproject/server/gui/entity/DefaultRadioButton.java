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
public class DefaultRadioButton extends Component {

    @Element(required=false)
    private int outerDiameter;
    @Element(required=false)
    private int border;
    @Element(required=false)
    private int innerDiameter;
    @Element(required=false)
    private String label;
    @Element(required=false)
    private String borderColor;
    @Element(required=false)
    private String outerColor;
    @Element(required=false)
    private String innerColor;
    @Element(required=false)
    private String labelColor;
    @Element(required=false)
    private int labelSize;

    public String getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(String borderColor) {
        this.borderColor = borderColor;
    }

    public String getOuterColor() {
        return outerColor;
    }

    public void setOuterColor(String outerColor) {
        this.outerColor = outerColor;
    }

    public String getInnerColor() {
        return innerColor;
    }

    public void setInnerColor(String innerColor) {
        this.innerColor = innerColor;
    }

    public int getOuterDiameter() {
        return outerDiameter;
    }

    public void setOuterDiameter(int outerDiameter) {
        this.outerDiameter = outerDiameter;
    }

    public int getBorder() {
        return border;
    }

    public void setBorder(int border) {
        this.border = border;
    }

    public int getInnerDiameter() {
        return innerDiameter;
    }

    public void setInnerDiameter(int innerDiameter) {
        this.innerDiameter = innerDiameter;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLabelColor() {
        return labelColor;
    }

    public void setLabelColor(String labelColor) {
        this.labelColor = labelColor;
    }

    public int getLabelSize() {
        return labelSize;
    }

    public void setLabelSize(int labelSize) {
        this.labelSize = labelSize;
    }
}
