import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BoardNodeFactoryTest {

    @Test
    void generateBoard() {
        var numberOfNodesOnEachSide = 3;
        Map<Point, Node> nodes = BoardNodeFactory.generateNodesForBoard(numberOfNodesOnEachSide)
                .stream()
                .collect(Collectors.toMap(e -> e.point, e -> e));

        BDDAssertions.assertThat(nodes.values().size()).isEqualTo(9);

        for (int i = 0; i < numberOfNodesOnEachSide; i++) {
            for (int j = 0; j < numberOfNodesOnEachSide; j++) {
                BDDAssertions.assertThat(nodes.containsKey(new Point(i, j))).isTrue();
            }
        }
    }

    @Test
    void traverseThroughNodes() {
        var numberOfNodesOnEachSide = 3;
        Map<Point, Node> nodes = BoardNodeFactory.generateNodesForBoard(numberOfNodesOnEachSide)
                .stream()
                .collect(Collectors.toMap(e -> e.point, e -> e));
        var board = new Board();
        board.nodes = nodes;
        board.length = numberOfNodesOnEachSide;
        NodeConnectionsGenerator.generateNodesConnections(nodes);

        GenerateSolutions.recursiveSolution(board);
    }
}
