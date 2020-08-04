import se.kth.id1020.Graph;
import se.kth.id1020.Vertex;

import java.util.Stack;

public class SubGraphs {
    public static int subGraphCount(Graph g) {

        Stack<Vertex> vertices = new Stack<>();
        boolean[] coloured = new boolean[g.numberOfVertices()];
        int subGraphs = 0;
        boolean toContinue = true; //If we have uncoloured vertices we need to continue, like in beginning.
        Vertex start = null;

        while (toContinue) { // loops until we have uncolored vertices
            toContinue = false; // we assume the loop will end, because we think the graph is fully connected


            for (int pos = 0; pos < coloured.length; pos++) {
                if (!coloured[pos]) {
                    start = g.vertex(pos);
                }
            }
            vertices.push(start); //lägger till startvertex i stacken

            DFS.dfs(g, vertices, coloured);
            subGraphs++; //when the vertices is empty, then we found a subgraph!

            //need to check if we found all subgraphs at this point
            for (int pos = 0; pos < coloured.length; pos++) {
                if (!coloured[pos]) {
                    //if we have non-colored nodes, we at least have one more subgraph!
                    //if we find one, we must continue (we were wrong in the start about fully connected.
                    toContinue = true;
                }
            }
        }
        return subGraphs; //amount of subgraphs found
    }
}
