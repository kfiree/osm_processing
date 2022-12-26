package model.utils;

import model.graph.Node;

@FunctionalInterface
public interface NodeOperation {
    void operate(Node node);
}
