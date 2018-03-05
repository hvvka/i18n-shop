package com.hania.gui;

import com.hania.gui.controller.MainFrameController;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author <a href="mailto:226154@student.pwr.edu.pl">Hanna Grodzicka</a>
 */
public class Runner {

    public static void main(String[] args) {
        Locale defaultLocale = Locale.getDefault();
        Locale englishLocale = new Locale("en", "GB");

        ResourceBundle.getBundle("MessageBundle", englishLocale);
        new MainFrameController();
    }


}
