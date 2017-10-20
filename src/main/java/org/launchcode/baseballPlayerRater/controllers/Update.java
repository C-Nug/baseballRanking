package org.launchcode.baseballPlayerRater.controllers;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.launchcode.baseballPlayerRater.models.Batter;
import org.launchcode.baseballPlayerRater.models.data.BatterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

/**
 * Created by CNUG on 8/10/17.
 */
@Controller
@RequestMapping("baseball")
public class Update {

    @Autowired
    private BatterDao batterDao;


    @RequestMapping (value = "update")
    public String updatePlayerData() {

        final String BATTER_DATA_FILE = "rawPlayerData/batterData1B.csv";

        try {

            Resource resource = new ClassPathResource(BATTER_DATA_FILE);
            InputStream is = resource.getInputStream();
            Reader reader = new InputStreamReader(is);
            CSVParser parser = CSVFormat.EXCEL.withFirstRecordAsHeader().withQuote('"').parse(reader);
            List<CSVRecord> records = parser.getRecords();


            for (CSVRecord record : records) {
                Integer playerId = Integer.valueOf(record.get("playerid"));
                String nameStr = record.get(0);     // 0 is Name. "Name" doesn't map correctly.
                String teamStr = record.get("Team");
                String positionStr = "1B";
                Integer runs = Integer.valueOf(record.get("R"));
                Integer hrs = Integer.valueOf(record.get("HR"));
                Integer rbis = Integer.valueOf(record.get("RBI"));
                Integer strikeOuts = Integer.valueOf(record.get("SO"));
                Integer sbs = Integer.valueOf(record.get("SB"));
                Double obp = Double.valueOf(record.get("OBP"));
                Double slg = Double.valueOf(record.get("SLG"));

                Batter createBatter = new Batter(playerId, nameStr, teamStr, positionStr, runs, hrs,
                        rbis, strikeOuts, sbs, obp, slg);
                batterDao.save(createBatter);


            }


        } catch (IOException e) {
            System.out.println("Failed to load player data");
            e.printStackTrace();
        }
        return "dataUpdated";
    }

}
