package model.utils;

import model.graph.Edge;

@FunctionalInterface
public interface EdgeOperation {
    void operate(Edge edge);
}
