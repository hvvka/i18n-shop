package com.hania.gui.view;

import com.hania.process.ItemType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.util.EventObject;
import java.util.Map;

/**
 * @author <a href="mailto:226154@student.pwr.edu.pl">Hanna Grodzicka</a>
 */
public class SpinnerEditor extends DefaultCellEditor {
    private JSpinner spinner;
    private JSpinner.DefaultEditor editor;
    private JTextField textField;
    private boolean valueSet;
    private Map<ItemType, Integer> collection;

    // Initializes the spinner
    SpinnerEditor(Map<ItemType, Integer> collection) {
        super(new JTextField());
        this.collection = collection;
        spinner = new JSpinner();
        editor = ((JSpinner.DefaultEditor) spinner.getEditor());
        textField = editor.getTextField();
        textField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent fe) {
                SwingUtilities.invokeLater(() -> {
                    if (valueSet) {
                        textField.setCaretPosition(1);
                    }
                });
            }

            @Override
            public void focusLost(FocusEvent fe) {
//                collection.
            }
        });
        textField.addActionListener(ae -> stopCellEditing());
    }

    // Prepares the spinner component and returns it
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if (!valueSet) {
            spinner.setValue(value);
        }
        SwingUtilities.invokeLater(() -> textField.requestFocus());
        return spinner;
    }

    @Override
    public boolean isCellEditable(EventObject eo) {
        if (eo instanceof KeyEvent) {
            KeyEvent ke = (KeyEvent) eo;
            textField.setText(String.valueOf(ke.getKeyChar()));
            valueSet = true;
        } else {
            valueSet = false;
        }
        return true;
    }

    // Returns the spinners current value
    @Override
    public Object getCellEditorValue() {
        return spinner.getValue();
    }

    @Override
    public boolean stopCellEditing() {
        try {
            editor.commitEdit();
            spinner.commitEdit();
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Invalid value, discarding.");
        }
        return super.stopCellEditing();
    }

    public Map<ItemType, Integer> getCollection() {
        return collection;
    }
}
