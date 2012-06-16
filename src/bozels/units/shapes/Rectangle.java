/*
 *  Copyright 2012 Stijn Seghers <stijn.seghers at ugent.be>.
 */

package bozels.units.shapes;

import java.awt.Graphics2D;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.common.Vec2;

/**
 * 
 * @author Stijn Seghers <stijn.seghers at ugent.be>
 */
public class Rectangle extends UnitShape {
    private final float halfWidth;
    private final float halfHeight;

    public Rectangle(boolean filled, float width, float height) {
        super(filled);
        this.halfWidth = width / 2;
        this.halfHeight = height / 2;
    }

    @Override
    public Shape getBox2DShape() {
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(halfWidth, halfHeight);
        return shape;
    }

    @Override
    public void draw(Graphics2D g, int factor, Vec2 position) {
        float fHW = factor * halfWidth;
        float fHH = factor * halfHeight;
        Vec2 fPos = position.mul(factor);
        if (filled)
            g.fillRect((int) (fPos.x - fHW), (int) (fPos.y - fHH),
                        (int) (2 * fHW), (int) (2 * fHH));
        else
            g.drawRect((int) (fPos.x - fHW), (int) (fPos.y - fHH),
                        (int) (2 * fHW), (int) (2 * fHH));
    }
}
