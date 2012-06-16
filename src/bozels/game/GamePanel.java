/*
 *  Copyright 2012 Stijn Seghers <stijn.seghers at ugent.be>.
 */

package bozels.game;

import bozels.configuration.WorldSettings;
import bozels.game.explosion.ExplosionHandler;
import bozels.game.explosion.Ray;
import bozels.units.Unit;
import java.awt.*;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.jbox2d.common.Vec2;

/**
 * 
 * @author Stijn Seghers <stijn.seghers at ugent.be>
 */
public class GamePanel extends JPanel implements ChangeListener {
    private static final int RESIZE_FACTOR = 7;
    
    private final UnitsHandler unitsHandler;
    private final LaunchModel launchModel;
    private final ExplosionHandler explosionHandler;
    private final WorldSettings worldSettings;

    public GamePanel(UnitsHandler unitsHandler, LaunchModel launchModel, ExplosionHandler explosionHandler) {
        super(null);
        this.unitsHandler = unitsHandler;
        this.launchModel = launchModel;
        this.explosionHandler = explosionHandler;
        worldSettings = WorldSettings.getSettings();
        worldSettings.addChangeListener(this);
        LaunchListener launchListener = new LaunchListener(launchModel, RESIZE_FACTOR);
        addMouseMotionListener(launchListener);
        addMouseListener(launchListener);
        
        setPreferredSize(new Dimension(1024, 450));
    }
    
    @Override
    protected void paintComponent(Graphics gg) {
        super.paintComponent(gg);
        
        if (unitsHandler.hasUnits()) {
            Graphics2D g = (Graphics2D) gg.create();
            g.translate(0, getHeight() - 1);
            g.scale(1, - 1);
            g.setStroke(new BasicStroke(1.5f));
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            if (worldSettings.areRaysShown()) {
                g.setColor(Color.MAGENTA);
                for (Ray ray : explosionHandler.getRays()) {
                    Vec2 start = ray.getStart(RESIZE_FACTOR);
                    Vec2 end = ray.getEnd(RESIZE_FACTOR);
                    g.drawLine((int) start.x, (int) start.y, (int) end.x, (int) end.y);
                }
            }
            for (Unit unit : unitsHandler.getUnits()) {
                Graphics2D elG = (Graphics2D) g.create();
                Color unitColor = unit.getColor();
                if (! unit.isAwake() && worldSettings.areSleepingObjectShown())
                    unitColor = new Color(unitColor.getRGB() + 0x60000000, true); // Alfa bits 30 en 31 op true
                elG.setColor(unitColor);
                Vec2 position = unit.getPosition().mul(RESIZE_FACTOR);
                elG.rotate(unit.getAngle(), position.x, position.y);
                unit.draw(elG, RESIZE_FACTOR);
                
                if (worldSettings.areCentroidsShown()) {
                    Color xord = new Color(255 - unitColor.getRed(),
                                            255 - unitColor.getGreen(),
                                            255 - unitColor.getBlue());
                    g.setColor(xord);
                    g.fillOval((int) (position.x - 2), (int) (position.y - 2), 4, 4);
                }
                if (worldSettings.isSpeedShown()) {
                    Vec2 speedVec = unit.getSpeedVector().mul(RESIZE_FACTOR).add(position);
                    g.setColor(Color.BLACK);
                    g.drawLine((int) position.x, (int) position.y,
                                (int) speedVec.x, (int) speedVec.y);
                }
            }
            Vec2 v1 = launchModel.getLaunchingBozelPosition().mul(RESIZE_FACTOR);
            Vec2 v2 = launchModel.getLaunchDevicePosition().mul(RESIZE_FACTOR);
            g.setColor(Color.BLACK);
            g.drawLine((int) v1.x, (int) v1.y, (int) v2.x, (int) v2.y);
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        repaint();
    }
}
