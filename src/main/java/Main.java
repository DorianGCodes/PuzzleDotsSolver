import com.google.common.collect.ImmutableSet;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        var numberOfNodesOnEachSide = 6;
        Map<Point, Node> nodes = BoardNodeFactory.generateNodesForBoard(numberOfNodesOnEachSide)
                .stream()
                .collect(Collectors.toMap(e -> e.point, e -> e));
        var board = new Board();
        board.nodes = nodes;
        board.length = numberOfNodesOnEachSide;
//        var indicator1 = new WallNumberIndicator(0.5,1.5,2, 0, board);
//        var indicator2 = new WallNumberIndicator(1.5,1.5,3, 0, board);
//        var indicator3 = new WallNumberIndicator(0.5,0.5,99, 0, board);
//        var indicator4 = new WallNumberIndicator(1.5,0.5,2, 0, board);
        var indicator04 = new WallNumberIndicator(0.5,4.5,3, 0, board);
        var indicator14 = new WallNumberIndicator(1.5,4.5,99, 0, board);
        var indicator24 = new WallNumberIndicator(2.5,4.5,3, 0, board);
        var indicator34 = new WallNumberIndicator(3.5,4.5,3, 0, board);
        var indicator44 = new WallNumberIndicator(4.5,4.5,3, 0, board);

        var indicator03 = new WallNumberIndicator(0.5,3.5,1, 0, board);
        var indicator13 = new WallNumberIndicator(1.5,3.5,2, 0, board);
        var indicator23 = new WallNumberIndicator(2.5,3.5,99, 0, board);
        var indicator33 = new WallNumberIndicator(3.5,3.5,2, 0, board);
        var indicator43 = new WallNumberIndicator(4.5,3.5,1, 0, board);

        var indicator02 = new WallNumberIndicator(0.5,2.5,99, 0, board);
        var indicator12 = new WallNumberIndicator(1.5,2.5,2, 0, board);
        var indicator22 = new WallNumberIndicator(2.5,2.5,99, 0, board);
        var indicator32 = new WallNumberIndicator(3.5,2.5,99, 0, board);
        var indicator42 = new WallNumberIndicator(4.5,2.5,99, 0, board);

        var indicator01 = new WallNumberIndicator(0.5,1.5,2, 0, board);
        var indicator11 = new WallNumberIndicator(1.5,1.5,3, 0, board);
        var indicator21 = new WallNumberIndicator(2.5,1.5,3, 0, board);
        var indicator31 = new WallNumberIndicator(3.5,1.5,2, 0, board);
        var indicator41 = new WallNumberIndicator(4.5,1.5,2, 0, board);


        var indicator00 = new WallNumberIndicator(0.5,0.5,2, 0, board);
        var indicator10 = new WallNumberIndicator(1.5,0.5,99, 0, board);
        var indicator20 = new WallNumberIndicator(2.5,0.5,2, 0, board);
        var indicator30 = new WallNumberIndicator(3.5,0.5,2, 0, board);
        var indicator40 = new WallNumberIndicator(4.5,0.5,3, 0, board);


        board.wallIndicators = ImmutableSet.<WallNumberIndicator>builder()
                .add(
                        indicator00,
                        indicator10,
                        indicator20,
                        indicator30,
                        indicator40,
                        indicator02,
                        indicator12,
                        indicator22,
                        indicator32,
                        indicator42,
                        indicator01,
                        indicator11,
                        indicator21,
                        indicator31,
                        indicator41,
                        indicator04,
                        indicator14,
                        indicator24,
                        indicator34,
                        indicator44,
                        indicator03,
                        indicator13,
                        indicator23,
                        indicator33,
                        indicator43
                )
                .build();;
        NodeConnectionsGenerator.generateNodesConnections(nodes);

        var executor = Executors.newFixedThreadPool(16);
        var tasks = board.nodes
                .values()
                .stream()
                .map(e -> CompletableFuture.supplyAsync(()-> GenerateSolutions.solution2(board,e),executor))
                .collect(Collectors.toList());
        tasks.stream().map(CompletableFuture::join).collect(Collectors.toList());
        System.out.println(GenerateSolutions._solutions.size());
//        GenerateSolutions.recursiveSolution(board);
    }
}
