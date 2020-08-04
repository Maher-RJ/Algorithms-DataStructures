import se.kth.id1020.Edge;
import se.kth.id1020.Graph;
import se.kth.id1020.Vertex;

import java.util.ArrayList;

public class Dijkstra {

    //returns the lowest distance between start and end
    public static double dijkstra(Graph g, Vertex start, Vertex end, boolean weighted) {
        double[] distances = new double[g.numberOfVertices()];
        for (int pos = 0; pos < distances.length; pos++) {
            distances[pos] = Double.MAX_VALUE;
        }
        distances[start.id] = 0;

        ArrayList<Vertex> vertices = new ArrayList<>(); // our "stack"
        vertices.add(start); // add start node to the "stack"
        while (vertices.size() != 0) {  // while the stack is not empty
            Vertex lowestCostVertex = vertices.get(0); // we assume that the first index has the lowest cost

            for (int pos = 1; pos < vertices.size(); pos++) {
                Vertex current = vertices.get(pos);
                if (distances[lowestCostVertex.id] > distances[current.id]) { // if it's not the lowest cost
                    lowestCostVertex = current;
                }
            }
            vertices.remove(lowestCostVertex); // remove the real lowest cost
            for (Edge e : g.adj(lowestCostVertex.id)) { // get all the edges to the vertex
                Vertex nextVertex = g.vertex(e.to);
                double distance = distances[lowestCostVertex.id]; // shortest distance
                if (weighted) {
                    //if weighted, we use distance fn
                    distance = distance + nextVertex.distance(lowestCostVertex); // adding the cost to the shortest distance
                } else {
                    distance++; // adding 1 to show we add an edge
                }

                //check if there is a faster way, if we update
                if (distance < distances[nextVertex.id]) { // if there is a faster way
                    distances[nextVertex.id] = distance;
                    if (!vertices.contains(nextVertex)) { // if it's not in the stack, add it
                        vertices.add(nextVertex);
                    }
                }
            }
        }
        return distances[end.id];
    }
}
