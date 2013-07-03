/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tomique
 */
public class ActionArea {

    private String id;
    private Background background;
    private int width;
    private int height;
    private int posx;
    private List<Text> texts;
    private Color color;

    public Color getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = Color.decode(color);
    }

    public ActionArea() {
        texts = new ArrayList<>();
    }

    public int getPosx() {
        return posx;
    }

    public void setPosx(int posx) {
        this.posx = posx;
    }

    public int getPosy() {
        return posy;
    }

    public void setPosy(int posy) {
        this.posy = posy;
    }
    private int posy;

    public String getId() {
        return id;
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

    public void setId(String id) {
        this.id = id;
    }

    public Background getBackground() {
        return background;
    }

    public void setBackground(Background background) {
        this.background = background;
    }

    public void addText(Text text) {
        texts.add(text);
    }
    
    public List<Text> getTexts() {
        return texts;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("id: ").append(id);
        sb.append(", width: ").append(width);
        sb.append(", height: ").append(height);
        sb.append(", posx: ").append(posx);
        sb.append(", posy: ").append(posy);
        sb.append(", ").append(background);

        for (Text text : texts) {
            sb.append(", ").append(text);
        }

        return sb.toString();
    }
}
