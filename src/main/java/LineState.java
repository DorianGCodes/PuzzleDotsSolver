import java.util.ArrayList;
import java.util.List;

public class LineState {
    List<Wall> walls = new ArrayList<>();

    void addWall(Wall wall) {
        this.walls.add(wall);
    }

    void printSolution() {
        System.out.println("Print");
        walls.forEach(e -> {
            var printString = String.format("Starting Node Cord %s, End Node Cord %s",
                    String.valueOf(e.startNode.point.x) + " " + String.valueOf(e.startNode.point.y),
                    String.valueOf(e.endNode.point.x) + " " + String.valueOf(e.endNode.point.y)
            );
            System.out.println(printString);
        });
    }


}
