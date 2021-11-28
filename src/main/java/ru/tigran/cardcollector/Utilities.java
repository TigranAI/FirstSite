package ru.tigran.cardcollector;

import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;

import java.io.*;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Random;

public class Utilities {
    private static final String downloadPath;
    public static Random rnd;
    static {
        rnd = new Random();
        downloadPath = "resources/";
    }

    public static String getTelegramFile(String fileId, String folder) {
        String name = ImageExists(fileId, folder);
        if (!name.equals("")) return name;
        try {
            LinkedHashMap<String, Object> result = GetFileInfo(fileId);
            String filePath = (String) result.get("file_path");
            String fileUrl = "https://api.telegram.org/file/bot"
                    + Config.get("telegram.bot.token")
                    + '/' + filePath;
            String fileName = fileId + "." + filePath.split("\\.")[1];
            String savePath = downloadPath + folder + "/" + fileName;
            URL url = new URL(fileUrl);
            InputStream in = new BufferedInputStream(url.openStream());
            OutputStream out = new BufferedOutputStream(new FileOutputStream(savePath));
            for ( int i; (i = in.read()) != -1; ) {
                out.write(i);
            }
            in.close();
            out.close();
            return  String.format("/%s/%s", folder, fileName);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private static String ImageExists(String fileName, String folder){
        File path = new File(downloadPath + folder + "/");
        path.mkdirs();
        File[] listOfFiles = path.listFiles();
        for (File file : listOfFiles)
        {
            if (file.isFile())
            {
                String[] fileData = file.getName().split("\\.(?=[^\\.]+$)");
                if(fileData[0].equals(fileName))
                    return String.format("/%s/%s.%s", folder, fileData[0], fileData[1]);
            }
        }
        return "";
    }

    private static LinkedHashMap<String, Object> GetFileInfo(String fileId) throws ParseException {
        String fileInfoUrl = "https://api.telegram.org/bot"
                + Config.get("telegram.bot.token")
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
}
