package ru.tigran.cardcollector;

import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;

import java.io.*;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Properties;
import java.util.Random;

public class Utilities {
    private static final String programPath;
    public static Random rnd;
    static {
        rnd = new Random();
        String classesPath = Utilities.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        programPath = classesPath.substring(0, classesPath.lastIndexOf('/')) + "/static/";
    }

    public static String getTelegramFile(String fileId, String folder) {
        String name = ImageExists(fileId, folder);
        if (!name.equals("")) return name;
        try {
            LinkedHashMap<String, Object> result = GetFileInfo(fileId);
            String filePath = (String) result.get("file_path");
            String fileUrl = "https://api.telegram.org/file/bot"
                    + Resources.get("telegram.bot.token")
                    + '/' + filePath;
            String fileName = fileId + "." + filePath.split("\\.")[1];
            String savePath = programPath + folder + "/" + fileName;
            URL url = new URL(fileUrl);
            InputStream in = new BufferedInputStream(url.openStream());
            OutputStream out = new BufferedOutputStream(new FileOutputStream(savePath));
            for ( int i; (i = in.read()) != -1; ) {
                out.write(i);
            }
            in.close();
            out.close();
            return folder + "/" + fileName;
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private static String ImageExists(String fileName, String folder){
        File path = new File(programPath + folder + "/");
        path.mkdirs();
        File[] listOfFiles = path.listFiles();
        for (File file : listOfFiles)
        {
            if (file.isFile())
            {
                String[] filename = file.getName().split("\\.(?=[^\\.]+$)");
                if(filename[0].equals(fileName))
                    return folder + "/" + filename[0]+"."+filename[1];
            }
        }
        return "";
    }

    private static LinkedHashMap<String, Object> GetFileInfo(String fileId) throws ParseException {
        String fileInfoUrl = "https://api.telegram.org/bot"
                + Resources.get("telegram.bot.token")
                + "/getFile?file_id="
                + fileId;
        String fileInfo = "";
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(fileInfoUrl).openStream()));
            String data;
            while ((data = reader.readLine()) != null) fileInfo += data;
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        JSONParser jsonObj = new JSONParser(fileInfo);
        return (LinkedHashMap<String, Object>) jsonObj.parseObject().get("result");
    }

    public static class Resources {
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
            return props.getProperty(name);
        }
    }
}
