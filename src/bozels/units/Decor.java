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
public class Decor extends Unit {
    
    public Decor(float x, float y, float angle, UnitShape shape, UnitSettings settings) {
        super(x, y, angle, shape, settings);
    }
}
