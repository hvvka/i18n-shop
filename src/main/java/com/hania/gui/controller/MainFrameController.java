package com.hania.gui.controller;

import com.hania.gui.view.MainFrame;

import javax.swing.*;

/**
 * @author <a href="mailto:226154@student.pwr.edu.pl">Hanna Grodzicka</a>
 */
public class MainFrameController {

    private MainFrame mainFrame;

    private JButton showProductsButton;
    private JComboBox chooseLanguageComboBox;

    public MainFrameController() {
        initComponents();
        initListeners();
    }

    private void initComponents() {
        mainFrame = new MainFrame();
        mainFrame.setVisible(true);

        showProductsButton = mainFrame.getShowProductsButton();
        chooseLanguageComboBox = mainFrame.getChooseLanguageComboBox();
    }

    private void initListeners() {
        showProductsButton.addActionListener(ae -> {
            mainFrame.setVisible(false);
            new ItemsFrameController(mainFrame);
        });

        chooseLanguageComboBox.addActionListener(ae -> {
//            if (Objects.requireNonNull(chooseLanguageComboBox.getSelectedItem()).toString().equals("English")) {
//                mainFrame.setResourceBundle("src/main/java/com/hania/bundles/i18n/en.properties");
//            } else {
//                mainFrame.setResourceBundle("src/main/java/com/hania/bundles/i18n/pl.properties");
//            }
            // todo localization
        });

    }
}
