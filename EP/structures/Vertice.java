package EP.structures;

import EP.structures.Item;
import EP.structures.Utilities;

public class Vertice {
    int value, row, col;
    boolean was_visited;
    Item[] items;

    public Vertice(int row, int col, int value, Item[] items) {
        this.was_visited = false;
        this.value = value;
        this.row = row;
        this.col = col;
        this.populateItems(items);
    }

    private void populateItems(Item[] items) {
        for (Item i: items) {
            if (i.row == this.row && i.col == this.col) {
                Utilities.append(items, i);
                System.out.println(items);
            }
        }
    }
}