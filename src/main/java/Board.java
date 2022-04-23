import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Board {
    final public Map<Point, Node> nodes;
    final public Set<SideNumberIndicator> wallIndicators;
    final public int length;

    Board(Map<Point, Node> nodes, Set<SideNumberIndicator> wallIndicators, int length) {
        this.nodes = nodes;
        this.wallIndicators = wallIndicators;
        this.length = length;
    }
}
