/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.gui.device;

import cz.ctu.guiproject.server.gui.bitmap.BitmapMixin;
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
 *
 * @author tomas.buk
 */
public class ClientLayout {

    private Layout metricLayout;
//    when painting, state information will be taken from state layout and position information will be taken from position layout
    private int dpi;

    public ClientLayout(int dpi) {
        this.dpi = dpi;
        initLayout();
    }

    public Component getIComponent(int x, int y) {
        for (Component comp : metricLayout.getComponents()) {
            int[] actionArea = comp.getActionArea();
            if (BitmapMixin.intersects(x, y, actionArea)) {
                // decide, which component was intersected and adequately update component
                return comp;
            }
        }
        return null;
    }
    
    public Component getMetricComponent(Component refComp) {
        for(Component comp : metricLayout.getComponents()) {
            if(comp.equals(refComp)) {
                return comp;
            }
        }
        throw new RuntimeException("Should never return null!!!");
    }

    public Layout getLayout() {
        return metricLayout;
    }

    private void initLayout() {
        metricLayout = Loader.loadLayout();

        DefaultRadioButton defaultRadio = Loader.loadDefaultRadioButton();
        DefaultComboBox defaultCombo = Loader.loadDefaultComboBox();
        DefaultToggleButton defaultToggleButton = Loader.loadDefaultToggleButton();
        DefaultButton defaultButton = Loader.loadDefaultButton();
        DefaultFader defaultFader = Loader.loadDefaultFader();
        DefaultLabel defaultLabel = Loader.loadDefaultLabel();

        for (Component comp : metricLayout.getComponents()) {
            if (comp instanceof DefaultRadioButton) {
                DefaultRadioButton r = (DefaultRadioButton) comp;

                // get from user-defined layout
                r.setPosX(BitmapMixin.getPixelCount(comp.getPosX(), dpi));
                r.setPosY(BitmapMixin.getPixelCount(comp.getPosY(), dpi));

                // get from default component
                r.setOuterDiameter(BitmapMixin.getPixelCount(defaultRadio.getOuterDiameter(), dpi));
                r.setBorder(BitmapMixin.getPixelCount(defaultRadio.getBorder(), dpi));
                r.setInnerDiameter(BitmapMixin.getPixelCount(defaultRadio.getInnerDiameter(), dpi));
                r.setLabelSize(BitmapMixin.getPixelCount(defaultRadio.getLabelSize(), dpi));
            } else if (comp instanceof DefaultComboBox) {
                DefaultComboBox c = (DefaultComboBox) comp;

                c.setPosX(BitmapMixin.getPixelCount(comp.getPosX(), dpi));
                c.setPosY(BitmapMixin.getPixelCount(comp.getPosY(), dpi));

                c.setArrowCoords(BitmapMixin.getPixelArrCount(defaultCombo.getArrowCoords(), dpi));
                c.setBorder(BitmapMixin.getPixelCount(defaultCombo.getBorder(), dpi));
                c.setOuterHeight(BitmapMixin.getPixelCount(defaultCombo.getOuterHeight(), dpi));
                c.setOuterWidth(BitmapMixin.getPixelCount(defaultCombo.getOuterWidth(), dpi));
                c.setValueSize(BitmapMixin.getPixelCount(defaultCombo.getValueSize(), dpi));

            } else if (comp instanceof DefaultToggleButton) {
                DefaultToggleButton t = (DefaultToggleButton) comp;

                t.setPosX(BitmapMixin.getPixelCount(comp.getPosX(), dpi));
                t.setPosY(BitmapMixin.getPixelCount(comp.getPosY(), dpi));

                t.setBorder(BitmapMixin.getPixelCount(defaultToggleButton.getBorder(), dpi));
                t.setLabelSize(BitmapMixin.getPixelCount(defaultToggleButton.getLabelSize(), dpi));
                t.setOuterHeight(BitmapMixin.getPixelCount(defaultToggleButton.getOuterHeight(), dpi));
                t.setOuterWidth(BitmapMixin.getPixelCount(defaultToggleButton.getOuterWidth(), dpi));

            } else if (comp instanceof DefaultButton) {
                DefaultButton b = (DefaultButton) comp;

                b.setPosX(BitmapMixin.getPixelCount(comp.getPosX(), dpi));
                b.setPosY(BitmapMixin.getPixelCount(comp.getPosY(), dpi));

                b.setBorder(BitmapMixin.getPixelCount(defaultButton.getBorder(), dpi));
                b.setLabelSize(BitmapMixin.getPixelCount(defaultButton.getLabelSize(), dpi));
                b.setOuterWidth(BitmapMixin.getPixelCount(defaultButton.getOuterWidth(), dpi));
                b.setOuterHeight(BitmapMixin.getPixelCount(defaultButton.getOuterHeight(), dpi));

            } else if (comp instanceof DefaultFader) {
                DefaultFader f = (DefaultFader) comp;

                f.setPosX(BitmapMixin.getPixelCount(comp.getPosX(), dpi));
                f.setPosY(BitmapMixin.getPixelCount(comp.getPosY(), dpi));

                f.setBorder(BitmapMixin.getPixelCount(defaultFader.getBorder(), dpi));
                f.setCaretBorder(BitmapMixin.getPixelCount(defaultFader.getCaretBorder(), dpi));
                f.setCaretHeight(BitmapMixin.getPixelCount(defaultFader.getCaretHeight(), dpi));
                f.setCaretWidth(BitmapMixin.getPixelCount(defaultFader.getCaretWidth(), dpi));
                f.setOuterHeight(BitmapMixin.getPixelCount(defaultFader.getOuterHeight(), dpi));
                f.setOuterWidth(BitmapMixin.getPixelCount(defaultFader.getOuterWidth(), dpi));
                f.setStopperBorder(BitmapMixin.getPixelCount(defaultFader.getStopperBorder(), dpi));
                f.setStopperHeight(BitmapMixin.getPixelCount(defaultFader.getStopperHeight(), dpi));
                f.setStopperWidth(BitmapMixin.getPixelCount(defaultFader.getStopperWidth(), dpi));

            } else if (comp instanceof DefaultRadioGroup) {
                DefaultRadioGroup g = (DefaultRadioGroup) comp;

                for (DefaultRadioButton r : g.getRadios()) {

                    r.setPosX(BitmapMixin.getPixelCount(r.getPosX(), dpi));
                    r.setPosY(BitmapMixin.getPixelCount(r.getPosY(), dpi));

                    r.setOuterDiameter(BitmapMixin.getPixelCount(defaultRadio.getOuterDiameter(), dpi));
                    r.setBorder(BitmapMixin.getPixelCount(defaultRadio.getBorder(), dpi));
                    r.setInnerDiameter(BitmapMixin.getPixelCount(defaultRadio.getInnerDiameter(), dpi));
                    r.setLabelSize(BitmapMixin.getPixelCount(defaultRadio.getLabelSize(), dpi));
                }
            } else if(comp instanceof DefaultLabel) {
                DefaultLabel l = (DefaultLabel) comp;
                
                l.setPosX(BitmapMixin.getPixelCount(comp.getPosX(), dpi));
                l.setPosY(BitmapMixin.getPixelCount(comp.getPosY(), dpi));
                
                l.setLabelSize(BitmapMixin.getPixelCount(defaultLabel.getLabelSize(), dpi));
            }
        }
    }
}
