/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.observers;

import cz.ctu.guiproject.server.events.ClickEvent;

/**
 *
 * @author tomas.buk
 */
public interface ClickObserver extends EventObserver<ClickEvent> {
    // TODO tady se pravdepodobne uzije generika (lower/upper bound)
}
