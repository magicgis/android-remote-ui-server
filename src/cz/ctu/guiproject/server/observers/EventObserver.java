/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.observers;

import cz.ctu.guiproject.server.events.AndroidEvent;

/**
 *
 * @author tomas.buk
 */
public interface EventObserver<T extends AndroidEvent> {
    // TODO tady se pravdepodobne uzije generika (lower/upper bound

    public void update(T event);
}
