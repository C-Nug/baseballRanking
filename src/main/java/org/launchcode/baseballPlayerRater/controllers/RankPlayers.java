package org.launchcode.baseballPlayerRater.controllers;

import org.launchcode.baseballPlayerRater.models.Batter;
import org.launchcode.baseballPlayerRater.models.data.BatterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

/**
 * Created by CNUG on 8/10/17.
 */
@Controller
@RequestMapping("baseball")
public class RankPlayers {

    @Autowired
    private BatterDao batterDao;


    @RequestMapping (value = "rankPlayers")
    public String rankPlayers(Model model) {
        ArrayList<Integer> allRuns = new ArrayList<>();
        Iterable<Batter> batters = batterDao.findAll();

        model.addAttribute("allRuns", allRuns);
        return "rankPlayers";

    }

}
