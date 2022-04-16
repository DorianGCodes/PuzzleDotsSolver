import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

import java.util.*;
import java.util.stream.Collectors;

public class GenerateSolutions {

    public static List<LineState> lineStates;
    public static LineState state = new LineState();
    public static GraphNode graphTree;

    static Solution solution(Board board) {
        Stack<Node> stack = new Stack<>();
        Set<UUID> visited = new HashSet<>();
        var firstNode = board.nodes.get(Point.of(0, board.length - 1));
        stack.push(firstNode);
        Node currentNode = null;
        while (!stack.isEmpty()) {
            Node previousNode = currentNode;
            currentNode = stack.pop();

            if (visited.contains(currentNode.id)) {
                continue;
            }

            visited.add(currentNode.id);
            System.out.println("Current point " + currentNode.point);
            if (previousNode != null) {
                state.addWall(new Wall(previousNode, currentNode));
            }


            for (Node n : currentNode.possibleConnections) {
                if (!visited.contains(n.id)) {
                    stack.push(n);
                }
            }
        }
        state.printSolution();
        return new Solution();
    }
    /*
    void find() {
        List<WallNumberIndicator> result = new ArrayList<>();
        for(WallNumberIndicator indicator : currentNode.wallNumberIndicators) {
            if(indicator.numberOfIncludedWalls != indicator.maxNumberOfTouchedWalls)
                result.add(indicator);
        }
        result
    }
     */

    public static List<List<Node>> _solutions = new ArrayList<>();
    public static List<List<Node>> solutions = new ArrayList<>();

    public static Solution solution2(Board board,Node _firstNode) {
        System.out.println(Thread.currentThread().getName() + " " + _firstNode);
        Stack<Node> stack = new Stack<>();
        Stack<Tuple> tupleStack = new Stack<>();
        Set<UUID> _visited = new HashSet<>();
        List<Node> _visitOrder = new ArrayList<>();
        Set<WallNumberIndicator> _wallNumberIndicators = board.wallIndicators;
        var firstNode = _firstNode;
//        stack.push(firstNode);
        tupleStack.push(new Tuple(firstNode, _visited, _visitOrder, _wallNumberIndicators));
//        Node currentNode = null;
        int counter = 0;
        while (!tupleStack.isEmpty()) {
            counter++;
            Tuple currentNode = tupleStack.pop();
            var currentVisited = ImmutableSet.<UUID>builder()
                    .addAll(currentNode.visited)
                    .add(currentNode.node.id)
                    .build();

            var currentVisitedOrder = ImmutableList.<Node>builder()
                    .addAll(currentNode.visitOrder)
                    .add(currentNode.node)
                    .build();
            var currentWallIndicator = currentNode.wallNumberIndicators;
            if (currentVisited.containsAll(currentNode.node.possibleConnections.stream().map(e -> e.id).collect(Collectors.toList()))) {
                if (board.nodes.size() == currentVisited.size()) {
                    if(currentNode.node.possibleConnections.contains(currentVisitedOrder.get(0))) {
                        Point firstPoint = currentVisitedOrder.get(0).point;
                        Set<WallNumberIndicator> nereastCommonIndicators =
                                currentWallIndicator.stream().filter(e -> e.nearPoints.contains(currentNode.node.point) && e.nearPoints.contains(firstPoint)).collect(Collectors.toSet());
                        if (nereastCommonIndicators.stream().allMatch(e -> e.numberOfIncludedWalls < e.maxNumberOfTouchedWalls)) {
                            currentWallIndicator = ImmutableSet.<WallNumberIndicator>builder()
                                    .addAll(currentWallIndicator.stream().filter(e -> !nereastCommonIndicators.contains(e)).collect(Collectors.toSet()))
                                    .addAll(nereastCommonIndicators.stream().map(WallNumberIndicator::incrementIncludedWalls).collect(Collectors.toSet()))
                                    .build();
                            currentVisitedOrder = ImmutableList.<Node>builder()
                                    .addAll(currentVisitedOrder)
                                    .add(currentVisitedOrder.get(0))
                                    .build();
                        }
                    }
                    if (currentWallIndicator.stream().allMatch(e -> e.maxNumberOfTouchedWalls == e.numberOfIncludedWalls || e.maxNumberOfTouchedWalls == 99))
                        _solutions.add(currentVisitedOrder);
                }
                continue;
            }

            for (Node n : currentNode.node.possibleConnections) {
                if (!currentVisited.contains(n.id)) {
                    Set<WallNumberIndicator> nereastCommonIndicators = currentWallIndicator.stream().filter(e -> e.nearPoints.contains(currentNode.node.point) && e.nearPoints.contains(n.point)).collect(Collectors.toSet());
                    if (nereastCommonIndicators.stream().allMatch(e -> e.numberOfIncludedWalls < e.maxNumberOfTouchedWalls)) {
                        var updatedWallNumberIndicators = ImmutableSet.<WallNumberIndicator>builder()
                                .addAll(currentWallIndicator.stream().filter(e -> !nereastCommonIndicators.contains(e)).collect(Collectors.toSet()))
                                .addAll(nereastCommonIndicators.stream().map(WallNumberIndicator::incrementIncludedWalls).collect(Collectors.toSet()))
                                .build();
                        tupleStack.push(new Tuple(n, currentVisited, currentVisitedOrder, updatedWallNumberIndicators));
                    }


                }
            }
//            System.out.println("Counter " + counter);
        }
        System.out.println(_solutions.size());
        return new Solution();
    }

    static Solution recursiveSolution(Board board) {
        Set<UUID> visited = new HashSet<>();
        var firstNode = board.nodes.get(Point.of(0, board.length - 1));
        dfsRecursive(firstNode, visited, new ArrayList<>(), board);
        List<int[][]> list = new ArrayList<>();
        solutions.stream()
                .filter(e -> e.size() == board.nodes.size())
                .forEach(e -> {
                    int[][] tab = new int[board.length][board.length];
                    for (int i = 0; i < e.size(); i++) {
                        var node = e.get(i);
                        tab[node.point.x][node.point.y] = i;
                    }
                    list.add(tab);
                });
//        list.forEach(e -> printBoards(e,board));
        System.out.println(list.size());
        return new Solution();
    }


    static void dfsRecursive(Node node, Set<UUID> visited, List<Node> visitSteps, Board board) {
        Set<UUID> visitiedTemp = new HashSet<>(Set.copyOf(visited));
        List<Node> visitStepsTemp = new ArrayList<>(visitSteps);
        visitiedTemp.add(node.id);

        if (!visited.isEmpty() && (visitiedTemp.containsAll(node.possibleConnections.stream().map(e -> e.id).collect(Collectors.toList())))) {
            if (board.nodes.size() == visitiedTemp.size()) {
                visitSteps.add(node);
            }
            solutions.add(visitSteps);
            return;
        }


        visitStepsTemp.add(node);
        for (Node dest : node.possibleConnections) {
            if (!visited.contains(dest.id))
                dfsRecursive(dest, visitiedTemp, visitStepsTemp, board);
        }
    }

    static void printBoards(int[][] boards, Board board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = board.length - 1; j >= 0; j--) {
                System.out.print(" " + boards[i][j] + " ");
            }
            System.out.println("");
            System.out.println("=========");
        }
        System.out.println("###############");
    }
}
