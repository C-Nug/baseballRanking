package org.launchcode.baseballPlayerRater.dataFetcher;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.launchcode.baseballPlayerRater.models.Batter;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CNUG on 8/30/17.
 *
 * Loads Batter data from specified .csv paths into memory
 */

public class BatterCSVReader {

    private static String batterFilePath;
    private static ArrayList<Batter> allBatters = new ArrayList<>();

    public BatterCSVReader () {
        batterFilePath = "resources/rawPlayerData/apiBatterDataV2.csv";

    }

    public static ArrayList<Batter> loadAllBatters() {
        BatterCSVReader readIn = new BatterCSVReader();
        readIn.loadBatterData(batterFilePath);
        return allBatters;
    }

    private void loadBatterData (String filepath) {

        try {

            Resource resource = new PathResource(filepath);
            InputStream is = resource.getInputStream();
            Reader reader = new InputStreamReader(is);
            CSVParser parser = CSVFormat.EXCEL.withFirstRecordAsHeader().parse(reader);
            List<CSVRecord> records = parser.getRecords();


            for (CSVRecord record : records) {

                Integer playerId = Integer.valueOf(record.get("#Player ID"));
                String nameStr = record.get("#FirstName").concat(" ").concat(record.get("#LastName"));
                String teamStr = record.get("#Team Name");
                String posStr = record.get("#Position");
                if (posStr.equals("LF") || posStr.equals("CF") || posStr.equals("RF"))
                    posStr = "OF";

                Integer abs = Integer.valueOf(record.get("#AtBats"));
                Integer runs = Integer.valueOf(record.get("#Runs"));
                Integer hrs = Integer.valueOf(record.get("#Homeruns"));
                Integer rbis = Integer.valueOf(record.get("#RunsBattedIn"));
                Integer strikeOuts = Integer.valueOf(record.get("#BatterStrikeouts"));
                Integer sbs = Integer.valueOf(record.get("#StolenBases"));
                Double obp = Double.valueOf(record.get("#BatterOnBasePct"));
                Double slg = Double.valueOf(record.get("#BatterSluggingPct"));

                Batter createBatter = new Batter(playerId, nameStr, teamStr, posStr, abs, runs, hrs,
                        rbis, strikeOuts, sbs, obp, slg);

                allBatters.add(createBatter);

            }

            parser.close();
            reader.close();
            is.close();


        } catch (IOException e) {
            System.out.println("Failed to load player data");
            e.printStackTrace();
        }
    }

    public static ArrayList<Batter> getAllBatters() {
        return allBatters;
    }

    public static int findById(ArrayList<Batter> batters, Integer id) {
        int i = 0;
        for (Batter batter : batters) {
            if (batter.getId().equals(id)) {
                return i;
            }
            i += 1;
        }
        return -1;
    }
}
