package com.hania.gui.view;

import com.apple.eawt.Application;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author <a href="mailto:226154@student.pwr.edu.pl">Hanna Grodzicka</a>
 */
public class MainFrame extends JFrame {

    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;

    private JPanel mainPanel;
    private JComboBox chooseLanguageComboBox;

    private JButton showProductsButton;
    private JLabel welcomeLabel;
    private JLabel languageLabel;
    public static ResourceBundle resourceBundle;
    static Locale currentLocale;
    private JButton flagButton;
    public MainFrame() {
        super("");
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setContentPane(mainPanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        currentLocale = Locale.getDefault();
//        currentLocale = new Locale("en", "GB");
        resourceBundle = ResourceBundle.getBundle("MessageBundle", currentLocale);

        addApplicationIcon();
    }

    private void addApplicationIcon() {
        Image image = Toolkit.getDefaultToolkit().getImage("src/main/resources/icons/XD.jpg");
        Application.getApplication().setDockIconImage(image);
        setIconImage(image);
    }

    public JComboBox getChooseLanguageComboBox() {
        return chooseLanguageComboBox;
    }

    public JButton getShowProductsButton() {
        return showProductsButton;
    }

    public JLabel getLanguageLabel() {
        return languageLabel;
    }

    public JLabel getWelcomeLabel() {
        return welcomeLabel;
    }

    public JButton getFlagButton() {
        return flagButton;
    }

    public void setCurrentLocale(Locale currentLocale) {
        MainFrame.currentLocale = currentLocale;
    }

}
