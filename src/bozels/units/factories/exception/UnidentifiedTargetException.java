/*
 *  Copyright 2012 Stijn Seghers <stijn.seghers at ugent.be>.
 */

package bozels.units.factories.exception;

/**
 * 
 * @author Stijn Seghers <stijn.seghers at ugent.be>
 */
public class UnidentifiedTargetException extends FactoryException {
    public UnidentifiedTargetException(String type) {
        super("een '" + type + "' doel");
    }
}
