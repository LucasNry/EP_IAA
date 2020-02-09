package EP.structures;

import EP.structures.Vertice;
import java.util.LinkedList;
import java.util.ListIterator;
import java.lang.Math;

public class Explorer {
    public LinkedList<Vertice> Path;
    public LinkedList<Item> items;
    public int weight, value, nOfItems;
    public float time;

    public Explorer () {
        this.weight = 0;
        this.value = 0;
        this.time = 0;
        this.Path = new LinkedList<Vertice>();
        this.items = new LinkedList<Item>();
    }

    public void addTime() {
        this.time += Math.pow(1 + (this.weight / 10), 2);
    }

    public void collectItems(LinkedList<Item> items) {
        ListIterator<Item> listIter = items.listIterator();
        while(listIter.hasNext()) {
            Item i = listIter.next();
            this.value += i.value;
            this.weight += i.weight;
            this.items.add(i);
        }
    }

}