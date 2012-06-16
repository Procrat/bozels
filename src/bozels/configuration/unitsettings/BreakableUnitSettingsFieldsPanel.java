/*
 *  Copyright 2012 Stijn Seghers <stijn.seghers at ugent.be>.
 */

package bozels.configuration.unitsettings;

import bozels.configuration.settingsfield.SettingsField;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import javax.swing.AbstractAction;
import javax.swing.GroupLayout;
import javax.swing.JCheckBox;
import javax.swing.JList;

/**
 * 
 * @author Stijn Seghers <stijn.seghers at ugent.be>
 */
public class BreakableUnitSettingsFieldsPanel extends UnitSettingsFieldsPanel {
    private final SettingsField powerTresholdField;
    private final SettingsField powerField;
    
    public BreakableUnitSettingsFieldsPanel(final UnitSettings settings, JList list) {
        super(settings, list);
        
        JCheckBox breakable = new JCheckBox(new AbstractAction("Breekbaar") {
            @Override
            public void actionPerformed(ActionEvent e) {
                settings.switchBreakable();
            }
        });
        breakable.setSelected(settings.isBreakable());
        powerTresholdField = new SettingsField(settings.getPowerTreshold());
        powerField = new SettingsField(settings.getPower());
        
        powerTresholdField.addFieldValueChangeListener(this);
        powerField.addFieldValueChangeListener(this);
        
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(densityLabel)
                    .addComponent(restitutionLabel)
                    .addComponent(frictionLabel)
                    .addComponent(colorLabel))
                .addGroup(layout.createParallelGroup()
                    .addComponent(densityField)
                    .addComponent(restitutionField)
                    .addComponent(frictionField)
                    .addComponent(colorButton))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(powerTresholdLabel)
                    .addComponent(powerLabel))
                .addGroup(layout.createParallelGroup()
                    .addComponent(breakable)
                    .addComponent(powerTresholdField)
                    .addComponent(powerField)));
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(densityLabel)
                    .addComponent(densityField)
                    .addComponent(breakable))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(restitutionLabel)
                    .addComponent(restitutionField)
                    .addComponent(powerTresholdLabel)
                    .addComponent(powerTresholdField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(frictionLabel)
                    .addComponent(frictionField)
                    .addComponent(powerLabel)
                    .addComponent(powerField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(colorLabel)
                    .addComponent(colorButton)));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SettingsField source = (SettingsField) evt.getSource();
        float value = (float) evt.getNewValue();
        if (source == densityField)
            settings.setDensity(value);
        else if (source == restitutionField)
            settings.setRestitution(value);
        else if (source == frictionField)
            settings.setFriction(value);
        else if (source == powerTresholdField)
            settings.setPowerTreshold(value);
        else if (source == powerField)
            settings.setPower(value);
    }
}
