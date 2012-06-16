/*
 *  Copyright 2012 Stijn Seghers <stijn.seghers at ugent.be>.
 */

package bozels.units.factories.exception;

/**
 * 
 * @author Stijn Seghers <stijn.seghers at ugent.be>
 */
public class UnidentifiedMaterialException extends FactoryException {
    
    public UnidentifiedMaterialException(String type) {
        super("'" + type + "' (materiaal)");
    }
}
