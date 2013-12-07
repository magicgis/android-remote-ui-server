/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.gui.bitmap;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author tomas.buk
 */
public class BitmapMixin {

    private static final Logger logger = Logger.getLogger(BitmapMixin.class.getName());

    /**
     * Converts length in mm to number of pixels with regards to dpi
     *
     * @param mm
     * @param dpi
     * @return
     */
    public static int getPixelCount(int mm, int dpi) {
        return (int) ((dpi * mm) / 25.4);
    }

    public static boolean intersects(int x, int y, int[] aabb) {

        if (x < aabb[0] || x > aabb[2]) {
            return false;
        }
        return (y >= aabb[1] && y <= aabb[3]);
    }

    /**
     * Converts given BufferedImage to byte array
     *
     * @param image BufferedImage, that is to be converted
     * @param format File format extension
     * @return byte array, that represents given image
     */
    public static byte[] toByteArray(BufferedImage image, String format) {

//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] out = null;
//        try {
//            ImageIO.write(image, format, baos);
//            baos.flush();
//            out = baos.toByteArray();
//            baos.close();
//        } catch (IOException ex) {
//            logger.log(Level.SEVERE, ex.getMessage());
//            throw new RuntimeException("Unable to convert image to byte array!");
//        }

        out = ((DataBufferByte) image.getData().getDataBuffer()).getData();

        return out;
    }

    /**
     * Converts given byte array to BufferedImage
     *
     * @param buffer Image buffer
     * @return BufferedImage object
     */
    public static BufferedImage toBufferedImage(byte[] buffer) {

        InputStream is = new ByteArrayInputStream(buffer);
        BufferedImage image = null;
        try {
            image = ImageIO.read(is);
        } catch (IOException ex) {
            logger.log(Level.SEVERE, ex.getMessage());
            throw new RuntimeException("Unable to convert byte array to BufferedImage!");
        }
        return image;
    }
}
