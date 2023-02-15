package com.mycompany.dvdstore.web.controller;

import com.mycompany.dvdstore.entity.Movie;
import com.mycompany.dvdstore.service.MovieServiceInterface;
import com.mycompany.dvdstore.web.form.MovieForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ModelAndViewMethodReturnValueHandler;

import javax.validation.Valid;

@Controller
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieServiceInterface movieService;

    public MovieServiceInterface getMovieService() {
        return movieService;
    }

    public void setMovieService(MovieServiceInterface movieService) {
        this.movieService = movieService;
    }

//    @GetMapping("/{id}")
//    public String displayMovieCard(@PathVariable("id") long number, Model model){
//        System.out.println("Affichage des détails du film");
//        model.addAttribute("movie",movieService.getMovieById(number));
//        return "movie-details";
//    }

    @PostMapping("/add")
    public String addMovie(@Valid @ModelAttribute("movieForm") MovieForm movieForm, BindingResult results) {

        if(results.hasErrors()){
            return "add-movie-form";
        }

        Movie movie = new Movie();
        movie.setTitle(movieForm.getTitle());
        movie.setGenre(movieForm.getGenre());
        movie.setDescription(movieForm.getDescription());

        movieService.registerMovie(movie);

        return "movie-added";
    }
}
