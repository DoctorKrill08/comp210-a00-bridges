package comp210.a0;

import bridges.base.Color;
import bridges.base.ColorGrid;
import bridges.connect.Bridges;

/**
 * COMP210 Assignment 0, the code-along.
 *
 * This program already works. Give it credentials so it can reach the BRIDGES
 * server, then change the picture into something of your own.
 *
 * Run it from IntelliJ: click the green arrow in the gutter next to main().
 */
public class HelloBridges {

    /** Grid size in pixels. 16x16 is small enough to draw by hand. */
    private static final int SIZE = 16;

    private static final Color CAROLINA = new Color(123, 175, 212);
    private static final Color PLUM = new Color(58, 42, 68);
    private static final Color KIWI = new Color(142, 188, 92);

    public static void main(String[] args) throws Exception {
        // Step 1: read the secrets out of .env instead of hardcoding them.
        BridgesConfig config = BridgesConfig.load();
        System.out.println("Loaded credentials: " + config.describe());

        // Step 2: hand those credentials to the BRIDGES client. Every BRIDGES
        // program you write this semester opens with some version of this line.
        Bridges bridges = new Bridges(
            config.getAssignmentNumber(),
            config.getUsername(),
            config.getApiKey());

        bridges.setTitle("COMP210 A0: " + config.getUsername() + " says hello");
        bridges.setDescription("Setup check for Assignment 0.");

        // Step 3: build something to look at.
        ColorGrid grid = makeGrid();
        bridges.setDataStructure(grid);

        // Step 4: POST it. BRIDGES turns your grid into JSON, sends it over
        // HTTP, and prints back a URL. Open the URL.
        bridges.visualize();
    }

    /**
     * Draws the starter picture: a Carolina blue field with a plum X
     * and a kiwi-green border.
     *
     * TODO (your part): throw this out and draw something of your own. Your
     * initials, a sprite, a flag, a very blocky portrait of Doobi. It has to
     * fit in SIZE x SIZE and use at least three colors; past that, no rules.
     */
    private static ColorGrid makeGrid() {
        ColorGrid grid = new ColorGrid(SIZE, SIZE, CAROLINA);

        for (int i = 0; i < SIZE; i++) {
            grid.set(i, i, PLUM);
            grid.set(i, SIZE - 1 - i, PLUM);
        }

        for (int i = 0; i < SIZE; i++) {
            grid.set(0, i, KIWI);
            grid.set(SIZE - 1, i, KIWI);
            grid.set(i, 0, KIWI);
            grid.set(i, SIZE - 1, KIWI);
        }

        return grid;
    }
}
