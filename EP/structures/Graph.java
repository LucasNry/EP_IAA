package EP.structures;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.HashMap;
import EP.structures.Vertice;

public class Graph {
    private HashMap<String, Vertice> vertices;
    public HashMap<Vertice, LinkedList<Vertice>> connections;

    public Graph() {
        this.vertices = new HashMap<String, Vertice>();
        this.connections = new HashMap<Vertice, LinkedList<Vertice>>();
    }

    public void insert(int row, int col, Vertice v) {
        String coord = String.format("%s%s", row, col);
        this.vertices.put(coord, v);
    }

    public Vertice fetch(int row, int col) {
        String coord = String.format("%s%s", row, col);
        Vertice v = this.vertices.get(coord);

        return v;
    }

    public void setConnections(String[][] Maze) {
        for(HashMap.Entry<Vertice, LinkedList<Vertice>> connection: this.connections.entrySet()) {
            Vertice v = connection.getKey();
            LinkedList<Vertice> vList = connection.getValue();
            Vertice upV = this.fetch(v.row - 1, v.col);
            Vertice downV = this.fetch(v.row + 1, v.col);
            Vertice leftV = this.fetch(v.row, v.col - 1);
            Vertice rightV = this.fetch(v.row, v.col + 1);

            if(leftV != null && !leftV.equals(v)) {
                vList.add(leftV);
            }

            if(rightV != null && !rightV.equals(v)) {
                vList.add(rightV);
            }

            if(upV != null && !upV.equals(v)) {
                vList.add(upV);
            }

            if(downV != null && !downV.equals(v)) {
                vList.add(downV);
            }
        }
    }

    public void printConnections () {
        for(HashMap.Entry<Vertice, LinkedList<Vertice>> entry: this.connections.entrySet()) {
            System.out.printf("Parent: %d %d\n", entry.getKey().row, entry.getKey().col);
            ListIterator<Vertice> list = entry.getValue().listIterator();
            while(list.hasNext()) {
                Vertice n = list.next();
                System.out.printf("%d %d\n", n.row, n.col);
            }
        }
    }

    public void findPath (HashMap<String, Integer> startCoord, HashMap<String, Integer> endCoord, Explorer explorer) {
        LinkedList<Vertice> queue = new LinkedList<Vertice>();
        Vertice origin = this.fetch(startCoord.get("row"), startCoord.get("col"));
        Vertice dest = this.fetch(endCoord.get("row"), endCoord.get("col"));

        origin.was_visited = true;
        queue.add(origin);

        while (queue.size() != 0) {
            origin = queue.poll();
            explorer.Path.add(origin);
            explorer.collectItems(origin.items);
            explorer.addTime();
  
            ListIterator<Vertice> i = this.connections.get(origin).listIterator();
            while (i.hasNext()) {
                Vertice n = i.next();
                if(n.equals(dest)) {
                    explorer.Path.add(n);
                    explorer.addTime();
                    explorer.collectItems(n.items);
                    return;
                }
                if (!n.was_visited && n.isWalkable) {
                    n.was_visited = true;
                    queue.add(n);
                }
            }
        }
    }
}
