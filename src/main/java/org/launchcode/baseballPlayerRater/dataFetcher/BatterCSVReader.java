package org.launchcode.baseballPlayerRater.dataFetcher;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.launchcode.baseballPlayerRater.models.Batter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by CNUG on 8/30/17.
 */


public class BatterCSVReader {

    private static HashMap<String, String> filePaths = new HashMap<>();
    private static ArrayList<Batter> allBatters = new ArrayList<>();
    private static boolean isDataLoaded = false;

    public BatterCSVReader () {
        filePaths.put("1B", "rawPlayerData/batterData1B.csv");
        filePaths.put("2B", "rawPlayerData/batterData2B.csv");
        filePaths.put("3B", "rawPlayerData/batterData3B.csv");
        filePaths.put("SS", "rawPlayerData/batterDataSS.csv");
        filePaths.put("C", "rawPlayerData/batterDataC.csv");
        filePaths.put("OF", "rawPlayerData/batterDataOF.csv");
    }

    public static void loadAllBatters() {
        for (Map.Entry<String, String> filePath : filePaths.entrySet()) {
            BatterCSVReader.loadBatterData(filePath);
        }
    }

    private static void loadBatterData (Map.Entry<String, String> filepath) {

        if (isDataLoaded) {
            return;
        }

        try {

            Resource resource = new ClassPathResource(filepath.getValue());
            InputStream is = resource.getInputStream();
            Reader reader = new InputStreamReader(is);
            CSVParser parser = CSVFormat.EXCEL.withFirstRecordAsHeader().withQuote('"').parse(reader);
            List<CSVRecord> records = parser.getRecords();


            for (CSVRecord record : records) {
                Integer playerId = Integer.valueOf(record.get("playerid"));
                String nameStr = record.get(0);     // 0 is Name. "Name" doesn't map correctly.
                String teamStr = record.get("Team");
                Integer runs = Integer.valueOf(record.get("R"));
                Integer hrs = Integer.valueOf(record.get("HR"));
                Integer rbis = Integer.valueOf(record.get("RBI"));
                Integer strikeOuts = Integer.valueOf(record.get("SO"));
                Integer sbs = Integer.valueOf(record.get("SB"));
                Double obp = Double.valueOf(record.get("OBP"));
                Double slg = Double.valueOf(record.get("SLG"));

                Batter createBatter = new Batter(playerId, nameStr, teamStr, filepath.getKey(), runs, hrs,
                        rbis, strikeOuts, sbs, obp, slg);
                allBatters.add(createBatter);



            }

            isDataLoaded = true;


        } catch (IOException e) {
            System.out.println("Failed to load player data");
            e.printStackTrace();
        }
    }

    public ArrayList<Batter> getAllBatters() {
        return allBatters;
    }
}
