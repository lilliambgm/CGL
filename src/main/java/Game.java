package main.java;

import java.util.Scanner;
import static main.java.Helper.intPrompter;
import static main.java.Helper.boolPrompter;

public class Game {
    private final int width;
    private final int height;
    private final int totalCells;
    private Cell[][] game;
    private boolean[][] previousGeneration;
    private int generation = 0;
    private final int weight;

    public Game () {
        Scanner input = new Scanner(System.in);

        // Ask for the width and height of the game, and writing that to the game object.
        this.width = intPrompter("What would you like the width of your game to be?");
        this.height = intPrompter("What would you like the height of your game to be?");
        this.totalCells = this.width * this.height;

        // Ask if user wants to set a weight manually
        boolean usingWeight = boolPrompter("Would you like to set the living cell weight?");

        // Set user provided weight, or use default depending on the previous prompt result.
        if (usingWeight) {
            this.weight = intPrompter("What would you like your weight to be? Enter a value between -49 and 49", -49, 49);
        } else {
            this.weight = 0;
        }

        // Create the game.
        createGame();
        // Determine all cells neighbours
        initialiseNeighbours();
        // Write the initial generation.
        writeGeneration();
        // Updating previousGeneration to carry the cell states.
        savePreviousGeneration();

        // Waiting for the user input to advance the generation, or exit the game.
        String userInput = input.nextLine();
        while (userInput.equalsIgnoreCase("next")
                || userInput.equalsIgnoreCase("n")
                || userInput.isBlank()) {
            advanceGeneration();
            userInput = input.nextLine();
        }
    }

    private void createGame() {
        // Creating the first generation by filling the gameCells matrix with cells.
        this.game = new Cell[this.height][this.width];
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                int[] location = {i, j};
                this.game[i][j] = new Cell(location, this, this.weight);
            }
        }
    }

    private void initialiseNeighbours() {
        for (Cell[] row : game) {
            for (Cell cell : row) {
                cell.calculateNeighbours();
            }
        }
    }

    public void writeGeneration() {
        this.generation++;
        System.out.printf("Generation %3$d of your randomly generated Game of Life with height %1$d and width %2$d (%4$d total cells):\n", this.height, this.width, this.generation, this.totalCells);
        for (Cell[] row : this.game) {
            for (Cell cell : row) {
                if (cell.getState()) {
                    IO.print("█");
                } else  {
                    IO.print(" ");
                }
            }
            IO.println();
        }
        IO.println("Press enter to continue, and any other key to end the program.");
    }

    private void savePreviousGeneration() {
        // System.out.println("This is the old game state");
        this.previousGeneration = new boolean[this.height][this.width];
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                this.previousGeneration[i][j] = this.game[i][j].getState();
//                if (this.game[i][j].getState()) {
//                    System.out.print("0");
//                } else {
//                    System.out.print("-");
//                }
            }
//            System.out.println();
        }
    }

    private void advanceGeneration() {
        for (Cell[] row : this.game) {
            for (Cell cell : row) {
//                System.out.println("Current cell being checked: " + Arrays.toString(cell.getLocation()));
                cell.liveNeighbourCount = 0;
                for (int[] neighbour : cell.getNeighbours()) {
//                    System.out.println("Current neighbour to be checked: " + Arrays.toString(neighbour));
                    if (this.previousGeneration[neighbour[0]][neighbour[1]]) {
                        cell.liveNeighbourCount++;
                    }
                }
                cell.updateCellState();
            }
        }
        savePreviousGeneration();
        writeGeneration();
    }

    public boolean isValidLocation(int[] toCheck) {
        return toCheck[0] >= 0 && toCheck[0] < this.height &&
               toCheck[1] >= 0 && toCheck[1] < this.width;
    }
}
