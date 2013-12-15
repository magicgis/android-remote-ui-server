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
public class ClientInitResponseMessage extends AndroidMessage<ClientInitResponseMessage> {

    @Element
    private String context;
    @Element
    private String format;
    @Element(required = false)
    private int[] updateArea;

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int[] getUpdateArea() {
        return updateArea;
    }

    public void setUpdateArea(int[] updateArea) {
        this.updateArea = updateArea;
    }

    @Override
    public String getXml() {
        return super.getXml(this);
    }
}
