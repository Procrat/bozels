/*
 *  Copyright 2012 Stijn Seghers <stijn.seghers at ugent.be>.
 */

package bozels.units.factories;

import org.jdom.Element;

/**
 * 
 * @author Stijn Seghers <stijn.seghers at ugent.be>
 */
public abstract class PieceFactory {
    protected static float getAttFloat(Element el, String attName) {
        return Float.parseFloat(el.getAttributeValue(attName));
    }
}
