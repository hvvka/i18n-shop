package com.hania.gui.controller;

import com.hania.gui.view.MainFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * @author <a href="mailto:226154@student.pwr.edu.pl">Hanna Grodzicka</a>
 */
public class MainFrameController {

    private MainFrame mainFrame;

    private JComboBox chooseLanguageComboBox;
    private JButton showProductsButton;
    private JLabel welcomeLabel;
    private JLabel languageLabel;
    private JButton flagButton;

    private Locale currentLocale;

    public MainFrameController() {
        initComponents();
        initListeners();
    }

    private void initComponents() {
        mainFrame = new MainFrame();
        mainFrame.setVisible(true);

        chooseLanguageComboBox = mainFrame.getChooseLanguageComboBox();

        showProductsButton = mainFrame.getShowProductsButton();
        welcomeLabel = mainFrame.getWelcomeLabel();
        languageLabel = mainFrame.getLanguageLabel();
        flagButton = mainFrame.getFlagButton();

        refreshLanguage();
    }

    private void refreshLanguage() {
        if (currentLocale != null)
            MainFrame.resourceBundle = ResourceBundle.getBundle("MessageBundle", currentLocale);

        welcomeLabel.setText(MainFrame.resourceBundle.getString("menu.welcome"));
        showProductsButton.setText(MainFrame.resourceBundle.getString("menu.show"));
        languageLabel.setText(MainFrame.resourceBundle.getString("menu.language"));

        flagButton.setBorder(new EmptyBorder(0, 0, 0, 0));
        flagButton.setOpaque(false);
        Image image = Toolkit.getDefaultToolkit().getImage(MainFrame.resourceBundle.getString("background"));
        flagButton.setIcon(new ImageIcon(image.getScaledInstance(300, 150, 0)));
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

            Graphics g = mainFrame.getContentPane().getGraphics();
            mainFrame.getContentPane().paintComponents(g);
            refreshLanguage();
        });
    }

    private void changeCurrentLocale(String language, String country) {
        currentLocale = new Locale(language, country);
        mainFrame.setCurrentLocale(currentLocale);
    }
}
