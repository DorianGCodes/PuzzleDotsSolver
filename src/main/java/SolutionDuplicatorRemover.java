import com.google.common.collect.Lists;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class SolutionDuplicatorRemover {

    static List<Solution> getSolutionWithoutDuplicates(List<Solution> solutions) {
        AtomicInteger couter = new AtomicInteger(0);
        Set<ConnectionSet> collect = solutions
                .stream()
                .map(solution -> {
                    var list = Lists.newArrayList(solution.solutionSteps);
                    list.remove(0);
                    ConnectionSet connectionSet = new ConnectionSet(new HashSet<>(), couter.get());
                    IntStream
                            .range(0, Math.min(list.size(), solution.solutionSteps.size()))
                            .forEach(e -> connectionSet.connections.add(new Side(solution.solutionSteps.get(e), list.get(e))));
                    couter.incrementAndGet();
                    return connectionSet;
                }).collect(Collectors.toSet());
        return collect.stream().map(e -> solutions.get(e.indexOfSolution)).collect(Collectors.toList());
    }
}
