import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class WallNumberIndicator {
    public final double posX;
    public final double posY;
    public final int maxNumberOfTouchedWalls;
    public final int numberOfIncludedWalls;
    public final Set<Point> nearPoints;

    public WallNumberIndicator(double posX, double posY, int maxNumberOfTouchedWalls, int numberOfIncludedWalls, Board board) {
        this.posX = posX;
        this.posY = posY;
        this.maxNumberOfTouchedWalls = maxNumberOfTouchedWalls;
        this.numberOfIncludedWalls = numberOfIncludedWalls;
        this.nearPoints = getAroundedPoints(board);
    }

    public WallNumberIndicator(double posX, double posY, int maxNumberOfTouchedWalls, int numberOfIncludedWalls,Set<Point> nearPoints) {
        this.posX = posX;
        this.posY = posY;
        this.maxNumberOfTouchedWalls = maxNumberOfTouchedWalls;
        this.numberOfIncludedWalls = numberOfIncludedWalls;
        this.nearPoints = nearPoints;
    }

    public WallNumberIndicator incrementIncludedWalls() {
        return new WallNumberIndicator(this.posX,this.posY,this.maxNumberOfTouchedWalls,this.numberOfIncludedWalls + 1, this.nearPoints);
    }

    public Set<Point> getAroundedPoints(Board board) {
        Set<Point> nearPoints = new HashSet<>();
        board.nodes
                .values()
                .forEach(e -> {
                    if (Math.abs((double) e.point.x - this.posX) <= 0.5 && Math.abs((double) e.point.y - this.posY) <= 0.5) {
                        nearPoints.add(e.point);
                    }
                });
        return nearPoints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WallNumberIndicator)) return false;
        WallNumberIndicator that = (WallNumberIndicator) o;
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
