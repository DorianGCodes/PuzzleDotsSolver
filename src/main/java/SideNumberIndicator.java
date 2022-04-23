import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

class SideNumberIndicator {
    final double posX;
    final double posY;
    final int maxNumberOfTouchedWalls;
    final int numberOfIncludedWalls;
    final Set<Point> nearPoints;

    SideNumberIndicator(double posX, double posY, int maxNumberOfTouchedWalls, Map<Point,Node> board) {
        this.posX = posX;
        this.posY = posY;
        this.maxNumberOfTouchedWalls = maxNumberOfTouchedWalls;
        this.numberOfIncludedWalls = 0;
        this.nearPoints = getPointsAround(board);
    }

    private SideNumberIndicator(double posX, double posY, int maxNumberOfTouchedWalls, int numberOfIncludedWalls, Set<Point> nearPoints) {
        this.posX = posX;
        this.posY = posY;
        this.maxNumberOfTouchedWalls = maxNumberOfTouchedWalls;
        this.numberOfIncludedWalls = numberOfIncludedWalls;
        this.nearPoints = nearPoints;
    }

    SideNumberIndicator incrementIncludedWalls() {
        return new SideNumberIndicator(this.posX,this.posY,this.maxNumberOfTouchedWalls,this.numberOfIncludedWalls + 1, this.nearPoints);
    }

    Set<Point> getPointsAround(Map<Point,Node> nodesByPoint) {
        Set<Point> nearPoints = new HashSet<>();
        nodesByPoint
                .values()
                .forEach(node -> {
                    if (Math.abs((double) node.point.x - this.posX) <= 0.5 && Math.abs((double) node.point.y - this.posY) <= 0.5) {
                        nearPoints.add(node.point);
                    }
                });
        return nearPoints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SideNumberIndicator)) return false;
        SideNumberIndicator that = (SideNumberIndicator) o;
        return Double.compare(that.posX, posX) == 0 &&
                Double.compare(that.posY, posY) == 0 &&
                maxNumberOfTouchedWalls == that.maxNumberOfTouchedWalls &&
                numberOfIncludedWalls == that.numberOfIncludedWalls &&
                Objects.equals(nearPoints, that.nearPoints);
    }

    @Override
    public int hashCode() {
        return Objects.hash(posX, posY, maxNumberOfTouchedWalls, numberOfIncludedWalls, nearPoints);
    }
}
