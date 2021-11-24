package ru.tigran.cardcollector;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Translations {
    public static Properties effects;

    static {
        try {
            InputStream stream = Utilities.class.getClassLoader().getResourceAsStream("effects.xml");
            effects = new Properties();
            effects.loadFromXML(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
