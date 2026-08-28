package main.java;

import java.lang.Math;
import java.util.Arrays;

public class Cell {
    private boolean state;
    private final int[] location;
    private int[][] neighbours;
    protected int liveNeighbourCount;

    public Cell(int[] location) {
        this.location = location;
        setStarterState();
    }

    private void setStarterState() {
        double random = Math.random();
        this.state = !(random < 0.5);
    }

    public void determineNeighbours(int max_height, int max_width) {
        if (this.location[0] == 0 && this.location[1] == 0) {
            // System.out.println("This is the top left corner.");
            this.neighbours = new int[][] {
                    {this.location[0], this.location[1] + 1},
                    {this.location[0] + 1, this.location[1]},
                    {this.location[0] + 1, this.location[1] + 1}
            };
        } else if (this.location[0] == 0 && this.location[1] == max_width - 1) {
            // System.out.println("This is the top right corner");
            this.neighbours = new int[][] {
                    {this.location[0], this.location[1] - 1},
                    {this.location[0] + 1, this.location[1] - 1},
                    {this.location[0] + 1, this.location[1]}
            };
        } else if (this.location[0] == max_height - 1 && this.location[1] == 0) {
            // System.out.println("This is the bottom left corner");
            this.neighbours = new int[][] {
                    {this.location[0] - 1, this.location[1]},
                    {this.location[0] - 1, this.location[1] + 1},
                    {this.location[0], this.location[1] + 1}
            };
        } else if (this.location[0] == max_height - 1 && this.location[1] == max_width - 1) {
            // System.out.println("This is the bottom right corner");
            this.neighbours = new int[][] {
                    {this.location[0] - 1, this.location[1] - 1},
                    {this.location[0] - 1, this.location[1]},
                    {this.location[0], this.location[1] - 1}
            };
        } else if (this.location[0] == 0) {
            // System.out.println("this is the top row.");
            this.neighbours = new int[][] {
                    {this.location[0], this.location[1] - 1},
                    {this.location[0], this.location[1] + 1},
                    {this.location[0] + 1, this.location[1] - 1},
                    {this.location[0] + 1, this.location[1]},
                    {this.location[0] + 1, this.location[1] + 1}
            };
        } else if (this.location[1] == 0) {
            // System.out.println("This is the left column");
            this.neighbours = new int[][] {
                    {this.location[0] - 1, this.location[1]},
                    {this.location[0] - 1, this.location[1] + 1},
                    {this.location[0], this.location[1] + 1},
                    {this.location[0] + 1, this.location[1]},
                    {this.location[0] + 1, this.location[1] + 1}
            };
        } else if (this.location[1] == max_width - 1) {
            // System.out.println("This is the right column");
            this.neighbours = new int[][] {
                    {this.location[0] - 1, this.location[1]},
                    {this.location[0] - 1, this.location[1] - 1},
                    {this.location[0], this.location[1] - 1},
                    {this.location[0] + 1, this.location[1]},
                    {this.location[0] + 1, this.location[1] - 1}
            };
        } else if (this.location[0] == max_height - 1) {
            // System.out.println("This is the bottom row");
            this.neighbours = new int[][] {
                    {this.location[0], this.location[1] - 1},
                    {this.location[0], this.location[1] + 1},
                    {this.location[0] - 1, this.location[1] - 1},
                    {this.location[0] - 1, this.location[1]},
                    {this.location[0] - 1, this.location[1] + 1}
            };
        } else {
            // System.out.println("This is not an edge");
            this.neighbours = new int[][] {
                    {this.location[0] - 1, this.location[1] -1},
                    {this.location[0] - 1, this.location[1]},
                    {this.location[0] - 1, this.location[1] + 1},
                    {this.location[0], this.location[1] - 1},
                    {this.location[0], this.location[1] + 1},
                    {this.location[0] + 1, this.location[1] - 1},
                    {this.location[0] + 1, this.location[1]},
                    {this.location[0] + 1, this.location[1] + 1}
            };
        }
        // System.out.println("Neighbours: " + Arrays.deepToString(this.neighbours));
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

    public boolean getState() {
        return this.state;
    }

    public int[][] getNeighbours() {
        return neighbours;
    }

    public int[] getLocation() {
        return location;
    }
}
