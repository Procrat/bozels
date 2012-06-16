/*
 *  Copyright 2012 Stijn Seghers <stijn.seghers at ugent.be>.
 */

package bozels.units.shapes;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.common.Vec2;

/**
 * Gelijkzijdige driehoek.
 * @author Stijn Seghers <stijn.seghers at ugent.be>
 */
public class Triangle extends UnitShape {
    private final float side;

    public Triangle(boolean filled, float side) {
        super(filled);
        this.side = side;
    }
    
    @Override
    public Shape getBox2DShape() {
        PolygonShape shape = new PolygonShape();
        float h = (float) Math.sqrt(3 * side * side / 4);
        Vec2[] vertices = new Vec2[3];
        vertices[0] = new Vec2(0, 2 * h / 3);
        vertices[1] = new Vec2(-side / 2, -h / 3);
        vertices[2] = new Vec2(side / 2, -h / 3);
        shape.set(vertices, 3);
        return shape;
    }

    @Override
    public void draw(Graphics2D g, int factor, Vec2 position) {
        float fSide = factor * side;
        Vec2 fPos = position.mul(factor);
        float fH = (float) Math.sqrt(3 * fSide * fSide / 4);
        Point p1 = new Point((int) fPos.x, (int) (fPos.y + 2 * fH / 3));
        Point p2 = new Point((int) (fPos.x - fSide / 2), (int) (fPos.y - fH / 3));
        Point p3 = new Point((int) (fPos.x + fSide / 2), (int) (fPos.y - fH / 3));
        int[] xs = {p1.x, p2.x, p3.x};
        int[] ys = {p1.y, p2.y, p3.y};
        Polygon triangle = new Polygon(xs, ys, xs.length);
        g.drawPolygon(triangle);
    }

}
