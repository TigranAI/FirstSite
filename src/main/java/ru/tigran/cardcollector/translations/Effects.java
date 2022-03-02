package ru.tigran.cardcollector.translations;

import java.io.*;
import java.util.Properties;

public class Effects {
    private static Properties effects;

    static {
        try {
            File file = new File("resources/translations/effects.xml");
            InputStream stream = new BufferedInputStream(new FileInputStream(file));
            effects = new Properties();
            effects.loadFromXML(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String get(Integer key) {
        return effects.getProperty(key.toString());
    }
}
