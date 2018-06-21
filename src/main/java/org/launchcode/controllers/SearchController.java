package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @RequestMapping(value="results")
    public String searchresults (@RequestParam String searchTerm, @RequestParam String searchType, Model model) {
        /*Same functionality as list controller found in other tab. Need to add category of search at top
        Categories found in ListController tab*/
        ArrayList<HashMap<String, String>> jobs;
        if (searchType.equals("all")) {
            /*look for specific search term in ALL categories*/
            jobs = JobData.findByValue(searchTerm);

            model.addAttribute("searchType","searchTerm");
            model.addAttribute("columns", ListController.columnChoices);/*this is for the categories that we get from the other controller*/
            model.addAttribute("jobs", jobs);
        }
        else {
            /*look for specific searhc term in your specified category*/
            jobs = JobData.findByColumnAndValue(searchType,searchTerm);
            model.addAttribute("searchType","searchTerm");
            model.addAttribute("columns", ListController.columnChoices);
            model.addAttribute("jobs", jobs);

        }



        return "search";
        }
    }




