package com.Jotto.Repository;



import com.Jotto.model.Game;
import com.Jotto.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("gameRepository")
public interface GameRepository extends JpaRepository<Game, Integer> {

//    Game findByGameId(Integer id);
//
//    List<Game> findAllByUser(User user);
//
//    Optional<Game> findById(Integer id)
    List<Game> findAllByUser(User user);

    Game findByGameid(Integer id);

//    @Query(value = "SELECT  * FROM Game WHERE Game.user_id=user", nativeQuery =true)
//    Game []findGameByUser(String user);
}

