/*
 *  Copyright 2012 Stijn Seghers <stijn.seghers at ugent.be>.
 */

package bozels.configuration.settingsfield;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * 
 * @author Stijn Seghers <stijn.seghers at ugent.be>
 */
public class SettingsField extends JTextField implements ActionListener, DocumentListener {
    private SettingsFieldModus modus;
    
    public SettingsField(float initValue) {
        super(initValue + "");
        modus = SettingsFieldModus.CORRECT;
        getDocument().addDocumentListener(this);
        addActionListener(this);
    }

    public void addFieldValueChangeListener(PropertyChangeListener listener) {
        super.addPropertyChangeListener("floatValue", listener);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            float value = Float.parseFloat(getText());
            setText(value + "");
            modus = SettingsFieldModus.CORRECT;
            firePropertyChange("floatValue", null, value);
        } catch (NumberFormatException ex) {
            modus = SettingsFieldModus.WRONG;
        }
        updateColor();
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        setChanging();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        setChanging();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {}
    
    private void setChanging() {
        modus = SettingsFieldModus.CHANGING;
        updateColor();
    }
    
    private void updateColor() {
        setBackground(modus.getColor());
    }
}