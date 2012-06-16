/*
 *  Copyright 2012 Stijn Seghers <stijn.seghers at ugent.be>.
 */
package bozels.units.bozel;

import bozels.configuration.unitsettings.UnitSettings;
import bozels.game.UnitsHandler;
import bozels.units.shapes.Rectangle;
import org.jbox2d.common.Vec2;

/**
 *
 * @author Stijn Seghers <stijn.seghers at ugent.be>
 */
public class YellowBozel extends Bozel {

    public YellowBozel(float x, float y) {
        super(x, y, new Rectangle(true, 2, 1), UnitSettings.getInstance("YellowBozel"));
    }

    @Override
    public void doSpecialAction(UnitsHandler units) {
        Vec2 oldVel = getBody().getLinearVelocity();
        getBody().setLinearVelocity(oldVel.mul(3));
    }
}
