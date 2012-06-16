/*
 *  Copyright 2012 Stijn Seghers <stijn.seghers at ugent.be>.
 */

package bozels.game;

import bozels.units.Decor;
import bozels.units.Target;
import bozels.units.Unit;
import bozels.units.bozel.Bozel;
import java.util.*;
import org.jbox2d.dynamics.World;

/**
 * 
 * @author Stijn Seghers <stijn.seghers at ugent.be>
 */
public class UnitsHandler {
    private final Set<Unit> allUnits;
    private final Set<Unit> activeUnits;
    private final Map<Integer, Bozel> bozels;
    private final Set<Target> targets;
    
    private final BreakingUnitsHandler breakingUnits;
    
    public UnitsHandler() {
        allUnits = new HashSet<>();
        activeUnits = new HashSet<>();
        bozels = new HashMap<>();
        targets = new HashSet<>();
        breakingUnits = new BreakingUnitsHandler();
    }

    // Bij het laden van een level.
    public void clear() {
        allUnits.clear();
        activeUnits.clear();
        bozels.clear();
        targets.clear();
    }
    
    // Bij (her)starten van het level.
    public void reset(World world) {
        activeUnits.addAll(allUnits);
        for (Unit unit : allUnits)
            unit.reset(world);
        for (Bozel bozel : bozels.values())
            bozel.setActive(false);
        breakingUnits.reset();
    }

    public void addBozel(Bozel bozel, int id) {
        bozels.put(id, bozel);
        allUnits.add(bozel);
    }

    public void addTarget(Target target) {
        targets.add(target);
        allUnits.add(target);
    }

    public void addDecor(Decor decorPiece) {
        allUnits.add(decorPiece);
    }
    
    public boolean hasUnits() {
        return ! activeUnits.isEmpty();
    }

    public Set<Unit> getUnits() {
        return Collections.unmodifiableSet(activeUnits);
    }
    
    public Collection<Bozel> getBozels() {
        return bozels.values();
    }
    
    public Bozel getBozelById(int id) {
        return bozels.get(id);
    }

    public boolean areTargetsDestroyed() {
        return Collections.disjoint(activeUnits, targets);
    }

    // ALLES IVM HET BREKEN VAN UNITS
    
    public void setUnitToBeDestroyed(Unit unit) {
        breakingUnits.setUnitToBeDestroyed(unit);
    }

    public void setUnitsToBeDestroyedIfNecessary(float force, Unit... units) {
        breakingUnits.setUnitsToBeDestroyedIfNecessary(force, units);
    }

    public void destoryBrokenUnits() {
        breakingUnits.destroyBrokenUnits(activeUnits);
    }

    public boolean isDestroyed(Bozel bozel) {
        return ! activeUnits.contains(bozel);
    }
}
