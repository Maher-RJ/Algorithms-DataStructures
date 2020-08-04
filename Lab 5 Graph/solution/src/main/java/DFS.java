import se.kth.id1020.Edge;
import se.kth.id1020.Graph;
import se.kth.id1020.Vertex;

import java.util.Stack;

class DFS {

    static void dfs(Graph g, Stack<Vertex> vertices, boolean[] coloured) {
        while (!vertices.isEmpty()) {
            Vertex current = vertices.pop();
            coloured[current.id] = true; // true = colour
            for (Edge E : g.adj(current.id)) { // tar ut alla kopplingar till dem närmaste noderna
                int nextVertexId = E.to;
                if (!coloured[nextVertexId]) {
                    vertices.push(g.vertex(nextVertexId));
                }
            }
        }
    }
}
