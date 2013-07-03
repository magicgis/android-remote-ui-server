/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tomique
 */
public class Component {

    private List<ActionArea> actionAreas;
    private String id;
    private int width;
    private int height;

    public Component() {
        actionAreas = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void addActionArea(ActionArea area) {
        actionAreas.add(area);
    }

    public List<ActionArea> getActionAreas() {
        return actionAreas;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Component\t");

        sb.append(" id: ").append(id);
        sb.append(", width: ").append(width);
        sb.append(", height: ").append(height);
        
        for (ActionArea area : actionAreas) {
            sb.append("\n\t\t");
            sb.append("[");
            sb.append(area);
            sb.append("]");
        }


        return sb.toString();
    }
}
