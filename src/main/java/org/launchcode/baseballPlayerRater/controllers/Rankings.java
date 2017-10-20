package org.launchcode.baseballPlayerRater.controllers;

import org.launchcode.baseballPlayerRater.models.data.BatterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by CNUG on 8/10/17.
 */
@Controller
@RequestMapping("baseball")
public class Rankings {

    @Autowired
    private BatterDao batterDao;


    // TODO: 8/10/17 make controller for displaying rankings page
    @RequestMapping(value = "")
    public String displayRankings(Model model) {

        model.addAttribute("batters", batterDao.findAll());

        return "displayRankings";
    }

}
