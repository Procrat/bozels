/*
 *  Copyright 2012 Stijn Seghers <stijn.seghers at ugent.be>.
 */

package bozels.game;

import bozels.configuration.WorldSettings;
import bozels.units.bozel.Bozel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.jbox2d.common.Vec2;

/**
 * 
 * @author Stijn Seghers <stijn.seghers at ugent.be>
 */
public class LaunchModel extends Model implements ChangeListener {
    private final UnitsHandler unitsHandler;
    private final WorldSettings worldSettings;
    private float power;
    
    private Vec2 launchDevicePos;
    private int readyBozelId;
    private Bozel readyBozel; //Bozel die klaarstaat om gelanceerd te worden
    private List<Bozel> flyingBozels; // Bozels actief in het spel.
    private boolean specialActionPerformed;
    
    public LaunchModel(UnitsHandler unitsHandler) {
        this.unitsHandler = unitsHandler;
        worldSettings = WorldSettings.getSettings();
        worldSettings.addChangeListener(this);
        power = worldSettings.getLaunchingPower();
        flyingBozels = new ArrayList<>();
    }

    public void reset() {
        readyBozelId = 1;
        readyBozel = unitsHandler.getBozelById(readyBozelId);
        launchDevicePos = readyBozel.getPosition();
        flyingBozels.clear();
        specialActionPerformed = false;
    }
    
    public Vec2 getLaunchingBozelPosition() {
        if (readyBozel != null)
            return readyBozel.getPosition();
        else
            return launchDevicePos;
    }

    public void setLaunchingBozelPosition(Vec2 position) {
        readyBozel.setPosition(position);
        fireStateChanged();
    }
    
    public boolean hasNextBozel() {
        return unitsHandler.getBozelById(readyBozelId + 1) != null;
    }
    
    public void nextLaunchingBozel() {
        readyBozelId++;
        readyBozel = unitsHandler.getBozelById(readyBozelId);
        readyBozel.setPosition(launchDevicePos);
        fireStateChanged();
    }
    
    public Vec2 getLaunchDevicePosition() {
        return launchDevicePos;
    }

    public void launchBozel(Vec2 launchVec) {
        readyBozel.setActive(true);
        readyBozel.launch(launchVec.mulLocal(power));
        flyingBozels.add(readyBozel);
        readyBozel = null;
        specialActionPerformed = false;
    }
    
    public boolean isBozelFlying() {
        return readyBozel == null;
    }

    public void removeSleepingBozels() {
        for (Iterator<Bozel> it = flyingBozels.iterator(); it.hasNext();) {
            Bozel bozel = it.next();
            if (! bozel.isAwake() || unitsHandler.isDestroyed(bozel)) {
                unitsHandler.setUnitToBeDestroyed(bozel);
                it.remove();
            }
        }
    }
    
    public void doSpecialAction() {
        if (! specialActionPerformed && ! flyingBozels.isEmpty()) {
            flyingBozels.get(flyingBozels.size()-1).doSpecialAction(unitsHandler);
            specialActionPerformed = true;
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        power = worldSettings.getLaunchingPower();
    }
}
