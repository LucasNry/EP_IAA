package EP;

import EP.structures.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;


class Main {
    public static void main(String[] args) throws Exception {
        Path filePath = Paths.get(args[0]);
        // int metodo = Integer.parseInt(args[1]); 
        String fileString = "";
        try {
            fileString = new String(Files.readAllBytes(filePath));
        } catch (IOException e) {
            System.err.println(e);
            throw new Exception("There was a problem reading the file");
        }

        String[] inputLines = fileString.split("\n");
        HashMap<String, String> mazeInfo = new HashMap<>();

        String[] rowsAndCols = inputLines[0].split(" ");
        mazeInfo.put("rows", rowsAndCols[0].toString());
        mazeInfo.put("cols", rowsAndCols[1].toString());

        String[] rawMaze = Arrays.copyOfRange(inputLines, 1, Integer.parseInt(mazeInfo.get("rows")) + 1);

        Boolean[][] navMaze = makeNavigableMaze(
            rawMaze,
            Integer.parseInt(mazeInfo.get("rows")),
            Integer.parseInt(mazeInfo.get("cols"))
        );


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
    
    private static Boolean[][] makeNavigableMaze (String[] rawMaze, int rows, int cols) {
        Boolean[][] navMaze = new Boolean[rows][cols];

        for (int i = 0; i < rows; i++) {
            String[] colItems = rawMaze[i].split("");
            for (int j = 0; j < cols; j++) {
                if (colItems[j].equals(".")) navMaze[i][j] = true;
                else navMaze[i][j] = false;
            }
        }
        
        return navMaze;
    }
    
    private static < E > int indexOf(E element, E[] array){
        E[] temp = Arrays.copyOf(array, (array.length + 1));
        temp[array.length] = element;

        int i = 0;
        while(!temp[i].equals(element)) i++;

        if (i != array.length) return i;
        else return -1;
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