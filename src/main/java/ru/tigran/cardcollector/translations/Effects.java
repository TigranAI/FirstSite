package ru.tigran.cardcollector.translations;

import ru.tigran.cardcollector.Utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Effects {
    private static Properties effects;

    static {
        try {
            InputStream stream = Utilities.class.getClassLoader().getResourceAsStream("effects.xml");
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
