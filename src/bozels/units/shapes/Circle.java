/*
 *  Copyright 2012 Stijn Seghers <stijn.seghers at ugent.be>.
 */

package bozels.units.shapes;

import java.awt.Graphics2D;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.common.Vec2;

/**
 * 
 * @author Stijn Seghers <stijn.seghers at ugent.be>
 */
public class Circle extends UnitShape {
    private final float radius;
    
    public Circle(boolean filled, float radius) {
        super(filled);
        this.radius = radius;
    }

    @Override
    public Shape getBox2DShape() {
        CircleShape shape = new CircleShape();
        shape.m_radius = radius;
        return shape;
    }

    @Override
    public void draw(Graphics2D g, int factor, Vec2 position) {
        float fRadius = factor * radius;
        Vec2 fPos = position.mul(factor);
        if (filled)
            g.fillOval((int) (fPos.x - fRadius), (int) (fPos.y- fRadius),
                        (int) (2 * fRadius), (int) (2 * fRadius));
        else
            g.drawOval((int) (fPos.x - fRadius), (int) (fPos.y- fRadius),
                        (int) (2 * fRadius), (int) (2 * fRadius));
    }
}
