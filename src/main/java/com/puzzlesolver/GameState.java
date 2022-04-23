package com.puzzlesolver;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

class GameState {
    final Node currentNode;
    final Set<UUID> visited;
    final List<Node> visitedOrder;
    final Set<SideNumberIndicator> sideNumberIndicators;
    final List<VisitNodeEvent> events;

    private GameState(Node currentNode, Set<UUID> visited, List<Node> visitedOrder, Set<SideNumberIndicator> sideNumberIndicators, List<VisitNodeEvent> events) {
        this.currentNode = currentNode;
        this.visited = visited;
        this.visitedOrder = visitedOrder;
        this.sideNumberIndicators = sideNumberIndicators;
        this.events = events;
    }

    static GameState initialGameState(Node firstNode, Set<SideNumberIndicator> sideNumberIndicators) {
        return new GameState(firstNode, Set.of(firstNode.id),List.of(firstNode), sideNumberIndicators, List.of());
    }

    GameState applyEvent(VisitNodeEvent visitNodeEvent) {
        var currentVisited = ImmutableSet.<UUID>builder()
                .addAll(this.visited)
                .add(visitNodeEvent.getNodeId())
                .build();
        var currentVisitedOrder = ImmutableList.<Node>builder()
                .addAll(this.visitedOrder)
                .add(visitNodeEvent.nodeToVisit)
                .build();
        Set<SideNumberIndicator> nereastCommonIndicators = getTouchedWalls(this.currentNode,visitNodeEvent.nodeToVisit);
        var updatedWallNumberIndicators = ImmutableSet.<SideNumberIndicator>builder()
                .addAll(this.sideNumberIndicators.stream().filter(e -> !nereastCommonIndicators.contains(e)).collect(Collectors.toSet()))
                .addAll(nereastCommonIndicators.stream().map(SideNumberIndicator::incrementIncludedWalls).collect(Collectors.toSet()))
                .build();
        var events = ImmutableList.<VisitNodeEvent>builder()
                .addAll(this.events)
                .add(visitNodeEvent)
                .build();
        return new GameState(visitNodeEvent.nodeToVisit,currentVisited,currentVisitedOrder,updatedWallNumberIndicators,events);
    }

    Set<SideNumberIndicator> getTouchedWalls(Node firstNode, Node secondNode) {
        return this.sideNumberIndicators.stream().filter(e -> e.nearPoints.contains(firstNode.point) && e.nearPoints.contains(secondNode.point)).collect(Collectors.toSet());
    }

    boolean checkIfAllCurrentNodeConnectionsAreTaggedAsVisited() {
        return this.visited.containsAll(this.currentNode.possibleConnections.stream().map(e -> e.id).collect(Collectors.toList()));
    }

    boolean checkIfAllNodesAreVisited(Board board) {
        return board.nodes.size() == this.visited.size();
    }

    boolean checkIfCanCloseLine() {
        return this.currentNode.possibleConnections.contains(this.visitedOrder.get(0));
    }

    boolean checkIfAllSideNumberIndicatorsHasAllIncludedWalls() {
        return this.sideNumberIndicators.stream().allMatch(e -> e.maxNumberOfTouchedWalls == e.numberOfIncludedWalls || e.maxNumberOfTouchedWalls == 99);
    }

    boolean checkIfCanVisit(Node nodeToVisit) {
        Set<SideNumberIndicator> nearestCommonIndicators = this.getTouchedWalls(this.currentNode, nodeToVisit);
        return nearestCommonIndicators.stream().allMatch(e -> e.numberOfIncludedWalls < e.maxNumberOfTouchedWalls);
    }
}
