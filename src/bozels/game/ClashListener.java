
/*
 *  Copyright 2012 Stijn Seghers <stijn.seghers at ugent.be>.
 */

package bozels.game;

import bozels.configuration.WorldSettings;
import bozels.units.Unit;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.contacts.Contact;

/**
 * 
 * @author Stijn Seghers <stijn.seghers at ugent.be>
 */
public class ClashListener implements ContactListener, ChangeListener {
    private final UnitsHandler unitsHandler;
    private final WorldSettings worldSettings;
    
    private float sleepTime;

    public ClashListener(UnitsHandler unitsHandler) {
        this.unitsHandler = unitsHandler;
        worldSettings = WorldSettings.getSettings();
        worldSettings.addChangeListener(this);
        sleepTime = worldSettings.getSleepTime();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        sleepTime = worldSettings.getSleepTime();
    }

    @Override
    public void beginContact(Contact contact) {}

    @Override
    public void endContact(Contact contact) {}

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {}

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
        Body bodyA = contact.getFixtureA().getBody();
        Body bodyB = contact.getFixtureB().getBody();
        Unit unitA = (Unit) bodyA.getUserData();
        Unit unitB = (Unit) bodyB.getUserData();
        float force = impulse.normalImpulses[0] / sleepTime;
        unitsHandler.setUnitsToBeDestroyedIfNecessary(force, unitA, unitB);
    }
}
