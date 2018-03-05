package com.hania.gui.controller;

import com.hania.gui.view.ItemsFrame;
import com.hania.gui.view.ItemsTableView;
import com.hania.gui.view.MainFrame;
import com.hania.process.Warehouse;
import com.hania.process.WarehouseImpl;

import javax.swing.*;

/**
 * @author <a href="mailto:226154@student.pwr.edu.pl">Hanna Grodzicka</a>
 */
class ItemsFrameController {

    private MainFrame mainFrame;
    private ItemsFrame itemsFrame;

    private JButton backToMenuButton;
    private ItemsTableView itemsTableView;

    private Warehouse warehouse;

    ItemsFrameController(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        initComponents();
        initListeners();
        initItems();
    }

    private void initItems() {
        warehouse = new WarehouseImpl();
        warehouse.loadItems();

        itemsTableView.setWarehouse(warehouse);
    }

    private void initComponents() {
        itemsFrame = new ItemsFrame();
        itemsFrame.setVisible(true);

        backToMenuButton = itemsFrame.getBackToMenuButton();
        itemsTableView = itemsFrame.getItemsTableView();
        //todo other components
    }

    private void initListeners() {
        backToMenuButton.addActionListener(ae -> {
            mainFrame.setVisible(true);
            itemsFrame.dispose();
        });
        //todo components' listeners
    }
}
