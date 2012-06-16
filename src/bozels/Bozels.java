/*
 *  Copyright 2012 Stijn Seghers <stijn.seghers at ugent.be>.
 */

package bozels;

import bozels.configuration.BozelMenuBar;
import bozels.configuration.ConfigurationPanel;
import bozels.configuration.ToggleAction;
import bozels.game.GamePanel;
import bozels.game.LaunchModel;
import bozels.game.Simulation;
import bozels.game.UnitsHandler;
import bozels.game.explosion.ExplosionHandler;
import bozels.initialization.Initializer;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.*;

/**
 * 
 * @author Stijn Seghers <stijn.seghers at ugent.be>
 */
public class Bozels {
    private static void createGUI() {
        UnitsHandler unitsHandler = new UnitsHandler();
        LaunchModel launchModel = new LaunchModel(unitsHandler);
        ExplosionHandler explosionHandler = new ExplosionHandler();
        final Simulation simulation = new Simulation(unitsHandler, launchModel, explosionHandler);
        Action pauseAction = new ToggleAction("Pauze") {
            @Override
            public void actionPerformed(ActionEvent e) {
                simulation.pause();
            }
        };
        Action restartAction = new AbstractAction("Herstarten") {
            @Override
            public void actionPerformed(ActionEvent e) {
                simulation.restart();
            }
        };
        
        Initializer initializer = new Initializer(simulation, unitsHandler, explosionHandler);
        
        GamePanel gamePanel = new GamePanel(unitsHandler, launchModel, explosionHandler);
        simulation.addChangeListener(gamePanel);
        launchModel.addChangeListener(gamePanel);
        ConfigurationPanel configurationPanel = new ConfigurationPanel(pauseAction, restartAction);
        
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.add(gamePanel, BorderLayout.CENTER);
        contentPane.add(configurationPanel, BorderLayout.SOUTH);
        
        JFrame frame = new JFrame("Bozels - \u00A9 Stijn Seghers");
        JOptionPane.setRootFrame(frame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(contentPane);
        frame.setJMenuBar(new BozelMenuBar(initializer, pauseAction, restartAction));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createGUI();
            }
        });
    }
}
