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
public class DefaultComboBox extends Component {

    @Element(required = false)
    private boolean selected;
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
    // arrow definition (coordinates are: [x1, y1, x2, y2, x3, y3])
    @Element(required = false)
    private int[] arrowCoords;
    @Element(required = false)
    private String arrowColor;
    // content
    @Element
    private String[] values;
    @Element(required = false)
    private String valueColor;
    @Element(required = false)
    private int valueSize;
    @Element(required = false)
    private String selectedValue;
    // drop-down list
    @Element(required = false)
    private String downOddColor;
    @Element(required = false)
    private String downEvenColor;
    private int[] actionAreaSelected;
    private int[] actionAreaDeselected;

    public String getSelectedValue() {
        return selectedValue;
    }

    public void setSelectedValue(String selectedValue) {
        this.selectedValue = selectedValue;
    }

    public String getDownOddColor() {
        return downOddColor;
    }

    public void setDownOddColor(String downOddColor) {
        this.downOddColor = downOddColor;
    }

    public String getDownEvenColor() {
        return downEvenColor;
    }

    public void setDownEvenColor(String downEvenColor) {
        this.downEvenColor = downEvenColor;
    }

    public int getValueSize() {
        return valueSize;
    }

    public void setValueSize(int valueSize) {
        this.valueSize = valueSize;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
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

    public int[] getArrowCoords() {
        return arrowCoords;
    }

    public void setArrowCoords(int[] arrowCoords) {
        this.arrowCoords = arrowCoords;
    }

    public String getArrowColor() {
        return arrowColor;
    }

    public void setArrowColor(String arrowColor) {
        this.arrowColor = arrowColor;
    }

    public String[] getValues() {
        return values;
    }

    public void setValues(String[] values) {
        this.values = values;
    }

    public String getValueColor() {
        return valueColor;
    }

    public void setValueColor(String valueColor) {
        this.valueColor = valueColor;
    }

    @Override
    public int[] getActionArea() {

        if (selected) {
            if (actionAreaSelected == null) {
                // test, whether the event happens on drop-down list at all
                int xMin = getPosX();
                int yMin = getPosY();
                int xMax = xMin + outerWidth;
                int yMax = yMin + ((values.length + 1) * outerHeight);

                actionAreaSelected = new int[]{xMin, yMin, xMax, yMax};
            }
            return actionAreaSelected;
        } else {
            if(actionAreaDeselected == null) {
                int xMin = getPosX();
                int yMin = getPosY();
                int xMax = xMin + outerWidth;
                int yMax = yMin + outerHeight;
                
                actionAreaDeselected = new int[]{xMin, yMin, xMax, yMax};
            }
            return actionAreaDeselected;
        }
    }
}
