/*
 *  Copyright 2012 Stijn Seghers <stijn.seghers at ugent.be>.
 */

package bozels.initialization;

import bozels.game.Simulation;
import bozels.game.UnitsHandler;
import bozels.game.explosion.ExplosionHandler;
import bozels.units.factories.exception.FactoryException;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.jdom.JDOMException;

/**
 * 
 * @author Stijn Seghers <stijn.seghers at ugent.be>
 */
public class Initializer {
    // Enkel voor testdoeleinden. Het is niet mogelijk om met een JFileChooser
    // naar een directory binnen een .jar te verwijzen. Bij het uitvoeren hier-
    // van in een .jar, verwijst de FC standaard naar de home directory door.
    private static final String DEFAULT_DIRECTORY = "/levels";
    
    private final Simulation simulation;
    private final UnitsHandler unitsHandler;
    private final ExplosionHandler explosionHandler;
    private final JFileChooser fileChooser;

    public Initializer(Simulation simulation, UnitsHandler unitsHandler, ExplosionHandler explosionHandler) {
        this.simulation = simulation;
        this.unitsHandler = unitsHandler;
        this.explosionHandler = explosionHandler;
        fileChooser = new JFileChooser(new File(Initializer.class.getResource(DEFAULT_DIRECTORY).getPath()));
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("XML-bestanden", "xml"));
    }

    public void openLevel() {
        int option = fileChooser.showOpenDialog(JOptionPane.getRootFrame());
        if (option == JFileChooser.APPROVE_OPTION) {
            try {
                XMLReader xmlReader = new XMLReader(fileChooser.getSelectedFile());
                unitsHandler.clear();
                explosionHandler.reset();
                xmlReader.readXML(unitsHandler, explosionHandler);
                simulation.loadLevel();
            } catch (ReflectiveOperationException ex) {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),
                        "Er heeft zich een interne fout voor gedaan.\n"
                        + ex.getMessage(), "Interne fout",
                        JOptionPane.ERROR_MESSAGE);
            } catch (JDOMException | IOException ex) {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),
                        "Het geselecteerde bestand kon niet worden geopend of"
                        + "juist worden ingelezen.", "Bestandsfout",
                        JOptionPane.ERROR_MESSAGE);
            } catch (FactoryException ex) {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),
                        ex.getMessage(), "Fout in XML",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
