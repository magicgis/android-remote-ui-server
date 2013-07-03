/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package android_toolkit;

import entity.Resolution;

/**
 *
 * @author tomique
 */
public class AndroidDevice {

    private Resolution resolution;
    private String model;
    private String manufacturer;

    // TODO informace o poskytnutych sluzbach (gyro, dotykovy displej, ...)
    public AndroidDevice() {
    }

    /*
     * GETTERS
     */
    /**
     * Returns the device manufacturer
     *
     * @return the device manufacturer
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * Returns the model name
     *
     * @return The model name
     */
    public String getModel() {
        return model;
    }

    /**
     * Returns the resolution of the device
     *
     * @return The resolution of the device
     */
    public Resolution getResolution() {
        return resolution;
    }
    
    /*
     * SETTERS
     */
    public void setResolution(Resolution resolution) {
        this.resolution = resolution;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
    
}
