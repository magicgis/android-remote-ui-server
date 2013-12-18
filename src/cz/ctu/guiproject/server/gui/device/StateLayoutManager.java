/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.gui.device;

import cz.ctu.guiproject.server.gui.entity.Component;
import cz.ctu.guiproject.server.gui.entity.DefaultButton;
import cz.ctu.guiproject.server.gui.entity.DefaultComboBox;
import cz.ctu.guiproject.server.gui.entity.DefaultFader;
import cz.ctu.guiproject.server.gui.entity.DefaultLabel;
import cz.ctu.guiproject.server.gui.entity.DefaultRadioButton;
import cz.ctu.guiproject.server.gui.entity.DefaultRadioGroup;
import cz.ctu.guiproject.server.gui.entity.DefaultToggleButton;
import cz.ctu.guiproject.server.gui.entity.Layout;
import cz.ctu.guiproject.server.gui.loader.Loader;

/**
 * All changes made on layout are propagated into this manager, so that newly
 * connected clients would get the most current layout
 *
 * @author tomas.buk
 */
public class StateLayoutManager {

    private static StateLayoutManager instance;
    private Layout stateLayout;

    private StateLayoutManager() {
        initLayout();
    }

    public static StateLayoutManager getInstance() {
        if (instance == null) {
            instance = new StateLayoutManager();
        }
        return instance;
    }

    public Layout getStateLayout() {
        return stateLayout;
    }

    private void initLayout() {
        stateLayout = Loader.loadLayout();
        // load default components
        DefaultRadioButton defaultRadio = Loader.loadDefaultRadioButton();
        DefaultComboBox defaultCombo = Loader.loadDefaultComboBox();
        DefaultToggleButton defaultToggleButton = Loader.loadDefaultToggleButton();
        DefaultButton defaultButton = Loader.loadDefaultButton();
        DefaultFader defaultFader = Loader.loadDefaultFader();
        DefaultLabel defaultLabel = Loader.loadDefaultLabel();

        // add default components
        for (Component comp : stateLayout.getComponents()) {

            if (comp instanceof DefaultRadioButton) {

                DefaultRadioButton r = (DefaultRadioButton) comp;

                r.setRenderable(defaultRadio.isRenderable());
                r.setBorderColor(defaultRadio.getBorderColor());
                r.setInnerColor(defaultRadio.getInnerColor());
                r.setLabelColor(defaultRadio.getLabelColor());
                r.setOuterColor(defaultRadio.getOuterColor());

            } else if (comp instanceof DefaultComboBox) {

                DefaultComboBox c = (DefaultComboBox) comp;

                c.setRenderable(defaultCombo.isRenderable());
                c.setArrowColor(defaultCombo.getArrowColor());
                c.setBorderColor(defaultCombo.getBorderColor());
                c.setOuterColor(defaultCombo.getOuterColor());
                c.setValueColor(defaultCombo.getValueColor());
                c.setSelected(defaultCombo.isSelected());
                c.setDownOddColor(defaultCombo.getDownOddColor());
                c.setDownEvenColor(defaultCombo.getDownEvenColor());
                c.setSelectedValue(defaultCombo.getSelectedValue());

            } else if (comp instanceof DefaultToggleButton) {

                DefaultToggleButton t = (DefaultToggleButton) comp;

                t.setRenderable(defaultToggleButton.isRenderable());
                t.setBorderColor(defaultToggleButton.getBorderColor());
                t.setBorderColorPressed(defaultToggleButton.getBorderColorPressed());
                t.setInnerColor(defaultToggleButton.getInnerColor());
                t.setInnerColorPressed(defaultToggleButton.getInnerColorPressed());
                t.setLabelColor(defaultToggleButton.getLabelColor());
                t.setLabelColorPressed(defaultToggleButton.getLabelColorPressed());

            } else if (comp instanceof DefaultButton) {

                DefaultButton b = (DefaultButton) comp;

                b.setRenderable(defaultButton.isRenderable());
                b.setBorderColor(defaultButton.getBorderColor());
                b.setBorderColorPressed(defaultButton.getBorderColorPressed());
                b.setLabelColor(defaultButton.getLabelColor());
                b.setLabelColorPressed(defaultButton.getLabelColorPressed());
                b.setOuterColor(defaultButton.getOuterColor());
                b.setOuterColorPressed(defaultButton.getOuterColorPressed());

            } else if (comp instanceof DefaultFader) {
                DefaultFader f = (DefaultFader) comp;

                f.setRenderable(defaultFader.isRenderable());
                f.setBorderColor(defaultFader.getBorderColor());
                f.setCaretBorderColor(defaultFader.getCaretBorderColor());
                f.setCaretColor(defaultFader.getCaretColor());
                f.setOuterColor(defaultFader.getOuterColor());
                f.setStopperBorderColor(defaultFader.getBorderColor());
                f.setStopperColor(defaultFader.getStopperColor());

            } else if (comp instanceof DefaultRadioGroup) {
                DefaultRadioGroup g = (DefaultRadioGroup) comp;

                for (DefaultRadioButton radio : g.getRadios()) {

                    radio.setRenderable(defaultRadio.isRenderable());
                    radio.setBorderColor(defaultRadio.getBorderColor());
                    radio.setInnerColor(defaultRadio.getInnerColor());
                    radio.setLabelColor(defaultRadio.getLabelColor());
                    radio.setOuterColor(defaultRadio.getOuterColor());
                }
            } else if(comp instanceof DefaultLabel) {
                DefaultLabel l = (DefaultLabel) comp;
                
                l.setLabelColor(defaultLabel.getLabelColor());
            }
        }
    }
}
