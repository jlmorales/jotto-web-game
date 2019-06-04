package com.Jotto.Bean;

import java.util.ArrayList;

public class GameState {
    boolean winning;
    ArrayList<String> UserGuesses;
    ArrayList<String> CompGuesses;
    ArrayList<Integer> userPoints;
    ArrayList<Integer> compPoints;
    String userSecret;
    String compSecret;
    Alphabet user_state;
    Alphabet comp_state;

    public GameState(boolean winning, ArrayList<String> userGuesses, ArrayList<String> compGuesses, ArrayList<Integer> userPoints, ArrayList<Integer> compPoints, String userSecret, String compSecret) {
        this.winning = winning;
        UserGuesses = userGuesses;
        CompGuesses = compGuesses;
        this.userPoints = userPoints;
        this.compPoints = compPoints;
        this.userSecret = userSecret;
        this.compSecret = compSecret;
    }

    public ArrayList<Integer> getUserPoints() {
        return userPoints;
    }

    public void setUserPoints(ArrayList<Integer> userPoints) {
        this.userPoints = userPoints;
    }

    public ArrayList<Integer> getCompPoints() {
        return compPoints;
    }

    public void setCompPoints(ArrayList<Integer> compPoints) {
        this.compPoints = compPoints;
    }

    public String getUserSecret() {
        return userSecret;
    }

    public void setUserSecret(String userSecret) {
        this.userSecret = userSecret;
    }

    public String getCompSecret() {
        return compSecret;
    }

    public void setCompSecret(String compSecret) {
        this.compSecret = compSecret;
    }

    public boolean isWinning() {
        return winning;
    }

    public void setWinning(boolean winning) {
        this.winning = winning;
    }

    public ArrayList<String> getUserGuesses() {
        return UserGuesses;
    }

    public void setUserGuesses(ArrayList<String> userGuesses) {
        UserGuesses = userGuesses;
    }

    public ArrayList<String> getCompGuesses() {
        return CompGuesses;
    }

    public void setCompGuesses(ArrayList<String> compGuesses) {
        CompGuesses = compGuesses;
    }

    public Alphabet getUser_state() {
        return user_state;
    }

    public void setUser_state(Alphabet user_state) {
        this.user_state = user_state;
    }

    public Alphabet getComp_state() {
        return comp_state;
    }

    public void setComp_state(Alphabet comp_state) {
        this.comp_state = comp_state;
    }
}
