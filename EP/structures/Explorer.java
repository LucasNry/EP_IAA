package EP.structures;

import java.util.HashMap;

public class Explorer {

    HashMap<String, Integer> start, end, position;
    int weight, value;

    public Explorer (HashMap<String, Integer> start, HashMap<String, Integer> end) {
        this.start = start;
        this.end = end;
        this.position = start;
        this.weight = 0;
        this.value = 0;

    }

    public void Move(int row, int col) {
        this.position.put("row", this.position.get("row") + row);
        this.position.put("col", this.position.get("col") + row);
    }

}