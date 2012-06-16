/*
 *  Copyright 2012 Stijn Seghers <stijn.seghers at ugent.be>.
 */

package bozels.units.factories.exception;

/**
 * 
 * @author Stijn Seghers <stijn.seghers at ugent.be>
 */
public class FactoryException extends Exception {
    protected FactoryException(String item) {
        super("Er werd geprobeerd " + item + " aan te maken.");
    }
}
