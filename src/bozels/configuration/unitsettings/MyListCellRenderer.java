/*
 *  Copyright 2012 Stijn Seghers <stijn.seghers at ugent.be>.
 */

package bozels.configuration.unitsettings;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 * 
 * @author Stijn Seghers <stijn.seghers at ugent.be>
 */
public class MyListCellRenderer extends JLabel implements ListCellRenderer<UnitSettingsFieldsPanel> {
    private static final Border SELECTED_BORDER = new LineBorder(Color.BLACK);
    private static final Border NOT_SELECTED_BORDER = new EmptyBorder(1, 1, 1, 1);
    private static final int ICON_WIDTH = 10;
    private static final int ICON_HEIGHT = 10;

    @Override
    public Component getListCellRendererComponent(
            JList<? extends UnitSettingsFieldsPanel> list,
            UnitSettingsFieldsPanel value, int index, boolean isSelected,
            boolean cellHasFocus) {
        setText(value + "");
        setIcon(new CellIcon(value.getColor()));
        setForeground(list.getForeground());
        setBackground(list.getBackground());
        if (isSelected)
            setBorder(SELECTED_BORDER);
        else
            setBorder(NOT_SELECTED_BORDER);
        return this;
    }

    private static class CellIcon implements Icon {
        private final Color color;

        CellIcon(Color color) {
            this.color = color;
        }

        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
            g.setColor(color);
            g.fillRect(x, y, ICON_WIDTH, ICON_HEIGHT);
        }

        @Override
        public int getIconWidth() {
            return ICON_WIDTH;
        }

        @Override
        public int getIconHeight() {
            return ICON_HEIGHT;
        }
    }
}
