package ru.tigran.cardcollector.translations;

import java.io.*;
import java.util.Properties;

public class ExclusiveTasks {
    private static Properties tasks;

    static {
        try {
            File file = new File("resources/translations/exclusive_tasks.xml");
            InputStream stream = new BufferedInputStream(new FileInputStream(file));
            tasks = new Properties();
            tasks.loadFromXML(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String get(Integer key) {
        return tasks.getProperty(key.toString());
    }
}