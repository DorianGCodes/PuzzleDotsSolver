import java.util.UUID;

class VisitNodeEvent {
    final Node nodeToVisit;
    VisitNodeEvent(Node nodeToVisit) {
        this.nodeToVisit = nodeToVisit;
    }

    UUID getNodeId() {
        return  this.nodeToVisit.id;
    }
}
