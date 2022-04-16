import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

public class NodeConnectionsGeneratorTest {

    @Test
    void generateConnections() {
        var nodes =
                BoardNodeFactory.generateNodesForBoard(3)
                        .stream()
                        .collect(Collectors.toMap(e -> e.point, e -> e));

        var givenNode = nodes.get(new Point(0,0));
        NodeConnectionsGenerator.generateForEachNodeConnection(givenNode, nodes);

        BDDAssertions.assertThat(givenNode.possibleConnections.size()).isEqualTo(2);
    }

    @Test
    void generateForEachNode() {
        var nodes =
                BoardNodeFactory.generateNodesForBoard(3)
                        .stream()
                        .collect(Collectors.toMap(e -> e.point, e -> e));
        NodeConnectionsGenerator.generateNodesConnections(nodes);

        BDDAssertions.assertThat(nodes.get(Point.of(0,0)).possibleConnections.size()).isEqualTo(2);
        BDDAssertions.assertThat(nodes.get(Point.of(1,1)).possibleConnections.size()).isEqualTo(4);
        BDDAssertions.assertThat(nodes.get(Point.of(1,0)).possibleConnections.size()).isEqualTo(3);
    }
}
