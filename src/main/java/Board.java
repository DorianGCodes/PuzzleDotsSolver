import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Board {
    public Map<Point, Node> nodes;
    public Set<WallNumberIndicator> wallIndicators;
    public int length;
}
