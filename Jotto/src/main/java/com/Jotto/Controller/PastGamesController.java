package com.Jotto.Controller;

import com.Jotto.Bean.Alphabet;
import com.Jotto.Service.GameService;
import com.Jotto.Service.GuessService;
import com.Jotto.Service.UserService;
import com.Jotto.model.Game;
import com.Jotto.model.Guess;
import com.Jotto.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PastGamesController {

    @Autowired
    private GameService gameService;

    @Autowired
    private UserService userService;

    @Autowired
    private GuessService guessService;

    @RequestMapping(value={"/pastgames"}, method = RequestMethod.GET)
    public ModelAndView pastGames(){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        List<Game> games = gameService.findGameByUser(user);
        Game gm = gameService.findGameById(1);
        List<Guess> guesses = guessService.getGuesses(gm);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("games",games);
        modelAndView.setViewName("pastgames");
        return modelAndView;
    }





    @RequestMapping(value = "/registration/{gameId}/", method = RequestMethod.GET)
    public ModelAndView loadGame(@RequestParam(value = "gameId") int gameId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("pastgames");
        Alphabet computerSet = new Alphabet(); // use this to determine state of each letter on computer guesses
        Alphabet userSet = new Alphabet(); // use this to determine state of each letter on computer guesses
        // insert code relevant to past games content
        return modelAndView;
    }
}
