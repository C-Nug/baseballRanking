package org.launchcode.baseballPlayerRater.controllers;

import org.launchcode.baseballPlayerRater.dataFetcher.BatterCSVReader;
import org.launchcode.baseballPlayerRater.models.data.BatterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

        batterDao.save(BatterCSVReader.loadAllBatters());

        return "dataUpdated";
    }

}
