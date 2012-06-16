/*
 *  Copyright 2012 Stijn Seghers <stijn.seghers at ugent.be>.
 */

package bozels.units.factories;

import bozels.configuration.unitsettings.UnitSettings;
import bozels.game.explosion.ExplosionHandler;
import bozels.units.bozel.*;
import bozels.units.factories.exception.UnidentifiedBozelException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import org.jdom.Element;

/**
 * 
 * @author Stijn Seghers <stijn.seghers at ugent.be>
 */
public class BozelFactory extends PieceFactory {
    private static final float FIRST_BOZEL_ELEVATION = 10f;
    private static final Map<String, Class<? extends Bozel>> TYPE_CLASSES;
    static {
        TYPE_CLASSES = new HashMap<>();
        TYPE_CLASSES.put("black", WhiteBozel.class);
        TYPE_CLASSES.put("blue", BlueBozel.class);
        TYPE_CLASSES.put("red", RedBozel.class);
        TYPE_CLASSES.put("white", WhiteBozel.class);
        TYPE_CLASSES.put("yellow", YellowBozel.class);
    }
    
    public static Bozel createBozel(Element el, ExplosionHandler explosionHandler)
            throws UnidentifiedBozelException, ReflectiveOperationException {
        float x = getAttFloat(el, "x");
        float y = getAttFloat(el, "y");
        if ("1".equals(el.getAttributeValue("id")))
            y += FIRST_BOZEL_ELEVATION;
        String typeStr = el.getAttributeValue("type");
        Class<? extends Bozel> type = TYPE_CLASSES.get(typeStr);
        if (type == null)
            throw new UnidentifiedBozelException(typeStr);
        if (type.equals(WhiteBozel.class))
            return type.getConstructor(float.class, float.class, ExplosionHandler.class).newInstance(x, y, explosionHandler);
        return type.getConstructor(float.class, float.class).newInstance(x, y);
    }
    
    public static Collection<UnitSettings> getTypes() {
        Collection<UnitSettings> settingsSet = new HashSet<>();
        for (Class<? extends Bozel> clazz : TYPE_CLASSES.values())
            settingsSet.add(UnitSettings.getInstance(clazz.getSimpleName()));
        return settingsSet;
    }
}
