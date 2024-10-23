public class Grid {
    private String level;
    private Tile[][] tiles;
    private int[][] mines;
    private int numRows;
    private int numCols;
    private int numOfMines;
    private boolean isLost;

    private double gridHeight, gridWidth, gridLeft, gridRight, gridBottom, gridTop;
    private double gridCellHeight, gridCellWidth;

    public Grid(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
        numOfMines = 10;
        calculateGridParameters();
        tiles = new Tile[numRows][numCols];
        mines = new int[numRows][numCols];
        initializeTiles();
        isLost = false;
    }

    /*
    * Description: calculate the parameters for the grid.
    * Input: void.
    * Output: void.
    */ 
    private void calculateGridParameters() {

        // I want a border of 10% of screen width on all sides
        // of the clickable grid
        gridHeight = 0.8 * numRows;
        gridWidth = 0.8 * numCols;
        gridLeft = 0.1 * numCols; // grid will be centered horizontally
        gridRight = 0.9 * numCols;
        gridTop = 0.2 * numRows; // grid will start 20% down the screen
        gridBottom = numRows;    // grid will end at the bottom of the screen
        gridCellWidth = gridWidth / numCols;
        gridCellHeight = gridHeight / numRows;
    }

    /*
    * Description: initialize the game, set the mines and then initializeTiles.
    * Input: void.
    * Output: void.
    */ 
    public void initializeRound() {
        isLost = false;

        // clear all the mines before randomly put mines on it.
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                mines[i][j] = 0;
            }
        }

        // randomly set the mines array, use 1 to represent there is a mine.
        for (int i = 0; i < numOfMines; i++) {
            int row = (int) (Math.random() * numRows);
            int col = (int) (Math.random() * numCols);

            // if this random position has been set to a mine, 
            // this round of random generation is meaningless.
            if (mines[row][col] == 1) {
                i--;
            } else {
                mines[row][col] = 1;
            }
        }
        initializeTiles();
    }

    /*
    * Description: initialize every single tile by calculating the x, y location and 
    *              number of adjacent mines.
    * Input: void.
    * Output: void.
    */ 
    private void initializeTiles() {
        double xOffset = (gridCellWidth / 2) + gridLeft;
        double yOffset = (gridCellHeight / 2) + gridTop;
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                double x = xOffset + (col * gridCellWidth);
                double y = yOffset + (row * gridCellHeight);

                // calculate the number of adjacent mines of each tile
                int numOfMines = 0;
                // left up corner
                if (row == 0 && col == 0) {
                    for (int i = row; i < row + 2; i++) {
                        for (int j = col; j < col + 2; j++) {
                            if (!(i == row && j == col)) {
                                numOfMines += mines[i][j];
                            }
                        }
                    }
                } else if (row == 0 && col == numCols - 1) { // right up corner
                    for (int i = row; i < row + 2; i++) {
                        for (int j = col - 1; j < col + 1; j++) {
                            if (!(i == row && j == col)) {
                                numOfMines += mines[i][j];
                            }
                        }
                    }
                } else if (row == numRows - 1 && col == 0) { // left bottom corner
                    for (int i = row - 1; i < row + 1; i++) {
                        for (int j = col; j < col + 2; j++) {
                            if (!(i == row && j == col)) {
                                numOfMines += mines[i][j];
                            }
                        }
                    }
                } else if (row == numRows - 1 && col == numCols - 1) {
                    // right bottom corner
                    for (int i = row - 1; i < row + 1; i++) {
                        for (int j = col - 1; j < col + 1; j++) {
                            if (!(i == row && j == col)) {
                                numOfMines += mines[i][j];
                            }
                        }
                    }
                } else if (row == 0 && col > 0 && col < numCols - 1) {
                    // the highest row
                    for (int i = row; i < row + 2; i++) {
                        for (int j = col - 1; j < col + 2; j++) {
                            if (!(i == row && j == col)) {
                                numOfMines += mines[i][j];
                            }
                        }
                    }
                } else if (row == numRows - 1 && col > 0 && col < numCols - 1) {
                    // the lowest row
                    for (int i = row - 1; i < row + 1; i++) {
                        for (int j = col - 1; j < col + 2; j++) {
                            if (!(i == row && j == col)) {
                                numOfMines += mines[i][j];
                            }
                        }
                    }
                } else if (col == 0 && row > 0 && row < numRows - 1) {
                    // the leftest col
                    for (int i = row - 1; i < row + 2; i++) {
                        for (int j = col; j < col + 2; j++) {
                            if (!(i == row && j == col)) {
                                numOfMines += mines[i][j];
                            }
                        }
                    }
                } else if (col == numCols - 1 && row > 0 && row < numRows - 1) {
                    // the rightest col
                    for (int i = row - 1; i < row + 2; i++) {
                        for (int j = col - 1; j < col + 1; j++) {
                            if (!(i == row && j == col)) {
                                numOfMines += mines[i][j];
                            }
                        }
                    }
                } else {
                    // the inner locations
                    for (int i = row - 1; i < row + 2; i++) {
                        for (int j = col - 1; j < col + 2; j++) {
                            if (!(i == row && j == col)) {
                                numOfMines += mines[i][j];
                            }
                        }
                    }
                }

                // initiate the tile
                tiles[row][col] = new Tile(numOfMines, mines[row][col],
                gridCellWidth, gridCellHeight, x, y);
            }
        }
    }

    /*
    * Description: drawing function for the grid and it calls the drawing method
    *              for each tile.
    * Input: void.
    * Output: void.
    */ 
    public void draw() {
        // clear the screen
        PennDraw.setPenColor(PennDraw.WHITE);
        PennDraw.filledRectangle(4.5, 4.5, 4.5, 4.5);

        // draw the grid of tiles
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numRows; col++) {
                tiles[row][col].draw();
            }
        }
    }

    /*
    * Description: this is the function when a tile is clicked by the player.
    *              (1) mark the tile as clicked;
    *              (2) if it is a mine, end the game and call isLost method.
    *              (3) then if the num is 0, recursively search for the nearest 
    *                  tiles containing a positive number.
    * Input: the mouse clicking coordinates.
    * Output: void.
    */    
    public void update(double x, double y) {
        // check if the click is not on the grid, if so, return directly
        if (x < gridLeft || x > gridRight || y < gridTop || y > gridBottom) {
            return;
        }

        int col = (int) (numCols * ((x - gridLeft) / gridWidth));
        int row = (int) (numRows * ((y - gridTop) / gridHeight));
        tiles[row][col].setClicked(true);

        // if the clicked tile is a mine, then end the game directly
        if (mines[row][col] == 1) {
            isLost = true;
            return;
        }

        // when the tile contains num 0, search for nearest num.
        if (tiles[row][col].getNum() == 0) {
            // recursively search for the nearest tile with a number.
            checkNearestNum(row, col);
        }
    }

    /*
    * Description: this is the recursive func checking the nearest tile contains 
    *              a number. (Mine is centered in numbers so it couldn't 
    *              find a mine directly, it has to touch the number first.)
    * Input: the current num-0 tile's row and col number
    * Output: void.
    */    
    private void checkNearestNum(int i, int j) {
        // base case
        // reach the edge of the grid
        if (i < 0 || i > numRows - 1 || j < 0 || j > numCols - 1) {
            return;
        }

        // if the tile has been clicked, return
        if (tiles[i][j].getChecked()) {
            return;
        }

        // reach a tile containing positive #, mark it as clicked and return
        if (tiles[i][j].getNum() > 0) { 
            tiles[i][j].setClicked(true);
            tiles[i][j].setChecked(true);
            return;
        } 

        // recursive calls
        tiles[i][j].setClicked(true);
        tiles[i][j].setChecked(true);
        checkNearestNum(i - 1, j - 1);
        checkNearestNum(i - 1, j);
        checkNearestNum(i - 1, j + 1);
        checkNearestNum(i, j - 1);
        checkNearestNum(i, j + 1);
        checkNearestNum(i + 1, j - 1);
        checkNearestNum(i + 1, j);
        checkNearestNum(i + 1, j + 1);
        return;
    }

    /*
    * Description: checking if the game is won.
    * Input: void.
    * Output: boolean value, true if the game is won.
    */ 
    public boolean isWon() {
        int notClickedMines = 0;
        int clickedTiles = 0;
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                if (tiles[row][col].getClicked()) {
                    clickedTiles++;
                }
                if (!tiles[row][col].getClicked() && (mines[row][col] == 1)) {
                    notClickedMines++;
                } 
            }
        }
        int numOfNotMines = numCols * numRows - numOfMines;

        // if the 10 mines are the only 10 tiles that haven't been clicked, win
        if (notClickedMines == numOfMines && clickedTiles == numOfNotMines) {
            return true;
        }
        return false;
    }

    /*
    * Description: getter of isLost.
    * Input: void.
    * Output: isLost value.
    */ 
    public boolean getIsLost() {
        return isLost;
    }

    /*
    * Description: draw the winning message.
    * Input: void.
    * Output: void.
    */ 
    public void drawVictory() {
        PennDraw.setPenColor(24, 153, 61);
        PennDraw.filledRectangle(numCols / 2.0, numRows / 2.0, numCols / 2.0, 
        numRows / 2.0);
        PennDraw.setPenColor(0, 0, 0);
        PennDraw.setFontSize(64);
        PennDraw.text(numCols / 2.0, numRows / 2.0, ":)  YOU WIN!!!");
        PennDraw.setFontSize(18);
        PennDraw.text(numCols / 2.0, numRows / 3.0, "Press 'r' to reset the game.");
    }

    /*
    * Description: draw the losing message.
    * Input: void.
    * Output: void.
    */ 
    public void drawLost() {
        PennDraw.setPenColor(217, 98, 74);
        PennDraw.filledRectangle(numCols / 2.0, numRows / 2.0, numCols / 2.0, 
        numRows / 2.0);
        PennDraw.setPenColor(0, 0, 0);
        PennDraw.setFontSize(64);
        PennDraw.text(numCols / 2.0, numRows / 2.0, ":(  YOU LOST...");
        PennDraw.setFontSize(18);
        PennDraw.text(numCols / 2.0, numRows / 3.0, "Press 'r' to reset the game.");
    }
}