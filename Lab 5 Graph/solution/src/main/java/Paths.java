import se.kth.id1020.DataSource;
import se.kth.id1020.Graph;
import se.kth.id1020.Vertex;


public class Paths {
    public static void main(String[] args) {
        Graph g = DataSource.load();
        System.out.println();
        System.out.println("Is the graph fully connected? " + IsFullyConnected.isFullyConnected(g));
        System.out.println("Amount of sub graphs: " + SubGraphs.subGraphCount(g));
        Vertex Renyn = null;
        Vertex Parses = null;
        for(Vertex v : g.vertices()) {
            if(v.label.equals("Renyn")) {
                Renyn = v;
            }
            if(v.label.equals("Parses")) {
                Parses = v;
            }
        }
        System.out.println("The shortest path: " +Dijkstra.dijkstra(g, Renyn, Parses, false));
        System.out.println("The shortest path with its cost: " + Dijkstra.dijkstra(g, Renyn, Parses, true));
    }

}