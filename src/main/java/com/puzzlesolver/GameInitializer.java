package com.puzzlesolver;

import java.util.Map;
import java.util.stream.Collectors;

class GameInitializer {
    static Board initGame() {
        var numberOfNodesOnEachSide = 6;
        Map<Point, Node> nodes = BoardNodeFactory.generateNodesForBoard(numberOfNodesOnEachSide)
                .stream()
                .collect(Collectors.toMap(e -> e.point, e -> e));
        NodeConnectionsGenerator.generateNodesConnections(nodes);
        var wallIndicators = SideNumberObjectMother.generateExampleWallIndicators(nodes);
        return new Board(nodes, wallIndicators, numberOfNodesOnEachSide);
    }
}
