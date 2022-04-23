import java.util.Objects;

class Side {
    Node startNode;
    Node endNode;


    Side(Node startNode, Node endNode) {
        this.startNode = startNode;
        this.endNode = endNode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Side)) return false;
        Side wall = (Side) o;
        return Objects.equals(startNode, wall.startNode) &&
                Objects.equals(endNode, wall.endNode) ||
                Objects.equals(startNode, wall.endNode) && Objects.equals(endNode, wall.startNode);
    }

    @Override
    public int hashCode() {
        return 17 * (startNode.hashCode() + endNode.hashCode());
    }
}
