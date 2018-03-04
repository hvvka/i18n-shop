package com.hania.gui.view;

import com.hania.process.WarehouseImpl;

import javax.swing.*;

/**
 * @author <a href="mailto:226154@student.pwr.edu.pl">Hanna Grodzicka</a>
 */
public class ItemsFrame extends JFrame {

    private static final int WIDTH = 550;
    private static final int HEIGHT = 550;

    private JPanel itemsPanel;
    private JButton backToMenuButton;

    private ItemsTableView itemsTableView;

    public ItemsFrame() {
        super("Electronics shop");
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setContentPane(itemsPanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public JButton getBackToMenuButton() {
        return backToMenuButton;
    }

    public ItemsTableView getItemsTableView() {
        return itemsTableView;
    }

    private void createUIComponents() {
        itemsTableView = new ItemsTableView(new WarehouseImpl(), 500, 500);
    }
}
