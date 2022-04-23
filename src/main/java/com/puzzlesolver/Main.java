package com.puzzlesolver;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Board gameBoard = GameInitializer.initGame();
        List<Solution> solutions = GenerateSolutions.solveGame(gameBoard);
        solutions.forEach(e -> System.out.println(e.visualize()));
    }
}
