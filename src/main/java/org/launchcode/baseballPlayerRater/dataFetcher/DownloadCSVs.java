package org.launchcode.baseballPlayerRater.dataFetcher;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class DownloadCSVs {

    private String path;

    public DownloadCSVs(String urlPath) {
        this.path = urlPath;
    }

    public URL download(String fileName) throws IOException {

        URL thisURL = new URL("http://notwork");

        try {
            URL csvURL = new URL(path);
            URLConnection csvConnection = csvURL.openConnection();
            csvConnection.connect();
            thisURL = csvConnection.getURL();

            BufferedReader in = new BufferedReader(new InputStreamReader(csvConnection.getInputStream()));
            String inputline;

            File dataFile = new File(fileName);
            Resource resource = new ClassPathResource(dataFile.getPath());
            FileWriter out = new FileWriter(resource.getFilename());
            CSVPrinter printer = new CSVPrinter(out, CSVFormat.EXCEL.withQuote('"'));


            printer.printRecord("Name","Team","G","AB","PA","H","1B","2B","3B","HR","R","RBI","BB","IBB","SO","HBP","SF","SH","GDP","SB","CS","AVG","OBP","SLG","playerid");


            while ((inputline = in.readLine()) != null) {
                if (inputline.contains("<td class=\"grid_line_regular\" align=\"right\">")) {
                    StringBuilder record = new StringBuilder("0");
                    char[] texts= inputline.toCharArray();
                    for (char text : texts) {
                        if (text >= 48 && text <= 57) {
                            record.append(text);
                        }
                    }
                    printer.printRecord(record);
                }
            }

            printer.close();
            out.close();
            in.close();


        }
        catch (Exception e) {
            e.printStackTrace();
        }


        return thisURL;
    }



}
