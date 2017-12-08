package org.launchcode.baseballPlayerRater.controllers;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.launchcode.baseballPlayerRater.models.data.BatterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by CNUG on 8/10/17.
 */
@Controller
@RequestMapping("baseball")
public class Rankings {

    @Autowired
    private BatterDao batterDao;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String displayRankings(Model model, HttpServletRequest request) {

        String position = request.getParameter("position");

        if (position == null || position.equals("All")) {
            model.addAttribute("batters", batterDao.findAllByOrderByRankAsc());
        } else {
            model.addAttribute("batters", batterDao.findByPositionsOrderByRankAsc(position));
            model.addAttribute("selectedPosition", position);
        }
        return "displayRankings";
    }

}
