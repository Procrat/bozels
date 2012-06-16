/*
 *  Copyright 2012 Stijn Seghers <stijn.seghers at ugent.be>.
 */
package bozels.units;

import bozels.configuration.unitsettings.UnitSettings;
import bozels.configuration.unitsettings.UnitSettingsListener;
import bozels.units.shapes.UnitShape;
import java.awt.Color;
import java.awt.Graphics2D;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;

/**
 *
 * @author Stijn Seghers <stijn.seghers at ugent.be>
 */
public class Unit implements UnitSettingsListener {
    private final BodyDef bodyDef;
    private final FixtureDef fixtureDef;
    
    private Body body;
    private Fixture fixture;
    private final UnitShape shape;
    private Color color;
    private float power;
    private float powerTreshold;

    protected Unit(float x, float y, float angle, UnitShape shape, UnitSettings settings) {
        bodyDef = new BodyDef();
        bodyDef.position.set(x, y);
        bodyDef.angle = - angle;
        bodyDef.type = settings.isBreakable() ? BodyType.DYNAMIC : BodyType.STATIC;

        fixtureDef = new FixtureDef();
        fixtureDef.density = settings.getDensity();
        fixtureDef.friction = settings.getFriction();
        fixtureDef.restitution = settings.getRestitution();
        fixtureDef.shape = shape.getBox2DShape();

        this.shape = shape;
        this.color = settings.getColor();
        this.power = settings.getPower();
        this.powerTreshold = settings.getPowerTreshold();
        
        settings.addSettingsListener(this);
    }
    
    public void draw(Graphics2D g, int factor) {
        // Om bijv. icoontjes te tekenen hoeft enkel dit aangepast te worden.
        shape.draw(g, factor, getPosition());
    }

    public void reset(World world) {
        if (body != null)
            world.destroyBody(body);
        body = world.createBody(bodyDef);
        fixture = body.createFixture(fixtureDef);
        body.setUserData(this);
    }
    
    public void destroy() {
        body.getWorld().destroyBody(body);
    }

    public boolean isAwake() {
        return body.isAwake() && body.isActive();
    }
    
    protected Body getBody() {
        return body;
    }

    public Vec2 getPosition() {
        if (body != null)
            return body.getPosition().clone();
        else
            return bodyDef.position.clone();
    }

    public Vec2 getSpeedVector() {
        return body.getLinearVelocity();
    }

    public float getAngle() {
        return body.getAngle();
    }

    public float getMass() {
        return body.getMass();
    }
    
    public double getDensity() {
        return fixture.getDensity();
    }

    public double getFriction() {
        return fixture.getFriction();
    }

    public double getRestitution() {
        return fixture.getRestitution();
    }

    public Color getColor() {
        return color;
    }

    public boolean isBreakable() {
        return body.getType() == BodyType.DYNAMIC;
    }

    public float getPower() {
        return power;
    }

    public float getPowerTreshold() {
        return powerTreshold;
    }

    public void setPosition(Vec2 position) {
        body.setTransform(position, getAngle());
    }

    @Override
    public void setDensity(float density) {
        fixtureDef.density = density;
        fixture.setDensity(density);
        body.resetMassData();
    }

    @Override
    public void setFriction(float friction) {
        fixtureDef.friction = friction;
        fixture.setFriction(friction);
    }

    @Override
    public void setRestitution(float restitution) {
        fixtureDef.restitution = restitution;
        fixture.setRestitution(restitution);
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void setBreakable(boolean breakable) {
        bodyDef.type = breakable ? BodyType.DYNAMIC : BodyType.STATIC;
        body.setType(breakable ? BodyType.DYNAMIC : BodyType.STATIC);
    }
    
    @Override
    public void setPower(float power) {
        this.power = power;
    }

    @Override
    public void setPowerTreshold(float powerTreshold) {
        this.powerTreshold = powerTreshold;
    }
}
