/*
 *  Copyright 2012 Stijn Seghers <stijn.seghers at ugent.be>.
 */

package bozels.units.factories;

import bozels.configuration.unitsettings.UnitSettings;
import bozels.units.Decor;
import bozels.units.factories.exception.FactoryException;
import bozels.units.factories.exception.UnidentifiedMaterialException;
import bozels.units.factories.exception.UnidentifiedShapeException;
import bozels.units.shapes.Circle;
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
public class DecorFactory extends PieceFactory {
    private static final Map<String, UnitSettings> TYPE_SETTINGS;
    static {
        TYPE_SETTINGS = new HashMap<>();
        TYPE_SETTINGS.put("ice", UnitSettings.getInstance("Ice"));
        TYPE_SETTINGS.put("metal", UnitSettings.getInstance("Metal"));
        TYPE_SETTINGS.put("solid", UnitSettings.getInstance("Solid"));
        TYPE_SETTINGS.put("stone", UnitSettings.getInstance("Stone"));
        TYPE_SETTINGS.put("wood", UnitSettings.getInstance("Wood"));
    }
    
    public static Decor createDecor(Element el)
            throws FactoryException, ReflectiveOperationException {
        float x = getAttFloat(el, "x");
        float y = getAttFloat(el, "y");
        float angle = (float) Math.toRadians(getAttFloat(el, "angle"));
        float width = getAttFloat(el, "width");
        float height = getAttFloat(el, "height");
        UnitShape shape = getShape(el.getName(), width, height);
        UnitSettings settings = TYPE_SETTINGS.get(el.getAttributeValue("material"));
        if (settings == null)
            throw new UnidentifiedMaterialException(el.getAttributeValue("material"));
        return new Decor(x, y, angle, shape, settings);
    }

    private static UnitShape getShape(String name, float width, float height) throws UnidentifiedShapeException {
        switch (name) {
            case "block":
                return new Rectangle(true, width, height);
            case "ellipse":
                return new Circle(true, width / 2);
            default:
                throw new UnidentifiedShapeException(name);
        }
    }
    
    public static Collection<UnitSettings> getTypes() {
        return TYPE_SETTINGS.values();
    }
}
