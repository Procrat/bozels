/*
 *  Copyright 2012 Stijn Seghers <stijn.seghers at ugent.be>.
 */

package bozels.game;

import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;
import org.jbox2d.common.Vec2;

/**
 * 
 * @author Stijn Seghers <stijn.seghers at ugent.be>
 */
public class LaunchListener extends MouseInputAdapter {
    //In meter
    private static final float SELECTABLE_RADIUS_SQUARED = 2.5f;
    private static final float DRAGABLE_RADIUS = 7f;
    
    private final LaunchModel launchModel;
    private final int resizeFactor;
    
    //In meter
    private Vec2 launchDevicePos;
    private Vec2 mousePos;
    
    private boolean bozelSelected;

    public LaunchListener(LaunchModel launchModel, int resizeFactor) {
        this.launchModel = launchModel;
        this.resizeFactor = resizeFactor;
        bozelSelected = false;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        launchDevicePos = launchModel.getLaunchDevicePosition();
        if (launchDevicePos != null) { // Er is een level geladen
            Vec2 clickedPos = getClickedPos(e);
            if (! launchModel.isBozelFlying() && inLaunchDeviceAdherence(clickedPos)) {
                mousePos = launchDevicePos.sub(clickedPos);
                bozelSelected = true;
            } else {
                launchModel.doSpecialAction();
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (bozelSelected) {
            bozelSelected = false;
            Vec2 rd = launchDevicePos.sub(mousePos.add(getClickedPos(e)));
            float rdLen = rd.normalize();
            if (rdLen < DRAGABLE_RADIUS)
                rd.mulLocal(rdLen / DRAGABLE_RADIUS);
            launchModel.launchBozel(rd);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (bozelSelected) {
            Vec2 rd = launchDevicePos.sub(mousePos.add(getClickedPos(e)));
            float rdLen = rd.length();
            Vec2 rubber = rd.mul(Math.min(rdLen, DRAGABLE_RADIUS) / rdLen);
            Vec2 bozelPos = launchDevicePos.sub(rubber);
            launchModel.setLaunchingBozelPosition(bozelPos);
        }
    }

    private boolean inLaunchDeviceAdherence(Vec2 clickedPos) {
        return launchDevicePos.sub(clickedPos).lengthSquared() < SELECTABLE_RADIUS_SQUARED;
    }
    
    private Vec2 getClickedPos(MouseEvent e) {
        Vec2 pos = new Vec2(e.getX(), e.getComponent().getHeight() - e.getY());
        return pos.mul(1f / resizeFactor);
    }
}
