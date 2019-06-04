package com.Jotto.Service;

import com.Jotto.model.Guess;
import com.Jotto.model.Game;
import com.Jotto.Repository.GuessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.Jotto.Bean.GameState;
import com.Jotto.Repository.GuessRepository;
import com.Jotto.model.Game;
import com.Jotto.model.Guess;
import com.Jotto.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("guessService")
public class GuessService {
    private GuessRepository guessRepository;

    @Autowired
    public GuessService(GuessRepository guessRepository) {
        this.guessRepository = guessRepository;
    }

    public void saveGuesses(GameState gameState, Game game){
        ArrayList<String> userGuesses = gameState.getUserGuesses();
        ArrayList<String> compGuesses = gameState.getCompGuesses();
        ArrayList<Integer> userPoints = gameState.getUserPoints();
        ArrayList<Integer> compPoints = gameState.getCompPoints();
        for(int i = 0; i < userGuesses.size(); i++){
            Guess guess = new Guess();
            guess.setGame(game);
            guess.setUserguess(userGuesses.get(i));
            guess.setCompguess(compGuesses.get(i));
            guess.setUserPoints(userPoints.get(i));
            guess.setCompPoints(compPoints.get(i));
            guessRepository.save(guess);
        }

    }
    public List<Guess> getGuesses(Game game){
        return guessRepository.findAllByGame(game);
    }

}
