package org.launchcode.baseballPlayerRater.controllers;

import org.launchcode.baseballPlayerRater.models.Batter;
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
    public BatterDao batterDao;


    // TODO: 8/10/17 make controller for displaying rankings page
    @RequestMapping (value = "")
    public String displayRankings(Model model) {

        Batter altuve = new Batter(302, "Jose Altuve", "Astros", "2B", 17, 5, 3, 4, 5, 3.0, 2.3);
        batterDao.save(altuve);
        model.addAttribute("batterData", altuve);

        return "displayRankings";
    }

}
