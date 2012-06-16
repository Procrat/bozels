/*
 *  Copyright 2012 Stijn Seghers <stijn.seghers at ugent.be>.
 */

package bozels.units.factories.exception;

/**
 * 
 * @author Stijn Seghers <stijn.seghers at ugent.be>
 */
public class UnidentifiedBozelException extends FactoryException {

    public UnidentifiedBozelException(String type) {
        super("een '" + type + "' bozel");
    }
}
