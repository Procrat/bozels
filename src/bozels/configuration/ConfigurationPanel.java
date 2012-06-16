/*
 *  Copyright 2012 Stijn Seghers <stijn.seghers at ugent.be>.
 */

package bozels.configuration;

import bozels.configuration.unitsettings.UnitSettingsPanel;
import bozels.units.factories.BozelFactory;
import bozels.units.factories.DecorFactory;
import bozels.units.factories.TargetFactory;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.*;

/**
 * 
 * @author Stijn Seghers <stijn.seghers at ugent.be>
 */
public class ConfigurationPanel extends JPanel {
    
    public ConfigurationPanel(Action pauseAction, Action restartAction) {
        super(new BorderLayout());
        
        JPanel buttonPanel = new JPanel(new GridLayout(0, 1, 5, 5));
        buttonPanel.add(new JToggleButton(pauseAction));
        buttonPanel.add(new JButton(restartAction));
        JPanel wrapperPanel = new JPanel();
        wrapperPanel.add(buttonPanel);
        add(wrapperPanel, BorderLayout.WEST);
        
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.add("Algemeen", new WorldSettingsPanel());
        tabbedPane.add("Materialen", new UnitSettingsPanel(true, DecorFactory.getTypes()));
        tabbedPane.add("Bozels", new UnitSettingsPanel(false, BozelFactory.getTypes()));
        tabbedPane.add("Doelen", new UnitSettingsPanel(true, TargetFactory.getTypes()));
        add(tabbedPane, BorderLayout.CENTER);
        
        setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createEtchedBorder(),
                        BorderFactory.createEmptyBorder(10, 10, 10, 10)));
    }
}
