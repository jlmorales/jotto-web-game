package com.Jotto.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "guess")
public class Guess {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "guess_id")
    private int id;
    @Column(name = "userguess")
    private String userguess;
    @Column(name = "userPoints")
    private int userPoints;
    @Column(name = "compguess")
    private String compguess;
    @Column(name = "compPoints")
    private int compPoints;
    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

}
