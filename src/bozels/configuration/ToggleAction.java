/*
 *  Copyright 2012 Stijn Seghers <stijn.seghers at ugent.be>.
 */

package bozels.configuration;

import javax.swing.AbstractAction;
import javax.swing.Action;

/**
 * 
 * @author Stijn Seghers <stijn.seghers at ugent.be>
 */
public abstract class ToggleAction extends AbstractAction {
    public ToggleAction(String name) {
        super(name);
        putValue(Action.SELECTED_KEY, "DavyEnNicoFTW!");
    }
}
