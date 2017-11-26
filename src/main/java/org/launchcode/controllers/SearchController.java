package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results
//columns
//searchTerm
    @RequestMapping(value = "/results")
    public String search(Model model, @RequestParam String searchType, @RequestParam String searchTerm){
        model.addAttribute("columns", ListController.columnChoices);
        if (searchType.equals("all")){
            ArrayList<HashMap<String, String>> query = JobData.findByValue(searchTerm);
            int itemcount = query.size();
            model.addAttribute("itemcount", itemcount);
            model.addAttribute("query",query);

        }
        else{
            ArrayList<HashMap<String, String>> query = JobData.findByColumnAndValue(searchType, searchTerm);
            int itemcount = query.size();
            model.addAttribute("itemcount", itemcount);
            model.addAttribute("query", query);


        }

        return "search";
    }
}
