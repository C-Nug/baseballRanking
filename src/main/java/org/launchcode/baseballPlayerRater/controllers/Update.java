package org.launchcode.baseballPlayerRater.controllers;

import org.launchcode.baseballPlayerRater.dataFetcher.DownloadBatterData;
import org.launchcode.baseballPlayerRater.models.RankerSystem;
import org.launchcode.baseballPlayerRater.models.data.BatterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.net.URL;

/**
 * Created by CNUG on 8/10/17.
 */
@Controller
@RequestMapping("baseball")
public class Update {

    @Autowired
    private BatterDao batterDao;


    @RequestMapping (value = "update")
    public String updatePlayerData() throws Exception {

        URL batterUrl = new URL("https://api.mysportsfeeds.com/v1.1/pull/mlb/2017-regular/cumulative_player_stats.csv?playerstats=AB,H,R,HR,RBI,SO,SB,OBP,SLG&position=1B,2B,3B,SS,LF,RF,CF,OF,C,DH");
        File batterFileName = new File("resources/rawPlayerData/apiBatterDataV2.csv");
        DownloadBatterData.saveFile(batterUrl, batterFileName);

        batterDao.save(RankerSystem.rankBatters());



        return "dataUpdated";
    }

}
