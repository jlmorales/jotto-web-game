package com.Jotto.Repository;

import com.Jotto.model.Guess;

import com.Jotto.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("guessRepository")
public interface GuessRepository extends JpaRepository<Guess, Integer> {


    List<Guess> findAllByGame(Game game);


}
