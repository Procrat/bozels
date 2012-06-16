/*
 *  Copyright 2012 Stijn Seghers <stijn.seghers at ugent.be>.
 */

package bozels.configuration.unitsettings;

import java.awt.Color;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author Stijn Seghers <stijn.seghers at ugent.be>
 */
public class UnitSettings {
    private static final Map<String, UnitSettings> INSTANCES;
    static {
        INSTANCES = new HashMap<>();
        INSTANCES.put("BlueBozel", new UnitSettings("Blauw", 8, 1, .7f, Color.BLUE, true, Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY));
        INSTANCES.put("RedBozel", new UnitSettings("Rood", 10, .9f, .3f, Color.RED, true, Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY));
        INSTANCES.put("WhiteBozel", new UnitSettings("Wit", 5, .2f, 0, Color.GRAY, true, Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY));
        INSTANCES.put("YellowBozel", new UnitSettings("Geel", 10, .9f, .1f, Color.YELLOW, true, Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY));
        
        INSTANCES.put("SmallTarget", new UnitSettings("Klein", 1, .9f, .1f, Color.GREEN, true, 2500, 1750));
        INSTANCES.put("BigTarget", new UnitSettings("Groot", 1, .9f, .1f, Color.PINK, true, 2500, 1250));
        
        INSTANCES.put("Ice", new UnitSettings("Ijs", 1, .1f, 0, Color.BLUE, true, 2500, 3750));
        INSTANCES.put("Metal", new UnitSettings("Metaal", 3, .3f, .2f, Color.CYAN, true, 13000, 4500));
        INSTANCES.put("Solid", new UnitSettings("Vast", 10, 1, .1f, Color.BLACK, false, 0, 0));
        INSTANCES.put("Stone", new UnitSettings("Beton", 4, .9f, 0, Color.GRAY, true, 12500, 5000));
        INSTANCES.put("Wood", new UnitSettings("Hout", .75f, .8f, .3f, new Color(128, 64, 0), true, 3000, 2500));
        
        INSTANCES.put("Ground", new UnitSettings("", 0, 1, .1f, Color.BLACK, false, 0, 0));
    }
    
    private final Set<UnitSettingsListener> listeners;
    
    private String name;
    private float density;
    private float friction;
    private float restitution;
    private Color color;
    private boolean breakable;
    private float power;
    private float powerTreshold;

    private UnitSettings(String name, float density, float friction, float restitution,
            Color color, boolean breakable, float power, float powerTreshold) {
        listeners = new HashSet<>();
        this.name = name;
        this.density = density;
        this.friction = friction;
        this.restitution = restitution;
        this.color = color;
        this.breakable = breakable;
        this.power = power;
        this.powerTreshold = powerTreshold;
    }
    
    public static UnitSettings getInstance(String className) {
        return INSTANCES.get(className);
    }
    
    public void addSettingsListener(UnitSettingsListener listener) {
        listeners.add(listener);
    }
    
    public String getName() {
        return name;
    }

    public float getDensity() {
        return density;
    }

    public float getFriction() {
        return friction;
    }

    public float getRestitution() {
        return restitution;
    }

    public Color getColor() {
        return color;
    }

    public boolean isBreakable() {
        return breakable;
    }

    public float getPower() {
        return power;
    }

    public float getPowerTreshold() {
        return powerTreshold;
    }

    public void setDensity(float density) {
        this.density = density;
        for (UnitSettingsListener settingsListener : listeners)
            settingsListener.setDensity(density);
    }

    public void setFriction(float friction) {
        this.friction = friction;
        for (UnitSettingsListener settingsListener : listeners)
            settingsListener.setFriction(friction);
    }

    public void setRestitution(float restitution) {
        this.restitution = restitution;
        for (UnitSettingsListener settingsListener : listeners)
            settingsListener.setRestitution(restitution);
    }

    public void setColor(Color color) {
        this.color = color;
        for (UnitSettingsListener settingsListener : listeners)
            settingsListener.setColor(color);
    }

    public void switchBreakable() {
        breakable ^= true;
        for (UnitSettingsListener settingsListener : listeners)
            settingsListener.setBreakable(breakable);
    }

    public void setPower(float power) {
        this.power = power;
        for (UnitSettingsListener settingsListener : listeners)
            settingsListener.setPower(power);
    }

    public void setPowerTreshold(float powerTreshold) {
        this.powerTreshold = powerTreshold;
        for (UnitSettingsListener settingsListener : listeners)
            settingsListener.setPowerTreshold(powerTreshold);
    }
}
