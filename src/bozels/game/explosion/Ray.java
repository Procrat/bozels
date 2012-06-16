/*
 * Copyright 2012 Stijn Seghers <stijn.seghers at ugent.be>
 */
package bozels.game.explosion;

import org.jbox2d.common.Vec2;

/**
 *
 * @author Stijn Seghers <stijn.seghers at ugent.be>
 */
public class Ray {
    // Om de een of andere reden zijn de getekende rays zodanig groot dat het
    // einde ervan niet te zien op het scherm tenzij ze met een bepaalde factor
    // worden verkleind.
    private static final float DOWNSIZE_FACTOR = 1f / 500f;

    private final Vec2 hitpoint;
    private final Vec2 force;

    public Ray(Vec2 hitpoint, Vec2 force) {
        this.hitpoint = hitpoint;
        this.force = force;
    }
    
    public Vec2 getStart(int mulFactor) {
        return hitpoint.mul(mulFactor);
    }
    
    public Vec2 getEnd(int mulFactor) {
        return force.mul(DOWNSIZE_FACTOR).add(hitpoint).mul(mulFactor);
    }
}
