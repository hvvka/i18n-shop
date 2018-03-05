package com.hania.gui.controller;

import com.hania.gui.view.MainFrame;

import javax.swing.*;
import java.util.Objects;
import java.util.ResourceBundle;

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
            switch (Objects.requireNonNull(chooseLanguageComboBox.getSelectedItem()).toString()) {
                case "EN":
                    ResourceBundle.getBundle("MessageBundle_en_GB");
                    break;
                case "PL":
                    ResourceBundle.getBundle("MessageBundle_pl_PL");
                    break;
                default:
                    ResourceBundle.getBundle("MessageBundle_de_DE");
                    break;
            }
            mainFrame.revalidate();
            mainFrame.repaint();
            // todo localization
        });

    }
}
