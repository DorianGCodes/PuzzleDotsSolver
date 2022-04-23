import java.util.Map;
import java.util.stream.Collectors;

class NodeConnectionsGenerator {

    static void generateNodesConnections(Map<Point, Node> nodes) {
        nodes.forEach((point,node) -> {
            generateForEachNodeConnection(node,nodes);
        });
    }

    static void generateForEachNodeConnection(
            Node givenNodeToGenerateConnections,
            Map<Point,Node> nodesOnBoard) {
        var connectedNodes = nodesOnBoard
                .values()
                .stream()
                .filter(givenNodeToGenerateConnections::isNextTo)
                .collect(Collectors.toList());
        givenNodeToGenerateConnections.updateConnections(connectedNodes);
    }
}
