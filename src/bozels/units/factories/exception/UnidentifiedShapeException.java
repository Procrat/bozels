/*
 *  Copyright 2012 Stijn Seghers <stijn.seghers at ugent.be>.
 */

package bozels.units.factories.exception;

/**
 * 
 * @author Stijn Seghers <stijn.seghers at ugent.be>
 */
public class UnidentifiedShapeException extends FactoryException {
    
    public UnidentifiedShapeException(String type) {
        super("een '" + type + "' (vorm) decorstuk");
    }
}
