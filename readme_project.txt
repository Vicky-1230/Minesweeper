/**********************************************************************
 *  readme template
 *  Project
 **********************************************************************/

Name: YIFAN ZHU
PennKey: vickyz
Recitation: 204



/**********************************************************************
 * Instructions for how to run your program
 * (which class to run, any command line arguments)
 **********************************************************************/
Execution: java GameBoard
After you run this, you need to wait 30 to 40 seconds until the drawing 
shows in 'View Running Program' (for the reason I don't know).

/**********************************************************************
 *  A brief description of each file and its purpose
 **********************************************************************/
 1. Tile.java
    This file mainly contains the drawing of the tile according to its 
    different status:
        Unclicked: gray
        Clicked: green
            0 booms adjacent: no number
            more than 0 booms adjacent: green tile w/ red num indicating #mines.
    Also there are some getters and setters.

 2. Grid.java
    This file contains several core methods:

        (1) void calculateGridParameters(): calculating the grid parameters.

        (2) void initializeRound(): initialize the game, set the mines and 
        then initializeTiles.

        (3) void initializeTiles(): initialize every single tile by calculating 
        the x, y location and number of adjacent mines.

        (4) void draw(): clear the screen and call draw() method on each tiles.

        (5) void update(double x, double y): update the status of tiles after 
        clicking, set the game lost if there is a mine, and if there is no 
        adjacent mines, call the recursion method to check nearest tile w/ number.

        (6) void checkNearestNum(int i, int j): recursively check the nearest 
        tiles w/ positive number and set clicked to true for all the tiles 
        along the way.

        (7) boolean isWon(): check if the game is won.

        (8) boolean getIsLost(): check if the game is lost.

        (9) void drawVictory(): draw the winning message.

        (10) void drawLost(): draw the losing message.

 3. GameBoard.java
    This file deals with the process of the game, clicking the mouse, update the 
    tiles, check winning/losing and draw victory/lose, and reset the game.
