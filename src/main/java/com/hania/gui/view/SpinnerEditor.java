package com.hania.gui.view;

import com.hania.process.ItemType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.util.EventObject;

/**
 * @author <a href="mailto:226154@student.pwr.edu.pl">Hanna Grodzicka</a>
 */
public class SpinnerEditor extends DefaultCellEditor {
    private JSpinner spinner;
    private JSpinner.DefaultEditor editor;
    private JTextField textField;
    private boolean valueSet;

    private int rowIndex = 0;
    private int colIndex = 0;
    private Integer previousItemNumber;

    // Initializes the spinner
    SpinnerEditor(ItemsTableView itemsTableView) {
        super(new JTextField());
        spinner = new JSpinner() {
            @Override
            public void commitEdit() throws ParseException {
                super.commitEdit();
                if (rowIndex >= 0 && colIndex == 1) {
                    Object selectedObject = ItemsTableView.table.getModel().getValueAt(rowIndex, colIndex - 1);
                    if (previousItemNumber < Integer.parseInt(textField.getText())) {
                        System.out.println("insert");
                        ItemsTableView.warehouse.addItem(ItemType.valueOf(selectedObject.toString().toUpperCase()));
                    } else if (previousItemNumber > Integer.parseInt(textField.getText())) {
                        System.out.println("remove");
                        ItemsTableView.warehouse.deleteItem(ItemType.valueOf(selectedObject.toString().toUpperCase()));
                    }
                    textField.setText(ItemsTableView.warehouse.getItems()
                            .get(ItemType.valueOf(selectedObject.toString().toUpperCase()))
                            .toString());

                    itemsTableView.refresh();
                }
            }
        };
        editor = ((JSpinner.DefaultEditor) spinner.getEditor());
        textField = editor.getTextField();

        textField.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent fe) {
                rowIndex = ItemsTableView.table.getSelectedRow();
                colIndex = ItemsTableView.table.getSelectedColumn();
                previousItemNumber = Integer.parseInt(textField.getText());

                SwingUtilities.invokeLater(() -> {
                    if (valueSet) {
                        textField.setCaretPosition(1);
                    }
                });
            }

            @Override
            public void focusLost(FocusEvent fe) {
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
}
