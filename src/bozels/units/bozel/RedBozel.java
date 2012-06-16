/*
 *  Copyright 2012 Stijn Seghers <stijn.seghers at ugent.be>.
 */
package bozels.units.bozel;

import bozels.configuration.unitsettings.UnitSettings;
import bozels.game.UnitsHandler;
import bozels.units.shapes.Triangle;

/**
 *
 * @author Stijn Seghers <stijn.seghers at ugent.be>
 */
public class RedBozel extends Bozel {
    
    public RedBozel(float x, float y) {
        super(x, y, new Triangle(false, 2), UnitSettings.getInstance("RedBozel"));
    }

    @Override
    public void doSpecialAction(UnitsHandler units) {}
}
