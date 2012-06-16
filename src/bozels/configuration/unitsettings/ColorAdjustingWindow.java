/*
 *  Copyright 2012 Stijn Seghers <stijn.seghers at ugent.be>.
 */

package bozels.configuration.unitsettings;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.*;

/**
 * 
 * @author Stijn Seghers <stijn.seghers at ugent.be>
 */
public class ColorAdjustingWindow extends JFrame {
    public ColorAdjustingWindow(final UnitSettings settings, final JList list) {
        super("Kies een kleur");
        JPanel contentPane = new JPanel(new BorderLayout());
        final JColorChooser cc = new JColorChooser(settings.getColor());
        contentPane.add(cc, BorderLayout.CENTER);
        contentPane.add(new JButton(new AbstractAction("Ok") {
            @Override
            public void actionPerformed(ActionEvent e) {
                settings.setColor(cc.getColor());
                list.repaint();
                dispose();
            }
        }), BorderLayout.SOUTH);
        setContentPane(contentPane);
        pack();
        setLocationRelativeTo(JOptionPane.getRootFrame());
    }
}
