package org.launchcode.baseballPlayerRater.dataFetcher;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.launchcode.baseballPlayerRater.models.Batter;
import org.launchcode.baseballPlayerRater.models.data.BatterDao;
import org.launchcode.baseballPlayerRater.models.data.BatterData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

/**
 * Created by CNUG on 8/30/17.
 */
public class BatterCSVReader {

    private static final String BATTER_DATA_FILE = "rawPlayerData/batterData1B.csv";
    private static boolean isDataLoaded = false;

    @Autowired
    private static BatterDao batterDao;


    static void loadBatterData (BatterData batterData) {

        if (isDataLoaded = true) {
            return;
        }

        try {

            Resource resource = new ClassPathResource(BATTER_DATA_FILE);
            InputStream is = resource.getInputStream();
            Reader reader = new InputStreamReader(is);
            CSVParser parser = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(reader);
            List<CSVRecord> records = parser.getRecords();
            Integer numberOfColumns = records.get(0).size();
            String[] headers = parser.getHeaderMap().keySet().toArray(new String[numberOfColumns]);

            for (CSVRecord record : records) {
                Integer playerId = Integer.valueOf(record.get("playerid"));
                String nameStr = record.get("Name");
                String teamStr = record.get("Team");
                String positionStr = "1B";
                Integer runs = Integer.valueOf(record.get("R"));
                Integer hrs = Integer.valueOf(record.get("HR"));
                Integer rbis = Integer.valueOf(record.get("RBI"));
                Integer strikeOuts = Integer.valueOf(record.get("SO"));
                Integer sbs = Integer.valueOf(record.get("SB"));
                Double obp = Double.valueOf(record.get("OBP"));
                Double slg = Double.valueOf(record.get("SLG"));

                Batter newBatter = new Batter(playerId, nameStr, teamStr, positionStr, runs, hrs,
                        rbis, strikeOuts, sbs, obp, slg);

                batterDao.save(newBatter);


            }

            isDataLoaded = true;

        } catch (IOException e) {
            System.out.println("Failed to load player data");
            e.printStackTrace();
        }
    }

}
