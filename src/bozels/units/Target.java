/*
 *  Copyright 2012 Stijn Seghers <stijn.seghers at ugent.be>.
 */

package bozels.units;

import bozels.configuration.unitsettings.UnitSettings;
import bozels.units.shapes.UnitShape;

/**
 * 
 * @author Stijn Seghers <stijn.seghers at ugent.be>
 */
public class Target extends Unit {
    
    public Target(float x, float y, UnitShape shape, UnitSettings settings) {
        super(x, y, 0, shape, settings);
    }
}
