/*
 *  Copyright 2012 Stijn Seghers <stijn.seghers at ugent.be>.
 */

package bozels.configuration.unitsettings;

import java.awt.Color;

/**
 * 
 * @author Stijn Seghers <stijn.seghers at ugent.be>
 */
public interface UnitSettingsListener {
    public void setDensity(float density);
    public void setFriction(float density);
    public void setRestitution(float restitution);
    public void setColor(Color color);
    public void setBreakable(boolean breakable);
    public void setPower(float power);
    public void setPowerTreshold(float powerTreshold);
}
