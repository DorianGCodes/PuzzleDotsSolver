import org.assertj.core.api.Assertions;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

public class NodeTest {

    @ParameterizedTest
    @CsvSource({
            "0,1,0,0, true",
            "0,0,1,1, false",
            "1,1,0,0, false",
            "1,1,2,1, true",
            "1,1,0,2, false",
            "0,2,1,2, true",
            "0,0,2,1,false",
            "0,0,1,2,false",
            "2,2,1,0,false"

    })
    void testNextNodes(
            int firstNodePosX,
            int firstNodePosY,
            int secondNodePosX,
            int secondNodePosY,
            boolean isNextTo
    ) {
        Node node1 = new Node(firstNodePosX,firstNodePosY);
        Node node2 = new Node(secondNodePosX, secondNodePosY);

        var isNext = node1.isNextTo(node2);

        BDDAssertions.assertThat(isNext).isEqualTo(isNextTo);
    }

    @Test
    void updateNodeConnections() {
        Node node1 = new Node(0,0);

        var possibleConnections = List.of(new Node(0,1), new Node(1,0));

        node1.updateConnections(possibleConnections);
        BDDAssertions.assertThat(node1.possibleConnections).isEqualTo(possibleConnections);
    }

    @Test
    void wrongConnections() {
        Node node1 = new Node(0,0);

        var possibleConnections = List.of(new Node(0,1), new Node(1,0), new Node(1,1));

        Throwable throwable = Assertions.catchThrowable(() -> node1.updateConnections(possibleConnections));
        BDDAssertions.assertThat(throwable.getClass()).isEqualTo(AssertionError.class);
        BDDAssertions.assertThat(throwable.getMessage()).isEqualTo("Passed nodes are next to given node");
    }


}
