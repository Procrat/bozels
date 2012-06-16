/*
 *  Copyright 2012 Stijn Seghers <stijn.seghers at ugent.be>.
 */

package bozels.configuration;

import bozels.configuration.settingsfield.SettingsField;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;

/**
 * 
 * @author Stijn Seghers <stijn.seghers at ugent.be>
 */
public class WorldSettingsPanel extends JPanel implements PropertyChangeListener {
    private final WorldSettings settings;
    
    private final SettingsField gravityFld;
    private final SettingsField sleepTimeFld;
    private final SettingsField speedFld;
    private final SettingsField launchPowerFld;

    public WorldSettingsPanel() {
        settings = WorldSettings.getSettings();
        
        JLabel gravityLbl = new JLabel("Zwaartekracht:");
        JLabel sleepTimeLbl = new JLabel("Tijdsstap:");
        JLabel speedLbl = new JLabel("Snelheid:");
        JLabel launchPowerLbl = new JLabel("Lanceerkracht:");
        
        gravityFld = new SettingsField(settings.getGravity());
        sleepTimeFld = new SettingsField(settings.getSleepTime());
        speedFld = new SettingsField(settings.getSpeed());
        launchPowerFld = new SettingsField(settings.getLaunchingPower());
        
        gravityFld.addFieldValueChangeListener(this);
        sleepTimeFld.addFieldValueChangeListener(this);
        speedFld.addFieldValueChangeListener(this);
        launchPowerFld.addFieldValueChangeListener(this);
        
        JCheckBox cb1 = new JCheckBox(new AbstractAction("Toon zwaartepunten") {
            @Override
            public void actionPerformed(ActionEvent e) {
                settings.switchCentroidsShown();
            }
        });
        JCheckBox cb2 = new JCheckBox(new AbstractAction("Toon snelheid") {
            @Override
            public void actionPerformed(ActionEvent e) {
                settings.switchSpeedShown();
            }
        });
        JCheckBox cb3 = new JCheckBox(new AbstractAction("Markeer slapende objecten") {
            @Override
            public void actionPerformed(ActionEvent e) {
                settings.switchSleepingObjectShown();
            }
        });
        JCheckBox cb4 = new JCheckBox(new AbstractAction("Toon rays") {
            @Override
            public void actionPerformed(ActionEvent e) {
                settings.switchRaysShown();
            }
        });
        
        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                .addGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(gravityLbl)
                    .addComponent(sleepTimeLbl)
                    .addComponent(speedLbl)
                    .addComponent(launchPowerLbl))
                .addGroup(
                    layout.createParallelGroup()
                    .addComponent(gravityFld)
                    .addComponent(sleepTimeFld)
                    .addComponent(speedFld)
                    .addComponent(launchPowerFld))
                .addGroup(
                    layout.createParallelGroup()
                    .addComponent(cb1)
                    .addComponent(cb2)
                    .addComponent(cb3)
                    .addComponent(cb4)));
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                .addGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.CENTER, false)
                    .addComponent(gravityLbl)
                    .addComponent(gravityFld)
                    .addComponent(cb1))
                .addGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.CENTER, false)
                    .addComponent(sleepTimeLbl)
                    .addComponent(sleepTimeFld)
                    .addComponent(cb2))
                .addGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.CENTER, false)
                    .addComponent(speedLbl)
                    .addComponent(speedFld)
                    .addComponent(cb3))
                .addGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.CENTER, false)
                    .addComponent(launchPowerLbl)
                    .addComponent(launchPowerFld)
                    .addComponent(cb4)));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SettingsField source = (SettingsField) evt.getSource();
        float value = (float) evt.getNewValue();
        if (source == gravityFld)
            settings.setGravity(value);
        else if (source == sleepTimeFld)
            settings.setSleepTime(value);
        else if (source == speedFld)
            settings.setSpeed((int) value);
        else if (source == launchPowerFld)
            settings.setLaunchingPower(value);
    }
}
