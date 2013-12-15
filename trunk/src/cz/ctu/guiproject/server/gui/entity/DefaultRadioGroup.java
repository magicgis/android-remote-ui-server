/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.gui.entity;

import java.util.List;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

/**
 *
 * @author tomas.buk
 */
@Root
public class DefaultRadioGroup extends Component {

    @ElementList
    private List<DefaultRadioButton> radios;
    @Element(required = false)
    private DefaultRadioButton selectedRadio;
    private int[] actionArea;

    public DefaultRadioGroup() {
    }

    public List<DefaultRadioButton> getRadios() {
        return radios;
    }

    public void setRadios(List<DefaultRadioButton> radios) {
        this.radios = radios;
    }

    public DefaultRadioButton getSelectedRadio() {
        return selectedRadio;
    }

    public void setSelectedRadio(DefaultRadioButton selectedRadio) {
        this.selectedRadio = selectedRadio;
    }

    private void setActionArea() {
        // define aabb of the whole radio group
        /*important is, that the whole group of radio buttons is grouped in space 
         * as well! It will not be possible to correctly identify the source
         * component, when different component will be placed "between" existing
         * group.
         */
        int xMin = Integer.MAX_VALUE;
        int yMin = Integer.MAX_VALUE;
        int xMax = Integer.MIN_VALUE;
        int yMax = Integer.MIN_VALUE;

        for (DefaultRadioButton radio : radios) {
            int[] tmp = radio.getActionArea();
            xMin = (tmp[0] < xMin) ? tmp[0] : xMin;
            yMin = (tmp[1] < yMin) ? tmp[1] : yMin;
            xMax = (tmp[2] > xMax) ? tmp[2] : xMax;
            yMax = (tmp[3] > yMax) ? tmp[3] : yMax;
        }
        if (xMin == Integer.MAX_VALUE || yMin == Integer.MAX_VALUE || xMax == Integer.MIN_VALUE || yMax == Integer.MIN_VALUE) {
            throw new RuntimeException("Exception that should never be thrown, was thrown!");
        }
        actionArea = new int[]{xMin, yMin, xMax, yMax};
    }

    @Override
    public int[] getActionArea() {
        if (actionArea == null) {
            setActionArea();
        }
        return actionArea;
    }
}
