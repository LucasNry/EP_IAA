package EP.structures;

import java.util.LinkedList;
import EP.structures.Vertice;;

public class Graph {
    int nOfV;
    LinkedList<Vertice> connections[];

    public Graph(int nOfV) {
        this.nOfV = nOfV;

        for(int i=0; i < this.nOfV; i++) {
            connections[i] = new LinkedList<Vertice>();
        }
    }
}
