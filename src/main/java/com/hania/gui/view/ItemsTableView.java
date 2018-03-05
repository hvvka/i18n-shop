package com.hania.gui.view;

import com.hania.process.ItemType;
import com.hania.process.Warehouse;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.MessageFormat;
import java.util.Map;

/**
 * @author <a href="mailto:226154@student.pwr.edu.pl">Hanna Grodzicka</a>
 */
public class ItemsTableView extends JScrollPane {

    private DefaultTableModel model;

    // todo remove static fields
    static Warehouse warehouse;
    static JTable table;

    ItemsTableView(Warehouse warehouse, int width, int height) {
        ItemsTableView.warehouse = warehouse;

        createTableModel();
        createTable();
        hideFirstColumn();
        addSpinner();

        setViewportView(table);
        setPreferredSize(new Dimension(width, height));
    }

    private void addSpinner() {
//        TableColumnModel tcm = table.getColumnModel();
//        TableColumn tc0 = tcm.getColumn(1);
//        tc0.setCellEditor(new SpinnerEditor(this));

        table.getColumnModel().getColumn(1).setCellEditor(new SpinnerEditor(this));
    }

    private void createTable() {
        table = new JTable(model);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        table.setRowHeight(100);
        table.getColumnModel().getColumn(3).setWidth(100);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setRowSelectionAllowed(false);
    }

    private void createTableModel() {
        String tableColumnKey = "table.column";
        Object[] columnNames = new String[]{
                MainFrame.resourceBundle.getString(tableColumnKey + "0"),
                MainFrame.resourceBundle.getString(tableColumnKey + "1"),
                MainFrame.resourceBundle.getString(tableColumnKey + "2"),
                MainFrame.resourceBundle.getString(tableColumnKey + "3"),
                MainFrame.resourceBundle.getString(tableColumnKey + "4")
        };
        model = new DefaultTableModel(columnNames, 0) {
            //  Returning the Class of each column will allow different
            //  renderers to be used based on Class
            @Override
            public Class getColumnClass(int column) {
                return getValueAt(0, column).getClass();
            }
        };
    }

    private void hideFirstColumn() {
        table.getColumnModel().getColumn(0).setMinWidth(0);
        table.getColumnModel().getColumn(0).setMaxWidth(0);
        table.getColumnModel().getColumn(0).setWidth(0);
    }

    public void setWarehouse(Warehouse warehouse) {
        ItemsTableView.warehouse = warehouse;
        refresh();
    }

    void refresh() {
        model.setRowCount(0);

        for (Map.Entry<ItemType, Integer> entry : warehouse.getItems().entrySet()) {
            Object[] o = new Object[]{
                    entry.getKey().toString().toLowerCase(),
                    entry.getValue(),
                    getItemName(entry),
                    getItemPrice(entry.getKey()),
                    new ImageIcon("src/main/resources/items/" + entry.getKey() + ".jpg")
            };
            model.addRow(o);
        }
    }

    private String getItemName(Map.Entry<ItemType, Integer> entry) {
        String bundleKey;

        switch (MainFrame.currentLocale.getCountry()) {
            case "PL":
                bundleKey = setPolishItemNames(entry);
                break;
            case "GB":
                bundleKey = setEnglishItemNames(entry);
                break;
            case "DE":
                bundleKey = setEnglishItemNames(entry);     // todo, ale będzie tak samo jak dla niemieckiego
                break;
            default:
                bundleKey = setEnglishItemNames(entry);
                break;
        }

        return MainFrame.resourceBundle.getString(bundleKey);
    }

    private String setEnglishItemNames(Map.Entry<ItemType, Integer> entry) {
        String bundleKey;
        // item.zero
        if (entry.getValue() == 0) {
            bundleKey = "item.zero." + entry.getKey().toString().toLowerCase();
        }
        // item.one
        else if (entry.getValue() == 1) {
            bundleKey = "item.one." + entry.getKey().toString().toLowerCase();
        }
        // item.two
        else {
            bundleKey = "item.two." + entry.getKey().toString().toLowerCase();
        }
        return bundleKey;
    }

    private String setPolishItemNames(Map.Entry<ItemType, Integer> entry) {
        String bundleKey;
        // item.two
        if (entry.getValue() % 10 >= 2 && entry.getValue() % 10 <= 4
                && entry.getValue() != 12 && entry.getValue() != 13 && entry.getValue() != 14) {
            bundleKey = "item.two." + entry.getKey().toString().toLowerCase();
        }
        // item.one
        else if (entry.getValue() == 1) {
            bundleKey = "item.one." + entry.getKey().toString().toLowerCase();
        }
        // item.zero
        else {
            bundleKey = "item.zero." + entry.getKey().toString().toLowerCase();
        }
        return bundleKey;
    }

    private String getItemPrice(ItemType key) {
        String price = "0";
        switch (key) {
            case DIODE:
                price = "12.34";
                break;
            case TRANSISTOR:
                price = "42.72";
                break;
            case CAPACITOR:
                price = "2.52";
                break;
            case INDUCTOR:
                price = "44.32";
                break;
            case MEMRISTOR:
                price = "23.45";
                break;
            case ANTENNA:
                price = "3.76";
                break;
            case OSCILLATOR:
                price = "50.98";
                break;
            case SENSOR:
                price = "54.34";
                break;
            case DETECTOR:
                price = "11.09";
                break;
            case TRANSDUCER:
                price = "144.87";
                break;
            case MOTOR:
                price = "13.00";
                break;
            case BATTERY:
                price = "12.12";
                break;
        }

        return MessageFormat.format(MainFrame.resourceBundle.getString("item.price"), price);
    }
}
