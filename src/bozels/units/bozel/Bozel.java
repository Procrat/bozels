/*
 *  Copyright 2012 Stijn Seghers <stijn.seghers at ugent.be>.
 */

package bozels.units.bozel;

import bozels.configuration.unitsettings.UnitSettings;
import bozels.game.UnitsHandler;
import bozels.units.Unit;
import bozels.units.shapes.UnitShape;
import org.jbox2d.common.Vec2;

/**
 * 
 * @author Stijn Seghers <stijn.seghers at ugent.be>
 */
public abstract class Bozel extends Unit {
    protected Bozel(float x, float y, UnitShape shape, UnitSettings settings) {
        super(x, y, 0, shape, settings);
    }
    
    public abstract void doSpecialAction(UnitsHandler units);
    
    public void setActive(boolean active) {
        getBody().setActive(active);
    }

    public void launch(Vec2 launchVec) {
        getBody().applyLinearImpulse(launchVec, getPosition());
    }
}
