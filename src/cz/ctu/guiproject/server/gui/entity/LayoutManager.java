/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.gui.entity;

import cz.ctu.guiproject.server.gui.bitmap.BitmapMixin;
import cz.ctu.guiproject.server.gui.loader.Loader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author tomas.buk
 */
public class LayoutManager {

    private Layout layout;
    private static LayoutManager instance;
    private List<LayoutObserver> observers;
    private static final Logger logger = Logger.getLogger(LayoutManager.class.getName());
    // currently clicked component
    private Component component;

    private LayoutManager() {
        observers = new ArrayList<>();
        component = null;
        initLayout();
    }

    public static LayoutManager getInstance() {
        if (instance == null) {
            instance = new LayoutManager();
        }
        return instance;
    }

    public Layout getInitialLayout() {
        return layout;
    }

    public Component getComponent() {
        return component;
    }

    public void updateLayout(int x, int y) {
//        logger.log(Level.INFO, "update at: [" + x + ", " + y + "]");
        // iterate through all existing components and identify the selected one
        boolean redraw = false;
        for (Component comp : layout.getComponents()) {
            int[] actionArea = comp.getActionArea();
            if (BitmapMixin.intersects(x, y, actionArea)) {
                component = comp;
                redraw = true;
                if (comp instanceof DefaultRadioButton) {
                    if (((DefaultRadioButton) comp).isSelected()) {
                        ((DefaultRadioButton) comp).setSelected(false);
                        break;
                    } else {
                        ((DefaultRadioButton) comp).setSelected(true);
                        break;
                    }
                } else if (comp instanceof DefaultComboBox) {
                    if (((DefaultComboBox) comp).isSelected()) {
                        ((DefaultComboBox) comp).setSelected(false);

                        DefaultComboBox cb = (DefaultComboBox) comp;
                        // set selected value - find selected row

                        // form action area of each drop-down list value
                        int minX = cb.getPosX();
                        int minY = cb.getPosY();
                        int maxX = minX + cb.getOuterWidth();
                        int maxY = minY + cb.getOuterHeight();


                        for (int i = 0; i < cb.getValues().length; ++i) {
                            // form actionArea
                            minY = maxY;
                            maxY += cb.getOuterHeight();
                            int[] aabb = {minX, minY, maxX, maxY};
                            if (BitmapMixin.intersects(x, y, aabb)) {
                                ((DefaultComboBox) comp).setSelectedValue(cb.getValues()[i]);
                                break;
                            }
                        }
                        break;
                    } else {
                        ((DefaultComboBox) comp).setSelected(true);
                        break;
                    }
                } else if (comp instanceof DefaultToggleButton) {
                    if (((DefaultToggleButton) comp).isPressed()) {
                        ((DefaultToggleButton) comp).setPressed(false);
                        break;
                    } else {
                        ((DefaultToggleButton) comp).setPressed(true);
                        break;
                    }
                }
            }
        }
        if (redraw) {
            notifyObservers();
        } else {
            component = null;
        }
    }

    private void initLayout() {
        layout = Loader.loadLayout();
        // load default components
        DefaultRadioButton defaultRadio = Loader.loadDefaultRadioButton();
        DefaultComboBox defaultCombo = Loader.loadDefaultComboBox();
        DefaultToggleButton defaultToggleButton = Loader.loadDefaultToggleButton();
        // add default components
        for (Component comp : layout.getComponents()) {
            if (comp instanceof DefaultRadioButton) {
                // !!
                ((DefaultRadioButton) comp).setRenderable(defaultRadio.isRenderable());
                ((DefaultRadioButton) comp).setBorder(defaultRadio.getBorder());
                ((DefaultRadioButton) comp).setBorderColor(defaultRadio.getBorderColor());
                ((DefaultRadioButton) comp).setInnerColor(defaultRadio.getInnerColor());
                ((DefaultRadioButton) comp).setInnerDiameter(defaultRadio.getInnerDiameter());
                ((DefaultRadioButton) comp).setLabelColor(defaultRadio.getLabelColor());
                ((DefaultRadioButton) comp).setLabelSize(defaultRadio.getLabelSize());
                ((DefaultRadioButton) comp).setOuterColor(defaultRadio.getOuterColor());
                ((DefaultRadioButton) comp).setOuterDiameter(defaultRadio.getOuterDiameter());
            } else if (comp instanceof DefaultComboBox) {
                
                // !!
                ((DefaultComboBox) comp).setRenderable(defaultCombo.isRenderable());
                ((DefaultComboBox) comp).setArrowColor(defaultCombo.getArrowColor());
                ((DefaultComboBox) comp).setArrowCoords(defaultCombo.getArrowCoords());
                ((DefaultComboBox) comp).setBorder(defaultCombo.getBorder());
                ((DefaultComboBox) comp).setBorderColor(defaultCombo.getBorderColor());
                ((DefaultComboBox) comp).setOuterColor(defaultCombo.getOuterColor());
                ((DefaultComboBox) comp).setOuterHeight(defaultCombo.getOuterHeight());
                ((DefaultComboBox) comp).setOuterWidth(defaultCombo.getOuterWidth());
                ((DefaultComboBox) comp).setValueColor(defaultCombo.getValueColor());
                ((DefaultComboBox) comp).setValueSize(defaultCombo.getValueSize());
                ((DefaultComboBox) comp).setSelected(defaultCombo.isSelected());
                ((DefaultComboBox) comp).setDownOddColor(defaultCombo.getDownOddColor());
                ((DefaultComboBox) comp).setDownEvenColor(defaultCombo.getDownEvenColor());
                ((DefaultComboBox) comp).setDownRowHeight(defaultCombo.getDownRowHeight());
                ((DefaultComboBox) comp).setSelectedValue(defaultCombo.getSelectedValue());
            } else if (comp instanceof DefaultToggleButton) {
                
                // !
                ((DefaultToggleButton) comp).setRenderable(defaultToggleButton.isRenderable());
                ((DefaultToggleButton) comp).setBorder(defaultToggleButton.getBorder());
                ((DefaultToggleButton) comp).setBorderColor(defaultToggleButton.getBorderColor());
                ((DefaultToggleButton) comp).setBorderColorPressed(defaultToggleButton.getBorderColorPressed());
                ((DefaultToggleButton) comp).setInnerColor(defaultToggleButton.getInnerColor());
                ((DefaultToggleButton) comp).setInnerColorPressed(defaultToggleButton.getInnerColorPressed());
                ((DefaultToggleButton) comp).setLabelColor(defaultToggleButton.getLabelColor());
                ((DefaultToggleButton) comp).setLabelColorPressed(defaultToggleButton.getLabelColorPressed());
                ((DefaultToggleButton) comp).setLabelSize(defaultToggleButton.getLabelSize());
                ((DefaultToggleButton) comp).setOuterHeight(defaultToggleButton.getOuterHeight());
                ((DefaultToggleButton) comp).setOuterWidth(defaultToggleButton.getOuterWidth());
                ((DefaultToggleButton) comp).setPressed(defaultToggleButton.isPressed());
            }
        }
    }

    public void registerObserver(LayoutObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void removeObserver(LayoutObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (LayoutObserver observer : observers) {
            observer.update(layout);
        }
    }
}
