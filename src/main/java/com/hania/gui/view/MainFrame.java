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
    private JButton showProductsButton;
    private JComboBox chooseLanguageComboBox;

    static Locale currentLocale;
    static ResourceBundle resourceBundle;

    public MainFrame() {
        super("Electronics shop");
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setContentPane(mainPanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // todo dock's and file's icon
        setIconImage(Toolkit.getDefaultToolkit().getImage("resources/bird.png"));

        currentLocale = Locale.getDefault();
//        currentLocale = new Locale("en", "GB");
        resourceBundle = ResourceBundle.getBundle("MessageBundle", currentLocale);
    }

    public JButton getShowProductsButton() {
        return showProductsButton;
    }

    public JComboBox getChooseLanguageComboBox() {
        return chooseLanguageComboBox;
    }

    public void setCurrentLocale(Locale currentLocale) {
        MainFrame.currentLocale = currentLocale;
    }

}
