package com.puzzlesolver;

import java.util.HashSet;
import java.util.Objects;

class ConnectionSet {
    ConnectionSet(HashSet<Side> connections, int indexOfSolution) {
        this.connections = connections;
        this.indexOfSolution = indexOfSolution;
    }

    final HashSet<Side> connections;
    final int indexOfSolution;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ConnectionSet)) return false;
        ConnectionSet that = (ConnectionSet) o;
        return Objects.equals(connections, that.connections);
    }

    @Override
    public int hashCode() {
        return Objects.hash(connections);
    }
}
