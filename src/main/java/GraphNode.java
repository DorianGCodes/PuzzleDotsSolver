import java.util.ArrayList;
import java.util.List;

public class GraphNode {
    Node node;
    List<Node> nextNodes;

    public GraphNode (Node node) {
        this.node = node;
        this.nextNodes = new ArrayList<>();
    }

    public void addNode(Node node) {
        this.nextNodes.add(node);
    }

}
