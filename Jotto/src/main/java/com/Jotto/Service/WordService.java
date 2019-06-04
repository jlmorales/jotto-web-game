package com.Jotto.Service;

import com.Jotto.Repository.WordRepository;
import com.Jotto.model.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("wordService")
public class WordService {

    private WordRepository wordRepository;

    @Autowired
    public WordService(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    public Word findWordByWord(String word) {
        return wordRepository.findByWord(word);
    }

    public Word findRandomWord(){return wordRepository.findRandomWord();}

    public List<Word> findAll(){
        return wordRepository.findAll();
    }
}
