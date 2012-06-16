/*
 * Copyright 2012 Stijn Seghers <stijn.seghers at ugent.be>
 */
package bozels.game.explosion;

import bozels.units.bozel.Bozel;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.jbox2d.callbacks.RayCastCallback;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.World;

/**
 *
 * @author Stijn Seghers <stijn.seghers at ugent.be>
 */
public class ExplosionHandler {
    private static final int RAYS = 100;
    private static final float RAY_LENGTH = 25; // In meter
    private static final float EXPLOSION_FORCE = 50000;
    
    private final Set<Ray> rays;

    public ExplosionHandler() {
        rays = new HashSet<>();
    }

    public void explode(World world, Bozel bozel) {
        Vec2 pos = bozel.getPosition();
        for (float i = 0; i < 2 * Math.PI; i += 2 * Math.PI / RAYS) {
            ExplosionRayCastCallback callBack = new ExplosionRayCastCallback(pos);
            Vec2 ray = new Vec2((float) (RAY_LENGTH * Math.cos(i)),
                                (float) (RAY_LENGTH * Math.sin(i)));
            world.raycast(callBack, pos, pos.add(ray));
            callBack.explode();
        }
    }

    public Set<Ray> getRays() {
        return Collections.unmodifiableSet(rays);
    }

    public void reset() {
        rays.clear();
    }

    private class ExplosionRayCastCallback implements RayCastCallback {
        private final Vec2 bomb;
        
        private float minFraction;
        private Fixture closest;
        private Vec2 hitpoint;

        ExplosionRayCastCallback(Vec2 bomb) {
            this.bomb = bomb.clone();
            minFraction = 1; 
       }

        @Override
        public float reportFixture(Fixture fixture, Vec2 point, Vec2 normal, float fraction) {
            if (fraction < minFraction) {
                minFraction = fraction;
                closest = fixture;
                hitpoint = point.clone();
            }
            return fraction;
        }

        public void explode() {
            if (hitpoint != null) {
                Vec2 fDir = hitpoint.sub(bomb);
                float dist = fDir.normalize();
                Vec2 force = fDir.mul(EXPLOSION_FORCE / (dist + 1));
                closest.getBody().applyForce(force, hitpoint);
                rays.add(new Ray(hitpoint, force));
            }
        }
    }
}
