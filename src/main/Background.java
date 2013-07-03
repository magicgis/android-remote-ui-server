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
public class Background {

    private String style;
    private String userSrc;
    private Color color;

    public Color getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = Color.decode(color);
    }

    public String getUserSrc() {
        return userSrc;
    }

    public void setUserSrc(String userSrc) {
        if (!style.equals("user")) {
            throw new RuntimeException("userSrc set, but background style not set to \"user\"");
        }
        this.userSrc = userSrc;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("background [style: ").append(style);
        if (style.equals("user")) {
            sb.append(", src: ").append(userSrc);
        }
        sb.append("]");

        return sb.toString();
    }
}
