package EP;

import EP.structures.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;


public class Main {
    public static void main(String[] args) throws Exception {
        Path filePath = Paths.get(String.format("EP/resources/%s", args[0]));
        int method = Integer.parseInt(args[1]); 
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
        
        int nOfItems = Integer.parseInt(inputLines[Integer.parseInt(mazeInfo.get("rows")) + 1]);
        int endOfMaze = Integer.parseInt(mazeInfo.get("rows")) + 2;
        String[] rawMaze = Arrays.copyOfRange(inputLines, 1, Integer.parseInt(mazeInfo.get("rows")) + 1);

        String[][] formattedRawMaze = formatMaze(rawMaze, Integer.parseInt(mazeInfo.get("rows")), Integer.parseInt(mazeInfo.get("cols")));
        
        Item[] items = makeItems(nOfItems, endOfMaze, inputLines);

        Graph maze = makeMaze(formattedRawMaze, Integer.parseInt(mazeInfo.get("rows")), Integer.parseInt(mazeInfo.get("cols")), items);
        maze.setConnections(formattedRawMaze);

        HashMap<String, Integer> startingCoordinates = new HashMap<>();
        HashMap<String, Integer> endingCoordinates = new HashMap<>();

        setCoordinates(startingCoordinates, endingCoordinates, inputLines);

        Explorer explorer = new Explorer();

        switch (method) {
            case 1:
                maze.findMinPath(startingCoordinates, endingCoordinates, explorer);        
                break;
            case 2:
                maze.findMaxPath(startingCoordinates, endingCoordinates, explorer);
                break;
            case 3:
                maze.findMostValPath(startingCoordinates, endingCoordinates, explorer, items);
                break;
            default:
                System.out.println("Please input a search method after the maze filename");;
            }

        printResults(explorer);
        
    }

    public static Item[] makeItems(int nOfItems, int endOfMaze, String[] inputLines) {
        Item[] items = new Item[nOfItems];

        for (int i = 0; i < nOfItems; i++) {
            String[] lineInfo = inputLines[endOfMaze + i].split(" ");
            Item tempI = new Item(Integer.parseInt(lineInfo[0]), Integer.parseInt(lineInfo[1]), Integer.parseInt(lineInfo[2]), Integer.parseInt(lineInfo[3]));
            items[i] = tempI;
        }

        return items;
    }
    
    public static Graph makeMaze (String[][] Maze, int rows, int cols, Item[] items) {
        Graph navMaze = new Graph();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Vertice v = new Vertice(i, j, Maze[i][j], items);
                navMaze.insert(i, j, v);
                navMaze.connections.put(v, new LinkedList<Vertice>());
            }
        }
        
        return navMaze;
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

    private static String[][] formatMaze (String[] rawMaze, int rows, int cols) {
        String[][] formattedMaze = new String[rows][cols];
        for (int i = 0; i < rawMaze.length; i++) {
            String[] elements = rawMaze[i].split("");
            for (int j = 0; j < cols; j++) {
                formattedMaze[i][j] = elements[j];
            }
        }

        return formattedMaze;
    }

    public static void printResults(Explorer explorer) {
        System.out.printf("%d %.2f\n", explorer.Path.size(), explorer.time);

        ListIterator<Vertice> pList = explorer.Path.listIterator();
        while(pList.hasNext()) {
            Vertice v = pList.next();
            System.out.printf("%d %d\n", v.row, v.col);
        }

        System.out.printf("%d %.0f %.0f\n", explorer.items.size(), explorer.value, explorer.weight);

        ListIterator<Item> iList = explorer.items.listIterator();
        while(iList.hasNext()) {
            Item i = iList.next();
            System.out.printf("%d %d\n", i.row, i.col);
        }

        if(explorer.error.length() > 0) {
            System.out.println(explorer.error);
        }
    }
}