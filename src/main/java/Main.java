package main.java;

/**
 * Conway's Game of Life
 * Rules:
 * <ol>
 *     <li>Any live cell with wight fewer than two live neighbours dies, as if
 *     by underpopulation.</li>
 *     <li>Any live cell with two or three live neighbours lives on to the next
 *     generation.</li>
 *     <li>Any live cell with more than three live neighbours dies, as if by
 *     overpopulation.</li>
 *     <li>Any dead cell with exactly three live neighbours becomes a live cell
 *     as if by reproduction.</li>
 * </ol>
 * <p>With this simple ruleset we can generate and simulate a plethora of
 * scenarios, where some cell structures will never die, some will have
 * animations that will work for several frames, before landing back on the
 * original "image" and some will generate moving structures until the end of
 * time.</p>
 * <p>Play with the variables you control, and see what you can create!</p>
 */

public class Main {
    void main(String[] args) {
        Game game = new Game();
    }
}
