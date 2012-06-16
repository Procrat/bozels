/*
 *  Copyright 2012 Stijn Seghers <stijn.seghers at ugent.be>.
 */

package bozels.units.shapes;

import java.awt.Graphics2D;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.common.Vec2;

/**
 * 
 * @author Stijn Seghers <stijn.seghers at ugent.be>
 */
public abstract class UnitShape {
    protected final boolean filled;
    
    protected UnitShape(boolean filled) {
        this.filled = filled;
    }
    
    public abstract Shape getBox2DShape();
    
    public abstract void draw(Graphics2D g, int factor, Vec2 position);
}
