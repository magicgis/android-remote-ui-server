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
public class Layout {

    @Element
    private String background;
    @ElementList
    private List<Component> components;

    public Layout() {
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public List<Component> getComponents() {
        return components;
    }

    public void setComponents(List<Component> components) {
        this.components = components;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Component[background: ").append(background).append("]");

        return sb.toString();
    }
}
