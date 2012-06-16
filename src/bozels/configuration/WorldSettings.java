/*
 *  Copyright 2012 Stijn Seghers <stijn.seghers at ugent.be>.
 */

package bozels.configuration;

import bozels.game.Model;

/**
 * 
 * @author Stijn Seghers <stijn.seghers at ugent.be>
 */
public class WorldSettings extends Model {
    private static final WorldSettings SETTINGS =new WorldSettings(
            -9.81f, 1f/60f, 8, 750, false, false, false, false);
    
    public static WorldSettings getSettings() {
        return SETTINGS;
    }
    
    private float gravity;
    private float sleepTime;
    private int speed;
    private float launchingPower;
    private boolean centroidsShown;
    private boolean speedShown;
    private boolean sleepingObjectShown;
    private boolean raysShown;
    
    private WorldSettings(float gravity, float sleepTime, int speed,
            float launchingPower, boolean centroidsShown, boolean speedShown,
            boolean sleepingObjectShown, boolean raysShown) {
        this.gravity = gravity;
        this.sleepTime = sleepTime;
        this.speed = speed;
        this.launchingPower = launchingPower;
        this.centroidsShown = centroidsShown;
        this.speedShown = speedShown;
        this.sleepingObjectShown = sleepingObjectShown;
        this.raysShown = raysShown;
    }

    public float getGravity() {
        return gravity;
    }

    public float getSleepTime() {
        return sleepTime;
    }

    public int getSpeed() {
        return speed;
    }

    public float getLaunchingPower() {
        return launchingPower;
    }

    public boolean areCentroidsShown() {
        return centroidsShown;
    }

    public boolean isSpeedShown() {
        return speedShown;
    }

    public boolean areSleepingObjectShown() {
        return sleepingObjectShown;
    }

    public boolean areRaysShown() {
        return raysShown;
    }

    public void setGravity(float gravity) {
        this.gravity = gravity;
        fireStateChanged();
    }

    public void setSleepTime(float sleepTime) {
        this.sleepTime = sleepTime;
        fireStateChanged();
    }

    public void setSpeed(int speed) {
        this.speed = speed;
        fireStateChanged();
    }

    public void setLaunchingPower(float launchingPower) {
        this.launchingPower = launchingPower;
        fireStateChanged();
    }

    public void switchCentroidsShown() {
        centroidsShown ^= true;
        fireStateChanged();
    }

    public void switchSpeedShown() {
        speedShown ^= true;
        fireStateChanged();
    }

    public void switchSleepingObjectShown() {
        sleepingObjectShown ^= true;
        fireStateChanged();
    }

    public void switchRaysShown() {
        raysShown ^= true;
        fireStateChanged();
    }
}
