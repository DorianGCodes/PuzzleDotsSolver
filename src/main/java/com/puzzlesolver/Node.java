package com.puzzlesolver;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

class Node {
    UUID id = UUID.randomUUID();
    Point point;
    List<Node> possibleConnections;

    Node(int positionX, int positionY) {
        this.point = new Point(positionX,positionY);
    }

    boolean isNextTo(Node node) {
        var xDistance = Math.abs(this.point.x - node.point.x);
        var yDistance = Math.abs(this.point.y - node.point.y);
        if((xDistance == 1 ^ yDistance == 1) && (xDistance <= 1 && yDistance<= 1)) {
            return true;
        }
        return false;
    }

    void updateConnections(List<Node> possibleConnections) {
        assert possibleConnections.stream().allMatch(this::isNextTo) : "Passed nodes are next to given node";
        this.possibleConnections = possibleConnections;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(id, node.id);
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", point=" + point +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
