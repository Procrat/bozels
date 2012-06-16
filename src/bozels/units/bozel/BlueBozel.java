/*
 *  Copyright 2012 Stijn Seghers <stijn.seghers at ugent.be>.
 */

package bozels.units.bozel;

import bozels.configuration.unitsettings.UnitSettings;
import bozels.game.UnitsHandler;
import bozels.units.shapes.Circle;

/**
 * 
 * @author Stijn Seghers <stijn.seghers at ugent.be>
 */
public class BlueBozel extends Bozel {
    
    public BlueBozel(float x, float y) {
        super(x, y, new Circle(true, 1), UnitSettings.getInstance("BlueBozel"));
    }

    @Override
    public void doSpecialAction(UnitsHandler units) {}
}
