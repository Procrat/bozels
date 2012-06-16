/*
 *  Copyright 2012 Stijn Seghers <stijn.seghers at ugent.be>.
 */

package bozels.configuration;

import bozels.initialization.Initializer;
import java.awt.event.ActionEvent;
import javax.swing.*;

/**
 * 
 * @author Stijn Seghers <stijn.seghers at ugent.be>
 */
public class BozelMenuBar extends JMenuBar {

    public BozelMenuBar(final Initializer initializer, Action pauseAction, Action restartAction) {
        JMenu file = new JMenu("Bestand");
        file.setMnemonic('B');
            JMenuItem open = new JMenuItem(new AbstractAction("Open level...") {
                @Override
                public void actionPerformed(ActionEvent e) {
                    initializer.openLevel();
                }
            });
            open.setMnemonic('O');
            open.setAccelerator(KeyStroke.getKeyStroke("control O"));
            file.add(open);
            
            JMenuItem exit = new JMenuItem(new AbstractAction("Afsluiten") {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });
            exit.setMnemonic('A');
            exit.setAccelerator(KeyStroke.getKeyStroke("control Q"));
            file.add(exit);
        add(file);
        
        JMenu game = new JMenu("Spel");
        game.setMnemonic('S');
            JCheckBoxMenuItem pause = new JCheckBoxMenuItem(pauseAction);
            pause.setMnemonic('P');
            pause.setAccelerator(KeyStroke.getKeyStroke("control P"));
            game.add(pause);
            
            JMenuItem restart = new JMenuItem(restartAction);
            restart.setMnemonic('H');
            restart.setAccelerator(KeyStroke.getKeyStroke("control R"));
            game.add(restart);
        add(game);
    }
}
