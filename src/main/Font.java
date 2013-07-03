/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author tomique
 */
public class Font {

    private String name;
    private String style;
    private int size;
    private java.awt.Font font;
    
    public Font() {
        // TODO default constructor
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }
    
    public void setFont() {
        font = java.awt.Font.decode(name + "-" + style + "-" + size);
    }
    
    public java.awt.Font getFont() {
        return font;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("font [name: ").append(name);
        sb.append(", style: ").append(style);
        sb.append(", size: ").append(size);
        sb.append("]");
        
        return sb.toString();
    }
}
