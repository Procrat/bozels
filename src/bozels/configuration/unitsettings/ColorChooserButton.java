/*
 *  Copyright 2012 Stijn Seghers <stijn.seghers at ugent.be>.
 */

package bozels.configuration.unitsettings;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JList;

/**
 * 
 * @author Stijn Seghers <stijn.seghers at ugent.be>
 */
public class ColorChooserButton extends JButton {
    private final UnitSettings settings;
    
    public ColorChooserButton(final UnitSettings settings, final JList list) {
        this.settings = settings;
        setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ColorAdjustingWindow(settings, list).setVisible(true);
            }
        });
        setMinimumSize(new Dimension(50, 25));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(settings.getColor());
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
