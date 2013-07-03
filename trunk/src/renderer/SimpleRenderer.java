/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package renderer;

import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.List;
import main.ActionArea;
import main.Background;
import main.Component;
import main.Text;

/**
 *
 * @author tomique
 */
public class SimpleRenderer {

    public SimpleRenderer() {
//        init();
    }

    public static BufferedImage getRenderedComponent(Component component) {
        BufferedImage img = new BufferedImage(component.getWidth(), component.getHeight(), BufferedImage.TYPE_INT_RGB);

        Graphics2D g = img.createGraphics();

        // render action  areas
        List<ActionArea> actionAreas = component.getActionAreas();
        for (ActionArea area : actionAreas) {
            renderActionArea(area, img);
        }

        return img;
    }

    private static void renderActionArea(ActionArea area, BufferedImage img) {

        // render back color
        Graphics2D g = img.createGraphics();
        g.setColor(area.getColor());
        g.fillRect(area.getPosx(), area.getPosy(), area.getWidth(), area.getHeight());

        // render background
        renderBackground(area, img);

        // render content
        List<Text> texts = area.getTexts();
        for (Text text : texts) {
            renderText(area, text, img);
        }
    }

    private static void renderBackground(ActionArea area, BufferedImage img) {
        
        Background back = area.getBackground();
        Graphics2D g = img.createGraphics();
        g.setColor(back.getColor());

        switch (back.getStyle()) {
            case "solid":
                g.fillRect(area.getPosx(), area.getPosy(), area.getWidth(), area.getHeight());
                break;
            case "border":
                g.drawRect(area.getPosx(), area.getPosy(), area.getWidth() - 1, area.getHeight() - 1);
                break;
            case "border-dashed":
                Rectangle2D rect = new Rectangle2D.Float(area.getPosx() + 1,
                        area.getPosy() + 1,
                        area.getWidth() - 2,
                        area.getHeight() - 2);
                float[] dash = {5F, 5F};
                Stroke dashedStroke = new BasicStroke(2F, BasicStroke.CAP_SQUARE,
                        BasicStroke.JOIN_MITER, 3F, dash, 0F);
                g.fill(dashedStroke.createStrokedShape(rect));
                break;
            case "user":
                // TODO
                break;
        }
    }

    private static void renderText(ActionArea area, Text text, BufferedImage img) {

        Graphics2D g = img.createGraphics();
        Font font = text.getFont().getFont();
        g.setFont(font);

        FontMetrics fm = g.getFontMetrics(font);
        int x = 0;
        int y = fm.getAscent();

        g.setColor(text.getColor());
        g.drawString(text.getValue(), area.getPosx() + x + text.getPosx(), area.getPosy() + y + text.getPosy());
        g.dispose();

    }

    public static void main(String[] args) {
        new SimpleRenderer();
    }
}
