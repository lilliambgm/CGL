package main.java;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    private final Game game;
    private boolean state;
    private final int[] location;
    private int[][] neighbours;
    protected int liveNeighbourCount;

    /**
     * Constructs the cell object, with which the game grid will be filled
     * @param location the location of the current cell
     * @param game the reference of the current game for when non-static game methods are called
     * @param weight the living cell weight, used as bias for cell generation
     */
    public Cell(int[] location, Game game, int weight) {
        this.location = location;
        this.game = game;
        setStarterState(weight);
    }

    private void setStarterState(int weight) {
        double random = Math.random();
        this.state = random < 0.5 + ((double) weight / 100);
    }

    public void determineNeighbours() {
        List<int[]> neighbourList = new ArrayList<>();

        for (int rowOffset = -1; rowOffset <= 1; rowOffset++) {
            for (int colOffset = -1; colOffset <= 1; colOffset++) {
                if (rowOffset == 0 && colOffset == 0) {
                    continue;
                }

                int[] toCheck = {this.location[0] + rowOffset, this.location[1] + colOffset};

                if (game.isValidLocation(toCheck)) {
                    neighbourList.add(toCheck);
                }
            }
        }

        this.neighbours = neighbourList.toArray(new int[0][]);
    }

    public void updateCellState() {
        if (this.state) {
            if (this.liveNeighbourCount < 2 || this.liveNeighbourCount > 3) {
                this.state = false;
            }
        } else {
            if (this.liveNeighbourCount == 3) {
                this.state = true;
            }
        }
    }

    //<editor-fold desc="Getters">
    public boolean getState() {
        return this.state;
    }

    public int[][] getNeighbours() {
        return neighbours;
    }

    public int[] getLocation() {
        return location;
    }
    //</editor-fold>
}
