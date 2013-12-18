/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.gui.entity;

import java.util.Objects;
import org.simpleframework.xml.Element;

/**
 *
 * @author tomas.buk
 */
public abstract class Component {

    @Element(required = false)
    private boolean renderable;
    @Element
    private int posX;
    @Element
    private int posY;
    @Element
    private String name;

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
     * Returns the name of the component
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Sets then name of the component
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns true, if the component is renderable
     *
     * @return
     */
    public boolean isRenderable() {
        return renderable;
    }

    /**
     * Sets the renderability of the component
     *
     * @param renderable
     */
    public void setRenderable(boolean renderable) {
        this.renderable = renderable;
    }

    /**
     * Returns x_min, y_min and x_max, y_max coordinates, that uniquely identify
     * the action area
     *
     * @return array[x_min, y_min, x_max, y_max]
     */
    public abstract int[] getActionArea();

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + (this.renderable ? 1 : 0);
        hash = 31 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Component other = (Component) obj;
//        if (this.renderable != other.renderable) {
//            return false;
//        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("renderable: ").append(renderable).append(", name: ").append(name);
        return sb.toString();
    }
}
