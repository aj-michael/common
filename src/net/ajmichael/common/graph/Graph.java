package net.ajmichael.common.graph;

import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Graph {

  private static final int INFINITY = Integer.MAX_VALUE;

  private List<Vertex> vertices;
  private Map<Vertex, List<Vertex>> adjMap;

  public Graph(List<Vertex> vertices, List<Edge> edges) {
    this.vertices = vertices;
    this.adjMap = new HashMap<>();
    for (Vertex v : vertices) {
      adjMap.put(v, new LinkedList<Vertex>());
    }
    for (Edge e : edges) {
      adjMap.get(e.v1).add(e.v2);
      adjMap.get(e.v2).add(e.v1);
    }
  }

  public void breadthFirstSearch(Vertex start) {
    search(start, new DequeAdder() {
      @Override
      public void add(Vertex vertex, Deque<Vertex> deque) {
        deque.addLast(vertex);
      }
    });
  }

  public void depthFirstSearch(Vertex start) {
    search(start, new DequeAdder() {
      @Override
      public void add(Vertex vertex, Deque<Vertex> deque) {
        deque.addFirst(vertex);
      }
    });
  }

  private void search(Vertex start, DequeAdder adder) {
    // Clear all the vertices.
    Iterator<Vertex> iterator = vertices.iterator();
    while (iterator.hasNext()) {
      Vertex v = iterator.next();
      v.isOpen = true;
      v.distance = INFINITY;
      v.predecessor = null;
    }

    Deque<Vertex> deque = new LinkedList<Vertex>();
    deque.addLast(start);
    while (!deque.isEmpty()) {
      Vertex v = deque.pop();
      List<Vertex> adjList = adjMap.get(v);
      for (Vertex next : adjList) {
        if (next.isOpen) {
          next.isOpen = false;
          next.distance = v.distance + 1;
          next.predecessor = v;
          adder.add(next, deque);
        }
      }
    }
  }

}
