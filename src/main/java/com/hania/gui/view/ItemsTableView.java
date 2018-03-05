package com.hania.gui.view;

import com.hania.process.ItemType;
import com.hania.process.Warehouse;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.Map;

/**
 * @author <a href="mailto:226154@student.pwr.edu.pl">Hanna Grodzicka</a>
 */
public class ItemsTableView extends JScrollPane {

    private DefaultTableModel model;
    static Warehouse warehouse;
    static JTable table;

    public ItemsTableView(Warehouse warehouse, int width, int height) {
        this.warehouse = warehouse;
        Object[] columns = new String[]{"Number of items", "Items", "Price"};    // todo intenationalization
        model = new DefaultTableModel(columns, 0);

        table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        TableColumnModel tcm = table.getColumnModel();
        TableColumn tc0 = tcm.getColumn(0);
        tc0.setCellEditor(new SpinnerEditor());

        table.setRowSelectionAllowed(false);

        setViewportView(table);
        setPreferredSize(new Dimension(width, height));
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
        refresh();
    }

    private void refresh() {
        model.setRowCount(0);

        for (Map.Entry<ItemType, Integer> entry : warehouse.getItems().entrySet()) {
            Object[] o = new Object[]{                      // todo intenationalization
                    entry.getValue(),
                    entry.getKey().toString().toLowerCase(),
                    getItemPrice(entry.getKey()),
            };
            model.addRow(o);
        }
    }

    private String getItemPrice(ItemType key) {
        switch (key) {
            case DIODE:
                return "12.34";
            case TRANSISTOR:
                return "42.72";
            case CAPACITOR:
                return "2.52";
            case INDUCTOR:
                return "44.32";
            case MEMRISTOR:
                return "23.45";
            case ANTENNA:
                return "3.76";
            case OSCILLATOR:
                return "50.98";
            case SENSOR:
                return "54.34";
            case DETECTOR:
                return "11.09";
            case TRANSDUCER:
                return "144.87";
            case MOTOR:
                return "13.00";
            case BATTERY:
                return "12.12";
        }
        return "";
    }
}
