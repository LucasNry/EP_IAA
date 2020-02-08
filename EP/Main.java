package EP;

import EP.structures.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;


public class Main {
    public static void main(String[] args) throws Exception {
        Path filePath = Paths.get(String.format("EP/resources/%s", args[0]));
        // int metodo = Integer.parseInt(args[1]); 
        String fileString = "";
        try {
            fileString = new String(Files.readAllBytes(filePath));
        } catch (IOException e) {
            System.err.println(e);
            throw new Exception(e);
        }

        String[] inputLines = fileString.split("\n");
        HashMap<String, String> mazeInfo = new HashMap<>();

        String[] rowsAndCols = inputLines[0].split(" ");
        mazeInfo.put("rows", rowsAndCols[0].toString());
        mazeInfo.put("cols", rowsAndCols[1].toString());

        String[] rawMaze = Arrays.copyOfRange(inputLines, 1, Integer.parseInt(mazeInfo.get("rows")) + 1);

        // Boolean[][] navMaze = makeNavigableMaze(
        //     rawMaze,
        //     Integer.parseInt(mazeInfo.get("rows")),
        //     Integer.parseInt(mazeInfo.get("cols"))
        // );

        Graph maze = this.makeMaze();


        HashMap<String, Integer> startingCoordinates = new HashMap<>();

        HashMap<String, Integer> endingCoordinates = new HashMap<>();

        setCoordinates(startingCoordinates, endingCoordinates, inputLines);

        int nOfItems = Integer.parseInt(inputLines[Integer.parseInt(mazeInfo.get("rows")) + 1]);

        
        int endOfMaze = Integer.parseInt(mazeInfo.get("rows")) + 2;
        
        Item[] items = makeItems(nOfItems, endOfMaze, inputLines);
        
    }

    private static Item[] makeItems(int nOfItems, int endOfMaze, String[] inputLines) {
        Item[] items = new Item[nOfItems];

        for (int i = 0; i < nOfItems; i++) {
            String[] lineInfo = inputLines[endOfMaze + i].split(" ");
            Item tempI = new Item(Integer.parseInt(lineInfo[0]), Integer.parseInt(lineInfo[1]), Integer.parseInt(lineInfo[2]), Integer.parseInt(lineInfo[3]));
            items[i] = tempI;
        }

        return items;
        
    }
    
    private static Graph makeMaze (String[] rawMaze, int rows, int cols) {
        Graph navMaze = new Graph(rows * cols);
        // WIP

        
        
        return navMaze;
    }

    public static Item fetchItem(int row, int col, Item[] items) {
        for (Item item : items) {
            if(item.row == row && item.col == col) return item;
        }

        return null;
    }

    private static void setCoordinates (
        HashMap<String,Integer> startingCoordinates,
        HashMap<String, Integer> endingCoordinates,
        String[] inputLines) {

        startingCoordinates.put("row", Integer.parseInt(inputLines[inputLines.length - 2].split(" ")[0]));

        startingCoordinates.put("col", Integer.parseInt(inputLines[inputLines.length - 2].split(" ")[1]));

        endingCoordinates.put("row", Integer.parseInt(inputLines[inputLines.length - 1].split(" ")[0]));

        endingCoordinates.put("col", Integer.parseInt(inputLines[inputLines.length - 1].split(" ")[1]));
    }
}