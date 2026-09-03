package main.java;

import java.util.Scanner;
import static main.java.Helper.intPrompter;
import static main.java.Helper.boolPrompter;

public class Game {
    private final int width;
    private final int height;
    private Cell[][] game;
    private boolean[][] oldFrameState;
    private int frameCount = 0;
    private final int weight;

    public Game () {
        Scanner input = new Scanner(System.in);

        // Ask for the width and height of the game, and writing that to the game object.
        this.width = intPrompter("What would you like the width of your game to be?");
        this.height = intPrompter("What would you like the height of your game to be?");

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
        frameNeighbourDetermination();
        // Write the initial frame.
        writeFrame();
        // Updating oldFrameState to carry the cell states.
        updateOldFrameStates();

        // Waiting for the user input to advance the frame, or exit the game.
        String userInput = input.nextLine();
        while (userInput.equals("Next")
                || userInput.equals("next")
                || userInput.equals("N")
                || userInput.equals("n")
                || userInput.isBlank()) {
            updateGame();
            userInput = input.nextLine();
        }
    }

    private void frameNeighbourDetermination() {
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                this.game[i][j].determineNeighbours();
            }
        }
    }

    private void createGame() {
        // Creating the first frame by filling the gameCells matrix with cells.
        this.game = new Cell[this.height][this.width];
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                int[] location = {i, j};
                this.game[i][j] = new Cell(location, this, this.weight);
            }
        }
    }

    public void writeFrame() {
        this.frameCount++;
        System.out.printf("Frame %3$d of your randomly generated Game of Life with height %1$d and width %2$d:\n", this.height, this.width, this.frameCount);
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                if (this.game[i][j].getState()) {
                    System.out.print("█");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
        IO.println("Press enter to continue, and any other key to end the program.");
    }

    private void updateOldFrameStates() {
        // System.out.println("This is the old game state");
        this.oldFrameState = new boolean[this.height][this.width];
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                this.oldFrameState[i][j] = this.game[i][j].getState();
//                if (this.game[i][j].getState()) {
//                    System.out.print("0");
//                } else {
//                    System.out.print("-");
//                }
            }
//            System.out.println();
        }
    }

    private void updateGame() {
        for (Cell[] row : this.game) {
            for (Cell cell :row) {
//                System.out.println("Current cell being checked: " + Arrays.toString(cell.getLocation()));
                cell.liveNeighbourCount = 0;
                for (int[] neighbour : cell.getNeighbours()) {
//                    System.out.println("Current neighbour to be checked: " + Arrays.toString(neighbour));
                    if (this.oldFrameState[neighbour[0]][neighbour[1]]) {
                        cell.liveNeighbourCount++;
                    }
                }
                cell.updateCellState();
            }
        }
        updateOldFrameStates();
        writeFrame();
    }

    public boolean isValidLocation(int[] toCheck) {
        return toCheck[0] >= 0 && toCheck[0] < this.height &&
               toCheck[1] >= 0 && toCheck[1] < this.width;
    }
}
