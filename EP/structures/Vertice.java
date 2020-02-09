package EP.structures;

import java.util.LinkedList;

import EP.structures.Item;

public class Vertice {
    public int value, row, col;
    public boolean was_visited;
    public boolean isWalkable;
    public LinkedList<Item> items;

    public Vertice(int row, int col, String symbol, Item[] items) {
        this.was_visited = false;
        this.row = row;
        this.col = col;
        this.items = new LinkedList<Item>();
        this.setIsWalkable(symbol);
        this.populateItems(items);
    }

    private void populateItems(Item[] items) {
        for (Item i: items) {
            if (i.row == this.row && i.col == this.col) {
                this.items.add(i);
            }
        }
    }

    private void setIsWalkable(String symbol) {
        if (symbol.equals(".")) {
            this.isWalkable = true;
        } else {
            this.isWalkable = false;
        }
    }
}