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
    private int posy;
    private List<Text> texts;
    private Color color;
    // TODO to dole, dbat, aby bylo aktualni
    private int minx = -1;
    private int miny = -1;
    private int maxx = -1;
    private int maxy = -1;

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

    public void setLimits() {
        minx = posx;
        miny = posy;
        maxx = posx + width;
        maxy = posy + height;
//        System.out.println(minx + " " + miny + " " + maxx + " " + maxy);
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

    public int getMinx() {
        return minx;
    }

    public int getMaxx() {
        return maxx;
    }

    public int getMiny() {
        return miny;
    }

    public int getMaxy() {
        return maxy;
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
