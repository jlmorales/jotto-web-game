package com.Jotto.Service;

import com.Jotto.Repository.GameRepository;
import com.Jotto.model.Game;
import com.Jotto.Bean.GameState;
import com.Jotto.Repository.GameRepository;
import com.Jotto.Repository.GuessRepository;
import com.Jotto.model.Game;
import com.Jotto.model.Guess;
import com.Jotto.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service("gameService")
public class GameService {

    private GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Game findGameById(Integer id){
        return gameRepository.findByGameid(id);
    }

    public List<Game> findGameByUser(User user){
        return gameRepository.findAllByUser(user);
    }

    public Game saveGame(GameState gameState, User user){
        Game game = new Game();
        game.setUser(user);
        game.setComputer_word(gameState.getCompSecret());
        game.setUser_word(gameState.getUserSecret());
        return gameRepository.save(game);
    }

}
