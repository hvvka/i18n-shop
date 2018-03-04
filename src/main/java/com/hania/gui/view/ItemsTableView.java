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
    private Warehouse warehouse;

    private Map<ItemType, Integer> collection;

    private JTable table;
    public ItemsTableView(Map<ItemType, Integer> collection, int width, int height) {
        Object[] columns = new String[]{"Items", "Price", "Change number"};    // todo intenationalization
        model = new DefaultTableModel(columns, 0);

        table = new JTable(model);
        TableColumnModel tcm = table.getColumnModel();
        TableColumn tc = tcm.getColumn(2);
        tc.setCellEditor(new SpinnerEditor(collection));

        table.setRowSelectionAllowed(false);
        this.collection = collection;

        setViewportView(table);
        setPreferredSize(new Dimension(width, height));
    }

    public Map<ItemType, Integer> getCollection() {
        return collection;
    }

    public void setCollection(Map<ItemType, Integer> collection) {
        this.collection = collection;
        refresh();
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
        refresh();
    }

    private void refresh() {
        model.setRowCount(0);

        for (Map.Entry<ItemType, Integer> entry : collection.entrySet()) {
            Object[] o = new Object[]{                      // todo intenationalization
                    entry.getValue().toString() + " " + entry.getKey().toString().toLowerCase(),
                    getItemPrice(entry.getKey()),
                    entry.getValue()
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
