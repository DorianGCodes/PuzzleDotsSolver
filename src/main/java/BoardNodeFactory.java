import java.util.ArrayList;
import java.util.List;

class BoardNodeFactory {

    static List<Node> generateNodesForBoard(int numberOfDotsOnSide) {
        List<Node> nodes = new ArrayList<>(numberOfDotsOnSide * numberOfDotsOnSide);
        for (int i = 0; i < numberOfDotsOnSide; i++) {
            for (int j = 0; j < numberOfDotsOnSide; j++) {
                nodes.add(new Node(i,j));
            }
        }
        return nodes;
    }
}
