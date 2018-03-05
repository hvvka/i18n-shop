package com.hania.gui.controller;

import com.hania.gui.view.MainFrame;

import javax.swing.*;
import java.util.Locale;
import java.util.Objects;

/**
 * @author <a href="mailto:226154@student.pwr.edu.pl">Hanna Grodzicka</a>
 */
public class MainFrameController {

    private MainFrame mainFrame;

    private JButton showProductsButton;
    private JComboBox chooseLanguageComboBox;

    private Locale currentLocale;

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
            String language;
            String country;

            switch (Objects.requireNonNull(chooseLanguageComboBox.getSelectedItem()).toString()) {
                case "EN":
                    language = "en";
                    country = "GB";
                    changeCurrentLocale(language, country);
                    break;
                case "PL":
                    language = "pl";
                    country = "PL";
                    changeCurrentLocale(language, country);
                    break;
                case "DE":
                    language = "de";
                    country = "DE";
                    changeCurrentLocale(language, country);
                    break;
                default:
                    language = "en";
                    country = "GB";
                    changeCurrentLocale(language, country);
                    break;
            }

            mainFrame.revalidate();
            mainFrame.repaint();
            initComponents();
            // todo localization
        });
    }

    private void changeCurrentLocale(String language, String country) {
        currentLocale = new Locale(language, country);
        mainFrame.setCurrentLocale(currentLocale);
    }
}
