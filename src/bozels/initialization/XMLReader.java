/*
 *  Copyright 2012 Stijn Seghers <stijn.seghers at ugent.be>.
 */

package bozels.initialization;

import bozels.game.UnitsHandler;
import bozels.game.explosion.ExplosionHandler;
import bozels.units.Decor;
import bozels.units.Target;
import bozels.units.bozel.Bozel;
import bozels.units.factories.BozelFactory;
import bozels.units.factories.DecorFactory;
import bozels.units.factories.TargetFactory;
import bozels.units.factories.exception.FactoryException;
import java.io.File;
import java.io.IOException;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

/**
 * 
 * @author Stijn Seghers <stijn.seghers at ugent.be>
 */
public class XMLReader {
    private final Element root;
    
    public XMLReader(File xmlFile) throws JDOMException, IOException {
        Document doc = new SAXBuilder().build(xmlFile);
        root = doc.getRootElement();
    }

    public void readXML(UnitsHandler unitsHandler, ExplosionHandler explosionHandler)
                    throws FactoryException, ReflectiveOperationException {
        for (Object obj : root.getChildren("bozel")) {
            Element el = (Element) obj;
            Bozel bozel = BozelFactory.createBozel(el, explosionHandler);
            unitsHandler.addBozel(bozel, Integer.parseInt(el.getAttributeValue("id")));
        }

        for (Object obj : root.getChildren("target")) {
            Element el = (Element) obj;
            Target target = TargetFactory.createTarget(el);
            unitsHandler.addTarget(target);
        }
        
        addDecorWithName("block", unitsHandler);
        addDecorWithName("ellipse", unitsHandler);
    }

    private void addDecorWithName(String block, UnitsHandler unitsHandler)
                    throws FactoryException, ReflectiveOperationException {
        for (Object obj : root.getChildren(block)) {
            Element el = (Element) obj;
            Decor decorPiece = DecorFactory.createDecor(el);
            unitsHandler.addDecor(decorPiece);
        }
    }
}
