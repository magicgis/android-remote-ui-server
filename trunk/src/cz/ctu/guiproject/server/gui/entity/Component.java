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
public abstract class Component {

    @Element
    private int posX;
    @Element
    private int posY;

    /**
     * Returns x position of the component
     *
     * @return x position of the component
     */
    public int getPosX() {
        return posX;
    }

    /**
     * Sets the current x position of the component
     *
     * @param posX current x position of the component to be set
     */
    public void setPosX(int posX) {
        this.posX = posX;
    }

    /**
     * Returns current y position of the component
     *
     * @return current y position of the component
     */
    public int getPosY() {
        return posY;
    }

    /**
     * Sets the current y position of the component
     *
     * @param posY current y position of the component
     */
    public void setPosY(int posY) {
        this.posY = posY;
    }

    /**
     * Returns x_min, y_min and x_max, y_max coordinates, that uniquely identify
     * the action area
     *
     * @return array[x_min, y_min, x_max, y_max]
     */
    public abstract int[] getActionArea();
}
