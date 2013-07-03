/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Color;

/**
 *
 * @author tomique
 */
public class Text {

    private String value;
    private int posx;
    private int posy;
    private Font font;
    private Color color;
    
    public Text() {
        // TODO default constructor
    }

    public Color getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = Color.decode(color);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("text [value: ").append(value);
        sb.append(", posx: ").append(posx);
        sb.append(", posy: ").append(posy);
        sb.append(", font: ").append(font);
        sb.append(", color: ").append(color);
        sb.append("]");
        
        return sb.toString();
    }
}
