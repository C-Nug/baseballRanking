package org.launchcode.baseballPlayerRater.controllers;

import org.launchcode.baseballPlayerRater.models.Batter;
import org.launchcode.baseballPlayerRater.models.RankerSystem;
import org.launchcode.baseballPlayerRater.models.data.BatterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by CNUG on 8/10/17.
 */
@Controller
@RequestMapping("baseball")
public class RankPlayers {

    @Autowired
    private BatterDao batterDao;


    @RequestMapping (value = "rankPlayers")
    public String rankPlayers() {
        Iterable<Batter> batters = batterDao.findAll();
        RankerSystem.findLowest(batters);

        return "rankPlayers";

    }

}
