/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.helper;

/**
 *
 * @author tomas.buk
 */
public class IdParser {

    public static int getNetworkId(String xml) {
        int a0 = xml.indexOf("[");
        int a1 = xml.indexOf("]");
        return Integer.parseInt(xml.substring(a0 + 1, a1));
    }

    public static String getSessionId(String xml) {
        String begin = "<sessionId>";
        int a0 = xml.indexOf(begin);
        int a1 = xml.indexOf("</sessionId>");
        return xml.substring(a0 + begin.length(), a1);
    }
}
