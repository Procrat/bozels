/*
 *  Copyright 2012 Stijn Seghers <stijn.seghers at ugent.be>.
 */

package bozels.configuration.settingsfield;

import java.awt.Color;

/**
 * 
 * @author Stijn Seghers <stijn.seghers at ugent.be>
 */
public enum SettingsFieldModus {
    CORRECT (Color.WHITE),
    CHANGING (Color.YELLOW),
    WRONG (Color.RED);

    private final Color color;

    private SettingsFieldModus(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}