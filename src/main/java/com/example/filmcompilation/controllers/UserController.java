package com.example.filmcompilation.controllers;

import com.example.filmcompilation.model.Film;
import com.example.filmcompilation.model.User;
import com.example.filmcompilation.service.FilmService;
import com.example.filmcompilation.service.SurveyService;
import com.example.filmcompilation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
    @Autowired
    private FilmService filmService;
    @Autowired
    private SurveyService surveyService;

    @GetMapping("/home")
    public String main(Model model,@AuthenticationPrincipal User user) {

        return "home";
    }

	@GetMapping("/login")
    public String login() {
        return "log";
    }
	
	@GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String createUser(User user, Model model) {
    	if(!userService.registration(user)) {
    		model.addAttribute("errorMessage", "Пользователь с таким логином уже существует");
    		return "registration";
    	}
        return "redirect:/login";
    }

    @GetMapping("/film/{id}")
    public String getFilmById(@PathVariable Long id,Model model, @AuthenticationPrincipal User user){
        model.addAttribute("film",filmService.getFilmById(id));
        model.addAttribute("user",user);
        model.addAttribute("isWatched",userService.isWatched(user,filmService.getFilmById(id)));
        return "film";
    }

    @GetMapping("/films")
    public String getAllFilms(Model model){
        model.addAttribute("films",filmService.getAllFilms());
        return "films";
    }

    @GetMapping("/profile")
    public String getProfile(@AuthenticationPrincipal User user, Model model){
        model.addAttribute("user",user);
        return "profile";
    }
    @GetMapping("/watchedFilms")
    public String getWatched(@AuthenticationPrincipal User user, Model model){
        model.addAttribute("watched",user.getWatchedFilms());
        return "watchedFilms";
    }

}
