package com.puzzlesolver;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

class GenerateSolutions {

    static List<Solution> solveGame(Board gameBoard) {
        var executor = Executors.newFixedThreadPool(16);
        var tasks = gameBoard.nodes
                .values()
                .stream()
                .map(e -> CompletableFuture.supplyAsync(()-> GenerateSolutions.solution(gameBoard,e),executor))
                .collect(Collectors.toList());
        List<Solution> collect =
                tasks.stream().map(CompletableFuture::join)
                        .flatMap(Collection::stream)
                        .collect(Collectors.toList());
        System.out.println("collect tasks");
        return SolutionDuplicatorRemover.getSolutionWithoutDuplicates(collect);
    }

    static List<Solution> solution(Board board, Node firstNode) {
        List<List<Node>> solutions = new ArrayList<>();
        System.out.println(Thread.currentThread().getName() + " " + firstNode);
        Stack<GameState> gameStateStack = new Stack<>();
        gameStateStack.push(GameState.initialGameState(firstNode, board.wallIndicators));
        while (!gameStateStack.isEmpty()) {
            final GameState currentGameState = gameStateStack.pop();
            if (currentGameState.checkIfAllCurrentNodeConnectionsAreTaggedAsVisited()) {
                if (!currentGameState.checkIfAllNodesAreVisited(board)) {
                    continue;
                }

                if (currentGameState.checkIfCanCloseLine() && currentGameState.checkIfCanVisit(firstNode)) {
                    gameStateStack.push(currentGameState.applyEvent(new VisitNodeEvent(firstNode)));
                }

                if (currentGameState.checkIfAllSideNumberIndicatorsHasAllIncludedWalls()) {
                    solutions.add(currentGameState.visitedOrder);
                    }
            }

            for (Node nodeToVisit : currentGameState.currentNode.possibleConnections) {
                if (!currentGameState.visited.contains(nodeToVisit.id) && currentGameState.checkIfCanVisit(nodeToVisit)) {
                    gameStateStack.push(currentGameState.applyEvent(new VisitNodeEvent(nodeToVisit)));
                }
            }
        }
        return solutions.stream()
                .map(Solution::new)
                .collect(Collectors.toList());
    }
}
