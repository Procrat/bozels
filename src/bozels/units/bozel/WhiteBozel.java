/*
 *  Copyright 2012 Stijn Seghers <stijn.seghers at ugent.be>.
 */
package bozels.units.bozel;

import bozels.configuration.unitsettings.UnitSettings;
import bozels.game.UnitsHandler;
import bozels.game.explosion.ExplosionHandler;
import bozels.units.shapes.Rectangle;

/**
 *
 * @author Stijn Seghers <stijn.seghers at ugent.be>
 */
public class WhiteBozel extends Bozel {
    private final ExplosionHandler explosionHandler;

    public WhiteBozel(float x, float y, ExplosionHandler explosionHandler) {
        super(x, y, new Rectangle(true, 2, 2), UnitSettings.getInstance("WhiteBozel"));
        this.explosionHandler = explosionHandler;
    }

    @Override
    public void doSpecialAction(UnitsHandler units) {
        explosionHandler.explode(getBody().getWorld(), this);
        units.setUnitToBeDestroyed(this);
    }
}
