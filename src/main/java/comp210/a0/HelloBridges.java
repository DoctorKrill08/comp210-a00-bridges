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

    private static final Color BLUE = new Color(0, 0, 255);
    private static final Color WHITE = new Color(255,255,255);
    private static final Color RED = new Color(255,0,0);

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
        ColorGrid grid = new ColorGrid(SIZE, SIZE, BLUE);

        for (int row = 0; row < SIZE; row++){
            for (int col = 0; col < SIZE; col++){
                if (row % 2 == 0){
                    grid.set(row,col,RED);
                }else{
                    grid.set(row,col,WHITE);
                }
            }
        }
        int CORNER_SIZE = SIZE / 2; //50% * 50% = 25%
        for (int row = 0; row < CORNER_SIZE; row++){
            for (int col = 0; col < CORNER_SIZE; col++){
                if (row % 3 == 0 && col % 3 == 0){
                    grid.set(row,col,WHITE);
                    continue;
                }
                grid.set(row,col,BLUE);
            }
        }

        return grid;
    }
}
