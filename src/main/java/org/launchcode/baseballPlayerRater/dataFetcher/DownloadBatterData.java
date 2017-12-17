package org.launchcode.baseballPlayerRater.dataFetcher;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;

public class DownloadBatterData {

    public static void saveFile(URL url, File fileName) throws Exception{
        try {
            URLConnection connection = url.openConnection();
            connection.setConnectTimeout(15000);

            String encoding = Base64.getEncoder().encodeToString("cnug:dontlookatme".getBytes());
            connection.setDoOutput(true);
            connection.setRequestProperty  ("Authorization", "Basic " + encoding);
            InputStream content = connection.getInputStream();
            FileUtils.copyInputStreamToFile(content, fileName);


        }
        catch (IOException e) {
            System.out.println("Could not download file.");
            e.printStackTrace();
        }
    }


}
