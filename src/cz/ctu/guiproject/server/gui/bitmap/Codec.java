/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.gui.bitmap;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 *
 * @author tomique
 */
public class Codec {

    private static final Logger logger = Logger.getLogger(Codec.class.getName());

    public static String encodeToBase64(BufferedImage image, String format) {
        String imageString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            ImageIO.write(image, format, bos);
            byte[] imageBytes = bos.toByteArray();

            BASE64Encoder encoder = new BASE64Encoder();
            imageString = encoder.encode(imageBytes);

            bos.close();
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        return imageString;
    }

    // bude se odehravat na androidu
    public static BufferedImage decodeBase64(String imageString) {

        BufferedImage image = null;
        byte[] imageByte;
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            imageByte = decoder.decodeBuffer(imageString);
            try (ByteArrayInputStream bis = new ByteArrayInputStream(imageByte)) {
                image = ImageIO.read(bis);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        return image;
    }
}
