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

            if(leftV != null && !leftV.equals(v) && leftV.isWalkable) {
                vList.add(leftV);
            }

            if(rightV != null && !rightV.equals(v) && rightV.isWalkable) {
                vList.add(rightV);
            }

            if(upV != null && !upV.equals(v) && upV.isWalkable) {
                vList.add(upV);
            }

            if(downV != null && !downV.equals(v) && downV.isWalkable) {
                vList.add(downV);
            }
        }
    }

    public void printConnections (Vertice v) {
        ListIterator<Vertice> list = this.connections.get(v).listIterator();
        System.out.printf("Parent %d %d\n", v.row, v.col);
        while(list.hasNext()) {
            Vertice n = list.next();
            System.out.printf("%d %d\n", n.row, n.col);
        }
    }

    public void findMinPath (HashMap<String, Integer> startCoord, HashMap<String, Integer> endCoord, Explorer explorer) {
        LinkedList<Vertice> queue = new LinkedList<Vertice>();
        Vertice origin = this.fetch(startCoord.get("row"), startCoord.get("col"));
        Vertice dest = this.fetch(endCoord.get("row"), endCoord.get("col"));

        origin.was_visited = true;
        queue.add(origin);

        while (queue.size() != 0) {
            origin = queue.poll();
            explorer.Path.add(origin);
            explorer.addTime();
            explorer.collectItems(origin.items);
            
            LinkedList<Vertice> vList = this.connections.get(origin); 
            ListIterator<Vertice> vIter = vList.listIterator();
            int minRowDist = Math.abs(dest.row - origin.row);
            int minColDist = Math.abs(dest.col - origin.col);
            Vertice minV = null;
            while (vIter.hasNext()) {
                Vertice n = vIter.next();

                if(n.equals(dest)) { // Destination was reached
                    explorer.Path.add(n);
                    explorer.addTime();
                    explorer.collectItems(n.items);
                    return;
                }

                int rowDistance = Math.abs(n.row - dest.row);
                int colDistance = Math.abs(n.col - dest.col);

                if (
                    rowDistance <= minRowDist
                    && colDistance <= minColDist
                    && !n.was_visited
                   ) {
                    minRowDist = Math.abs(n.row - dest.row);
                    minColDist = Math.abs(n.col - dest.col);
                    minV = n;
                }
            }
            minV.was_visited = true;
            queue.add(minV);
        }
    }
    //! Still a WIP
    public void findMaxPath (HashMap<String, Integer> startCoord, HashMap<String, Integer> endCoord, Explorer explorer) {
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
            
            LinkedList<Vertice> vList = this.connections.get(origin); 
            ListIterator<Vertice> i = vList.listIterator();
            Vertice maxV = null;
            int maxRowDist = -1;
            int maxColDist = -1;
            while (i.hasNext()) {
                Vertice n = i.next();

                if(n.equals(dest)) { // Destination was reached
                    explorer.Path.add(n);
                    explorer.addTime();
                    explorer.collectItems(n.items);
                    return;
                }

                int rowDistance = Math.abs(n.row - dest.row);
                int colDistance = Math.abs(n.col - dest.col);
                if (
                    rowDistance >= maxRowDist
                    && colDistance >= maxColDist
                    && !n.was_visited 
                   ) {
                    maxRowDist = Math.abs(n.row - dest.row);
                    maxColDist = Math.abs(n.col - dest.col);
                    maxV = n;
                }
            }
            System.out.printf("%d %d\n", maxV.row, maxV.col);
            System.out.println("WALK");
            maxV.was_visited = true;
            queue.add(maxV);
        }
    }

    public void findMostValPath (HashMap<String, Integer> startCoord, HashMap<String, Integer> endCoord, Explorer explorer, Item[] items) {
        try {
            HashMap<String, Integer> startItem = new HashMap<String, Integer>() {{
                put("row", items[0].row);
                put("col", items[0].col);
            }};
            HashMap<String, Integer> endItem = null;

            this.findMinPath(startCoord, startItem, explorer);

            for(int i = 0; i < items.length - 1; i++) {
                int index = i;
                startItem = new HashMap<String, Integer>() {{
                    put("row", items[index].row);
                    put("col", items[index].col);
                }};
                endItem = new HashMap<String, Integer>() {{
                    put("row", items[index + 1].row);
                    put("col", items[index + 1].col);
                }};
    
                this.findMinPath(startItem, endItem, explorer);
            }
    
            this.findMinPath(endItem, endCoord, explorer);
        } catch (Exception e) {
            // System.out.println("Your input doesn't have any items");
            System.out.println(e);
        }
    }
}
