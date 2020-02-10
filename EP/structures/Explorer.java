package EP.structures;

import EP.structures.Vertice;
import java.util.LinkedList;
import java.util.ListIterator;

public class Explorer {
    public LinkedList<Vertice> Path;
    public LinkedList<Item> items;
    public float weight, value, nOfItems;
    public double time;

    public Explorer () {
        this.weight = 0;
        this.value = 0;
        this.time = 0;
        this.Path = new LinkedList<Vertice>();
        this.items = new LinkedList<Item>();
    }

    public void addTime() {
        this.time += (1 + (this.weight / 10)) * (1 + (this.weight / 10));
    }

    public void collectItems(LinkedList<Item> items) {
        ListIterator<Item> listIter = items.listIterator();
        while(listIter.hasNext()) {
            Item i = listIter.next();
            this.value += i.value;
            this.weight += i.weight;
            this.items.add(items.poll());
            
        }
    }

}