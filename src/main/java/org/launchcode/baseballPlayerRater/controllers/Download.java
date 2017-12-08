package org.launchcode.baseballPlayerRater.controllers;

import org.launchcode.baseballPlayerRater.dataFetcher.DownloadCSVs;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.net.URL;

@Controller
@RequestMapping("baseball")
public class Download {

    @RequestMapping (value = "download")
    public String downloadPlayerData(Model model) throws IOException {

        DownloadCSVs catchers = new DownloadCSVs("http://www.fangraphs.com/leaders.aspx?pos=c&stats=bat&lg=all&qual=0&type=c,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,37,38&season=2017&month=0&season1=2017&ind=0&team=0&rost=0&age=0&filter=&players=0");
        URL url = catchers.download("rawPlayerData/batterDataC3.csv");
        model.addAttribute("url", url);

        return "dataDownloaded";
    }



}
