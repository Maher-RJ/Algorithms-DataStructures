import se.kth.id1020.Graph;
import se.kth.id1020.Vertex;

import java.util.Stack;

public class IsFullyConnected {


    public static boolean isFullyConnected(Graph g) {

        Stack<Vertex> vertices = new Stack<Vertex>(); //
        boolean[] coloured = new boolean[g.numberOfVertices()];
        Vertex start = g.vertex(0);
        vertices.push(start); //lägger till startvertex i stacken
        DFS.dfs(g, vertices, coloured);

        for (int pos = 0; pos < coloured.length; pos++) {
            if (!coloured[pos]) {
                return false; // G is not fully connected
            }
        }
        return true; // G is fully connected
    }
}
