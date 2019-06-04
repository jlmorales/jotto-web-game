package com.Jotto.Repository;

import com.Jotto.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("wordRepository")
public interface WordRepository extends JpaRepository<Word, Integer>{
    Word findByWord(String word);

    @Query(value="SELECT * FROM WORD ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Word findRandomWord();

    @Override
    List<Word> findAll();



}


