import java.util.List;
import java.util.Set;
import java.util.UUID;

public class Tuple {
    public final Node node;
    public final Set<UUID> visited;
    public final List<Node> visitOrder;
    public final Set<WallNumberIndicator> wallNumberIndicators;

    public Tuple(Node node, Set<UUID> visited, List<Node> visitOrde, Set<WallNumberIndicator> wallNumberIndicators) {
        this.node = node;
        this.visited = visited;
        this.visitOrder = visitOrde;
        this.wallNumberIndicators = wallNumberIndicators;
    }
}
