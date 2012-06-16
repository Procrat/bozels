/*
 *  Copyright 2012 Stijn Seghers <stijn.seghers at ugent.be>.
 */

package bozels.configuration.unitsettings;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Collection;
import java.util.Iterator;
import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * 
 * @author Stijn Seghers <stijn.seghers at ugent.be>
 */
public class UnitSettingsPanel extends JPanel implements ListSelectionListener {
    private final BorderLayout layout;
    private final JList<UnitSettingsFieldsPanel> list;

    public UnitSettingsPanel(boolean breakable, Collection<UnitSettings> settingsCol) {
        layout = new BorderLayout(10, 10);
        setLayout(layout);
        
        list = new JList<>();
        list.setListData(generatePanels(breakable, settingsCol));
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setPreferredSize(new Dimension(100, 100));
        list.setCellRenderer(new MyListCellRenderer());
        if (settingsCol.size() > 0) {
            list.setSelectedIndex(0);
            add(list.getSelectedValue(), BorderLayout.CENTER);
        }
        list.addListSelectionListener(this);
        
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        list.setBorder(BorderFactory.createLoweredBevelBorder());
        
        add(list, BorderLayout.WEST);
    }

    private UnitSettingsFieldsPanel[] generatePanels(boolean breakable, Collection<UnitSettings> settingsCol) {
        UnitSettingsFieldsPanel[] panels = new UnitSettingsFieldsPanel[settingsCol.size()];
        Iterator<UnitSettings> it = settingsCol.iterator();
        for (int i = 0; it.hasNext(); i++) {
            if (breakable)
                panels[i] = new BreakableUnitSettingsFieldsPanel(it.next(), list);
            else
                panels[i] = new UnbreakableUnitSettingsFieldPanel(it.next(), list);
        }
        return panels;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        remove(layout.getLayoutComponent(BorderLayout.CENTER));
        add(list.getSelectedValue(), BorderLayout.CENTER);
        revalidate();
        repaint();
    }
}
