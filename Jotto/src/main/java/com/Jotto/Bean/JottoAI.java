package com.Jotto.Bean;

import com.Jotto.Service.WordService;
import com.Jotto.model.Game;
import com.Jotto.model.Guess;
import com.Jotto.model.Word;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Scanner;

public class JottoAI {

    @Autowired
    public static WordService service;

    public static void intelligence(List<Word> wordList, String word, String guess){
        word = word.toLowerCase(); guess = guess.toLowerCase();
        int numberOfLetters = letterCounter(word, guess);
        for (int i = 0; i < wordList.size(); i++) {
            if (letterCounter(wordList.get(i).getWord(), guess) != numberOfLetters) {
                wordList.remove(wordList.get(i));
            }
            else if (wordList.get(i).getWord().equals(guess)) {
                wordList.remove(wordList.get(i));
            }
        }
    }

    public static int letterCounter(String word, String guess) {
        int counter = 0;
        word = word.toLowerCase(); guess = guess.toLowerCase();
        for (int i = 0; i < 5; i++) {
            if (word.indexOf(guess.charAt(i)) > -1) {
                    counter++;
            }
        }
        return counter;
    }
}
