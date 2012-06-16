/*
 *  Copyright 2012 Stijn Seghers <stijn.seghers at ugent.be>.
 */

package bozels.configuration.unitsettings;

import bozels.configuration.settingsfield.SettingsField;
import java.beans.PropertyChangeEvent;
import javax.swing.GroupLayout;
import javax.swing.JList;

/**
 * 
 * @author Stijn Seghers <stijn.seghers at ugent.be>
 */
public class UnbreakableUnitSettingsFieldPanel extends UnitSettingsFieldsPanel {
    
    public UnbreakableUnitSettingsFieldPanel(final UnitSettings settings, JList list) {
        super(settings, list);
        
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
                    .addComponent(colorButton)));
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(densityLabel)
                    .addComponent(densityField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(restitutionLabel)
                    .addComponent(restitutionField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(frictionLabel)
                    .addComponent(frictionField))
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
    }
}
