package com.hania.gui;

import com.hania.gui.controller.MainFrameController;

import java.util.Locale;

/**
 * @author <a href="mailto:226154@student.pwr.edu.pl">Hanna Grodzicka</a>
 */
public class Runner {

    public static void main(String[] args) {
        Locale defaultLocale = Locale.getDefault();
//        Locale currentLocale = new Locale("pl");
//        ResourceBundle.getBundle("resources/message.properties", defaultLocale);
        new MainFrameController();
    }


}
