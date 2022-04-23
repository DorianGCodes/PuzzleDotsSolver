package com.puzzlesolver;

import java.util.List;
import java.util.stream.Collectors;

class Solution {
    final List<Node> solutionSteps;

    Solution(List<Node> solutionSteps) {
        this.solutionSteps = solutionSteps;
    }

    String visualize() {
        return solutionSteps
                .stream()
                .map(node -> node.point.toString())
                .collect(Collectors.joining(" -> "));
    }
}
