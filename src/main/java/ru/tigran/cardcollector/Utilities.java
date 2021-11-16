package ru.tigran.cardcollector;

import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;

import java.io.*;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Properties;

public class Utilities {

    public static String DownloadTelegramFile(String fileId) {
        try {
            LinkedHashMap<String, Object> result = GetFileInfo(fileId);
            String filePath = (String) result.get("file_path");
            String fileUrl = "https://api.telegram.org/file/bot"
                    + Resources.get("telegram.bot.token")
                    + '/' + filePath;
            String programPath = Utilities.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            new File(programPath + "images").mkdirs();
            String savePath = programPath + "images/" + fileId + "." + filePath.split("\\.")[1];
            URL url = new URL(fileUrl);
            InputStream in = new BufferedInputStream(url.openStream());
            OutputStream out = new BufferedOutputStream(new FileOutputStream(savePath));
            for ( int i; (i = in.read()) != -1; ) {
                out.write(i);
            }
            in.close();
            out.close();
            return savePath;
        } catch (ParseException | IOException e) {
            e.printStackTrace();
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
