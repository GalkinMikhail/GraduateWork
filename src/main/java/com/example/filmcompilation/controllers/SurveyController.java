package com.example.filmcompilation.controllers;


import com.example.filmcompilation.model.Film;
import com.example.filmcompilation.model.User;
import com.example.filmcompilation.repository.FilmRepository;
import com.example.filmcompilation.service.SurveyService;
import com.example.filmcompilation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

@Controller
public class SurveyController {
    @Autowired
    private SurveyService surveyService;
    @Autowired
    private UserService userService;
    @Autowired
    private FilmRepository filmRepository;

    @GetMapping("/survey")
    public String getPrimarySurvey(@AuthenticationPrincipal User user, Model model){
        if (userService.isSurveyPassed(user) && !(user.getWatchedFilms().size() > 0)){
            model.addAttribute("survey",surveyService.getPrimarySurvey(user));
            return "primarySurvey";
        }
        else return "redirect:/home";
    }

    @PostMapping("/passSurvey")
    public String passSurvey(@AuthenticationPrincipal User user, @RequestParam String genre
            ,@RequestParam String dateFrom, @RequestParam String dateFor, @RequestParam String country){
        surveyService.passPrimarySurvey(user,genre,dateFrom,dateFor,country);
        return "redirect:/chooseWatched";
    }

    @GetMapping("/recommendations")
    public String getRecommns(@AuthenticationPrincipal User user, Model model){
        model.addAttribute("recomList",userService.getRecommendations(user));
        return "recommendations";
    }

    @GetMapping("/chooseWatched")
    public String chooseWatchedFilms(@AuthenticationPrincipal User user,Model model){
        model.addAttribute("recoms",user.getRecommendationsFilms());
        model.addAttribute("watched",user.getWatchedFilms());
        return "chooseWatchedFilms";
    }

    @PostMapping("/choose")
    public String choose(@AuthenticationPrincipal User user,@RequestParam List<Film> film){
        surveyService.addToWatchedFilms(user,film);
        return "redirect:/chooseWatched";
    }

    @PostMapping("/addToWatched/{id}")
    public String addToWatched(@AuthenticationPrincipal User user,@PathVariable Long id){
        surveyService.alreadySeen(user,id);
        return "redirect:/recommendations";
    }
}
