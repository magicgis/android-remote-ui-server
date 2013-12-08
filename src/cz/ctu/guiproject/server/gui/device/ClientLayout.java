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
import cz.ctu.guiproject.server.gui.entity.DefaultRadioButton;
import cz.ctu.guiproject.server.gui.entity.DefaultToggleButton;
import cz.ctu.guiproject.server.gui.entity.Layout;
import cz.ctu.guiproject.server.gui.loader.Loader;

/**
 *
 * @author tomas.buk
 */
public class ClientLayout {

    private Layout layout;
    private int dpi;

    public ClientLayout(int dpi) {
        this.dpi = dpi;
        initLayout();
    }

    public Component getIComponent(int x, int y) {
        for (Component comp : layout.getComponents()) {
            int[] actionArea = comp.getActionArea();
            if (BitmapMixin.intersects(x, y, actionArea)) {
                // decide, which component was intersected and adequately update component
                return comp;
            }
        }
        return null;
    }

    public Layout getLayout() {
        return layout;
    }

    private void initLayout() {
        layout = Loader.loadLayout();
        // load default components
        DefaultRadioButton defaultRadio = Loader.loadDefaultRadioButton();
        DefaultComboBox defaultCombo = Loader.loadDefaultComboBox();
        DefaultToggleButton defaultToggleButton = Loader.loadDefaultToggleButton();
        DefaultButton defaultButton = Loader.loadDefaultButton();
        DefaultFader defaultFader = Loader.loadDefaultFader();

        // add default components
        for (Component comp : layout.getComponents()) {

            comp.setPosX(BitmapMixin.getPixelCount(comp.getPosX(), dpi));
            comp.setPosY(BitmapMixin.getPixelCount(comp.getPosY(), dpi));

            if (comp instanceof DefaultRadioButton) {

                ((DefaultRadioButton) comp).setRenderable(defaultRadio.isRenderable());
                ((DefaultRadioButton) comp).setBorderColor(defaultRadio.getBorderColor());
                ((DefaultRadioButton) comp).setInnerColor(defaultRadio.getInnerColor());
                ((DefaultRadioButton) comp).setLabelColor(defaultRadio.getLabelColor());
                ((DefaultRadioButton) comp).setOuterColor(defaultRadio.getOuterColor());

                ((DefaultRadioButton) comp).setOuterDiameter(BitmapMixin.getPixelCount(defaultRadio.getOuterDiameter(), dpi));
                ((DefaultRadioButton) comp).setBorder(BitmapMixin.getPixelCount(defaultRadio.getBorder(), dpi));
                ((DefaultRadioButton) comp).setInnerDiameter(BitmapMixin.getPixelCount(defaultRadio.getInnerDiameter(), dpi));
                ((DefaultRadioButton) comp).setLabelSize(BitmapMixin.getPixelCount(defaultRadio.getLabelSize(), dpi));

            } else if (comp instanceof DefaultComboBox) {

                ((DefaultComboBox) comp).setRenderable(defaultCombo.isRenderable());
                ((DefaultComboBox) comp).setArrowColor(defaultCombo.getArrowColor());
                ((DefaultComboBox) comp).setBorderColor(defaultCombo.getBorderColor());
                ((DefaultComboBox) comp).setOuterColor(defaultCombo.getOuterColor());
                ((DefaultComboBox) comp).setValueColor(defaultCombo.getValueColor());
                ((DefaultComboBox) comp).setSelected(defaultCombo.isSelected());
                ((DefaultComboBox) comp).setDownOddColor(defaultCombo.getDownOddColor());
                ((DefaultComboBox) comp).setDownEvenColor(defaultCombo.getDownEvenColor());
                ((DefaultComboBox) comp).setSelectedValue(defaultCombo.getSelectedValue());

                ((DefaultComboBox) comp).setArrowCoords(BitmapMixin.getPixelArrCount(defaultCombo.getArrowCoords(), dpi));
                ((DefaultComboBox) comp).setBorder(BitmapMixin.getPixelCount(defaultCombo.getBorder(), dpi));
                ((DefaultComboBox) comp).setOuterHeight(BitmapMixin.getPixelCount(defaultCombo.getOuterHeight(), dpi));
                ((DefaultComboBox) comp).setOuterWidth(BitmapMixin.getPixelCount(defaultCombo.getOuterWidth(), dpi));
                ((DefaultComboBox) comp).setValueSize(BitmapMixin.getPixelCount(defaultCombo.getValueSize(), dpi));

            } else if (comp instanceof DefaultToggleButton) {

                ((DefaultToggleButton) comp).setRenderable(defaultToggleButton.isRenderable());
                ((DefaultToggleButton) comp).setBorderColor(defaultToggleButton.getBorderColor());
                ((DefaultToggleButton) comp).setBorderColorPressed(defaultToggleButton.getBorderColorPressed());
                ((DefaultToggleButton) comp).setInnerColor(defaultToggleButton.getInnerColor());
                ((DefaultToggleButton) comp).setInnerColorPressed(defaultToggleButton.getInnerColorPressed());
                ((DefaultToggleButton) comp).setLabelColor(defaultToggleButton.getLabelColor());
                ((DefaultToggleButton) comp).setLabelColorPressed(defaultToggleButton.getLabelColorPressed());

                ((DefaultToggleButton) comp).setBorder(BitmapMixin.getPixelCount(defaultToggleButton.getBorder(), dpi));
                ((DefaultToggleButton) comp).setLabelSize(BitmapMixin.getPixelCount(defaultToggleButton.getLabelSize(), dpi));
                ((DefaultToggleButton) comp).setOuterHeight(BitmapMixin.getPixelCount(defaultToggleButton.getOuterHeight(), dpi));
                ((DefaultToggleButton) comp).setOuterWidth(BitmapMixin.getPixelCount(defaultToggleButton.getOuterWidth(), dpi));

            } else if (comp instanceof DefaultButton) {

                ((DefaultButton) comp).setRenderable(defaultButton.isRenderable());
                ((DefaultButton) comp).setBorderColor(defaultButton.getBorderColor());
                ((DefaultButton) comp).setBorderColorPressed(defaultButton.getBorderColorPressed());
                ((DefaultButton) comp).setLabelColor(defaultButton.getLabelColor());
                ((DefaultButton) comp).setLabelColorPressed(defaultButton.getLabelColorPressed());
                ((DefaultButton) comp).setOuterColor(defaultButton.getOuterColor());
                ((DefaultButton) comp).setOuterColorPressed(defaultButton.getOuterColorPressed());

                ((DefaultButton) comp).setBorder(BitmapMixin.getPixelCount(defaultButton.getBorder(), dpi));
                ((DefaultButton) comp).setLabelSize(BitmapMixin.getPixelCount(defaultButton.getLabelSize(), dpi));
                ((DefaultButton) comp).setOuterWidth(BitmapMixin.getPixelCount(defaultButton.getOuterWidth(), dpi));
                ((DefaultButton) comp).setOuterHeight(BitmapMixin.getPixelCount(defaultButton.getOuterHeight(), dpi));

            } else if (comp instanceof DefaultFader) {
                DefaultFader f = (DefaultFader) comp;
                
                f.setRenderable(defaultFader.isRenderable());
                f.setBorderColor(defaultFader.getBorderColor());
                f.setCaretBorderColor(defaultFader.getCaretBorderColor());
                f.setCaretColor(defaultFader.getCaretColor());
                f.setOuterColor(defaultFader.getOuterColor());
                f.setStopperBorderColor(defaultFader.getBorderColor());
                f.setStopperColor(defaultFader.getStopperColor());
                
                f.setBorder(BitmapMixin.getPixelCount(defaultFader.getBorder(), dpi));
                f.setCaretBorder(BitmapMixin.getPixelCount(defaultFader.getCaretBorder(), dpi));
                f.setCaretHeight(BitmapMixin.getPixelCount(defaultFader.getCaretHeight(), dpi));
                f.setCaretWidth(BitmapMixin.getPixelCount(defaultFader.getCaretWidth(), dpi));
                f.setOuterHeight(BitmapMixin.getPixelCount(defaultFader.getOuterHeight(), dpi));
                f.setOuterWidth(BitmapMixin.getPixelCount(defaultFader.getOuterWidth(), dpi));
                f.setStopperBorder(BitmapMixin.getPixelCount(defaultFader.getStopperBorder(), dpi));
                f.setStopperHeight(BitmapMixin.getPixelCount(defaultFader.getStopperHeight(), dpi));
                f.setStopperWidth(BitmapMixin.getPixelCount(defaultFader.getStopperWidth(), dpi));
            }
        }
    }
}
