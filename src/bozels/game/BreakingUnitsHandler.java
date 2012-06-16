/*
 *  Copyright 2012 Stijn Seghers <stijn.seghers at ugent.be>.
 */

package bozels.game;

import bozels.units.Unit;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author Stijn Seghers <stijn.seghers at ugent.be>
 */
public class BreakingUnitsHandler {
    private final Map<Unit, Float> damage;
    private final Set<Unit> toBeDestroyed;
    
    public BreakingUnitsHandler() {
        damage = new HashMap<>();
        toBeDestroyed = new HashSet<>();
    }
    
    public void reset() {
        damage.clear();
        toBeDestroyed.clear();
    }
    
    public void setUnitToBeDestroyed(Unit unit) {
        toBeDestroyed.add(unit);
    }
    
    public void setUnitsToBeDestroyedIfNecessary(float force, Unit... units) {
        for (Unit unit : units) {
            if (unit.isBreakable() && force > unit.getPowerTreshold()) {
                float prevDamage = damage.containsKey(unit) ? damage.get(unit) : 0;
                damage.put(unit, prevDamage + force);
                if (prevDamage + force > unit.getPower() * unit.getMass())
                    toBeDestroyed.add(unit);
            }
        }
    }

    public void destroyBrokenUnits(Set<Unit> activeUnits) {
        for (Unit unit : toBeDestroyed) {
            unit.destroy();
            activeUnits.remove(unit);
        }
        toBeDestroyed.clear();
    }
}
