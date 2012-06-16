/*
 *  Copyright 2012 Stijn Seghers <stijn.seghers at ugent.be>.
 */

package bozels.game;

import bozels.configuration.WorldSettings;
import bozels.configuration.unitsettings.UnitSettings;
import bozels.game.explosion.ExplosionHandler;
import bozels.units.Decor;
import bozels.units.Unit;
import bozels.units.shapes.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

/**
 * 
 * @author Stijn Seghers <stijn.seghers at ugent.be>
 */
public class Simulation extends Model implements ActionListener, ChangeListener {
    private static final int VELOCITY_ITERATIONS = 8;
    private static final int POSITION_ITERATIONS = 3;
    private static final int NEXT_BOZEL_WAIT_TIME = 3; // In (wereld)seconden
    private static final int LEVEL_END_WAIT_TIME = 20; // In (wereld)seconden
    
    private final UnitsHandler unitsHandler;
    private final LaunchModel launchModel;
    private final ExplosionHandler explosionHandler;
    private final WorldSettings worldSettings;
    private final Timer timer;
    
    private World world;
    private Runnable worldThread;
    private float worldStepTime; // In seconden
    private boolean paused;
    private float stepsSinceLaunch;
    private boolean levelEnded;
    
    public Simulation(UnitsHandler unitsHandler, LaunchModel launchModel, ExplosionHandler explosionHandler) {
        this.unitsHandler = unitsHandler;
        this.launchModel = launchModel;
        this.explosionHandler = explosionHandler;
        worldSettings = WorldSettings.getSettings();
        worldStepTime = worldSettings.getSleepTime();
        timer = new Timer(worldSettings.getSpeed(), this);
        paused = false;
    }

    public void loadLevel() {
        world = new World(new Vec2(0, worldSettings.getGravity()), true);
        worldSettings.addChangeListener(this);
        world.setContactListener(new ClashListener(unitsHandler));
        worldThread = new Thread(new Runnable() {
            @Override
            public void run() {
                world.step(worldStepTime, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
                fireStateChanged();
            }
        });
        unitsHandler.addDecor(new Decor(0, 0, 0, new Rectangle(true, 1000, 0),
                                UnitSettings.getInstance("Ground")));
        reset();
        
        if (! paused)
            timer.start();
        fireStateChanged();
    }
    
    public void pause() {
        paused ^= true;
        if (hasLoadedLevel()) {
            if (paused)
                timer.stop();
            else
                timer.start();
        }
    }

    public void restart() {
        if (hasLoadedLevel()) {
            timer.stop();
            reset();
            if (! paused)
                timer.restart();
            fireStateChanged();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        worldThread.run();
        unitsHandler.destoryBrokenUnits();
        
        if (! levelEnded) {
            if (unitsHandler.areTargetsDestroyed()) {
                levelEnded = true;
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        JOptionPane.showMessageDialog(
                                JOptionPane.getRootFrame(),
                                "Goedzooo", "",
                                JOptionPane.INFORMATION_MESSAGE);
                        restart();
                    }
                });
            } else {
                launchModel.removeSleepingBozels();
                if (launchModel.isBozelFlying()) {
                    stepsSinceLaunch += worldStepTime;
                    if (launchModel.hasNextBozel()) {
                        if (stepsSinceLaunch >= NEXT_BOZEL_WAIT_TIME) {
                            stepsSinceLaunch = 0;
                            launchModel.nextLaunchingBozel();
                        }
                    } else { // Laatste bozel vliegt.
                        if (! isWorldAwake() || stepsSinceLaunch >= LEVEL_END_WAIT_TIME) {
                            stepsSinceLaunch = 0;
                            levelEnded = true;
                            SwingUtilities.invokeLater(new Runnable() {
                                @Override
                                public void run() {
                                    JOptionPane.showMessageDialog(
                                            JOptionPane.getRootFrame(),
                                            "Jammer!", "",
                                            JOptionPane.INFORMATION_MESSAGE);
                                    restart();
                                }
                            });
                        }
                    }
                }
            }
        }
    }
    
    private boolean hasLoadedLevel() {
        return world != null;
    }

    private boolean isWorldAwake() {
        for (Unit unit : unitsHandler.getUnits())
            if (unit.isAwake())
                return true;
        return false;
    }

    private void reset() {
        unitsHandler.reset(world);
        explosionHandler.reset();
        launchModel.reset();
        stepsSinceLaunch = 0;
        levelEnded = false;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        float gravity = worldSettings.getGravity();
        if (world.getGravity().y != gravity)
            world.setGravity(new Vec2(0, gravity));
        float newStepTime = worldSettings.getSleepTime();
        if (worldStepTime != newStepTime )
            worldStepTime = newStepTime;
        int speed = worldSettings.getSpeed();
        if (speed != timer.getDelay())
            timer.setDelay(speed);
    }
}
