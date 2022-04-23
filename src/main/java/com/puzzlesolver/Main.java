package com.puzzlesolver;

import java.util.List;
import java.util.logging.Logger;

public class Main {
    public static Logger logger = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {
        Board gameBoard = GameInitializer.initGame();
        List<Solution> solutions = GenerateSolutions.solveGame(gameBoard);
        solutions.forEach(e -> logger.info(e.visualize()));
    }
}
