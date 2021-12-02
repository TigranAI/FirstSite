package ru.tigran.cardcollector;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static Boolean DEBUG = true;
    private static Properties props;

    static {
        try {
            InputStream stream = Utilities.class.getClassLoader().getResourceAsStream("config.properties");
            props = new Properties();
            props.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String get(String name){
        if (DEBUG) name += ".debug";
        return props.getProperty(name);
    }
}