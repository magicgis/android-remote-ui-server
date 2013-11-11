/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.messaging;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 *
 * @author tomas.buk
 */
@Root
public class ClientInitRequestMessage extends AndroidMessage<ClientInitRequestMessage> {

    @Element
    private int screenWidth;
    @Element
    private int screenHeight;
    @Element
    private String name;

    /**
     * Default constructor
     */
    public ClientInitRequestMessage() {
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getXml() {
        return super.getXml(this);
    }
}
