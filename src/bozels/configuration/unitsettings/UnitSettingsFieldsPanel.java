/*
 *  Copyright 2012 Stijn Seghers <stijn.seghers at ugent.be>.
 */

package bozels.configuration.unitsettings;

import bozels.configuration.settingsfield.SettingsField;
import java.awt.Color;
import java.beans.PropertyChangeListener;
import javax.swing.*;
import javax.swing.border.EtchedBorder;

/**
 * 
 * @author Stijn Seghers <stijn.seghers at ugent.be>
 */
public abstract class UnitSettingsFieldsPanel extends JPanel implements PropertyChangeListener {
    protected final UnitSettings settings;
    protected final GroupLayout layout;
    
    protected final JLabel densityLabel;
    protected final JLabel restitutionLabel;
    protected final JLabel frictionLabel;
    protected final JLabel colorLabel;
    protected final JLabel powerLabel;
    protected final JLabel powerTresholdLabel;
    protected final SettingsField densityField;
    protected final SettingsField restitutionField;
    protected final SettingsField frictionField;
    protected final ColorChooserButton colorButton;
    
    protected UnitSettingsFieldsPanel(UnitSettings settings, JList list) {
        this.settings = settings;
        
        densityLabel = new JLabel("Dichtheid:");
        restitutionLabel = new JLabel("Restitution:");
        frictionLabel = new JLabel("Wrijving:");
        colorLabel = new JLabel("Kleur:");
        powerTresholdLabel = new JLabel("Krachtdrempel:");
        powerLabel = new JLabel("Sterkte:");
        
        densityField = new SettingsField(settings.getDensity());
        restitutionField = new SettingsField(settings.getRestitution());
        frictionField = new SettingsField(settings.getFriction());
        colorButton = new ColorChooserButton(settings, list);
        
        densityField.addFieldValueChangeListener(this);
        restitutionField.addFieldValueChangeListener(this);
        frictionField.addFieldValueChangeListener(this);
        
        layout = new GroupLayout(this);
        setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
    }

    @Override
    public String toString() {
        return settings.getName();
    }

    public Color getColor() {
        return settings.getColor();
    }
}