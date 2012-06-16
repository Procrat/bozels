/*
 *  Copyright 2012 Stijn Seghers <stijn.seghers at ugent.be>.
 */

package bozels.units.factories;

import bozels.configuration.unitsettings.UnitSettings;
import bozels.units.Target;
import bozels.units.factories.exception.UnidentifiedTargetException;
import bozels.units.shapes.Rectangle;
import bozels.units.shapes.UnitShape;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.jdom.Element;

/**
 * 
 * @author Stijn Seghers <stijn.seghers at ugent.be>
 */
public class TargetFactory extends PieceFactory {
    private static final Map<String, UnitSettings> TYPE_SETTINGS;
    static {
        TYPE_SETTINGS = new HashMap<>();
        TYPE_SETTINGS.put("small", UnitSettings.getInstance("SmallTarget"));
        TYPE_SETTINGS.put("big", UnitSettings.getInstance("BigTarget"));
    }
    private static final Map<String, UnitShape> TYPE_SHAPES;
    static {
        TYPE_SHAPES = new HashMap<>();
        TYPE_SHAPES.put("small", new Rectangle(true, 2.5f, 2.5f));
        TYPE_SHAPES.put("big", new Rectangle(true, 4, 4));
    }
    
    public static Target createTarget(Element el)
            throws UnidentifiedTargetException, ReflectiveOperationException {
        float x = getAttFloat(el, "x");
        float y = getAttFloat(el, "y");
        String type = el.getAttributeValue("type");
        UnitShape shape = TYPE_SHAPES.get(type);
        UnitSettings settings = TYPE_SETTINGS.get(type);
        if (shape == null || settings == null)
            throw new UnidentifiedTargetException(type);
        return new Target(x, y, shape, settings);
    }
    
    public static Collection<UnitSettings> getTypes() {
        return TYPE_SETTINGS.values();
    }
}
