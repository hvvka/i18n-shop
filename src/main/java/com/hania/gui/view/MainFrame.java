package com.hania.gui.view;

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

    public static ResourceBundle resourceBundle;
    private JButton showProductsButton;
    private JLabel welcomeLabel;

    static Locale currentLocale;
    private JLabel languageLabel;

    public MainFrame() {
        super("Electronics shop");
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setContentPane(mainPanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        currentLocale = Locale.getDefault();
//        currentLocale = new Locale("en", "GB");
        resourceBundle = ResourceBundle.getBundle("MessageBundle", currentLocale);

        // todo dock's and file's icon
        setIconImage(Toolkit.getDefaultToolkit().getImage("resources/bird.png"));
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

    public void setCurrentLocale(Locale currentLocale) {
        MainFrame.currentLocale = currentLocale;
    }

}
