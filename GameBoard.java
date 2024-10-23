public class GameBoard {
    private static int ROWS = 9;
    private static int COLS = 9;

    public static void main(String[] args) {
        Grid g = new Grid(ROWS, COLS);
        PennDraw.setXscale(0, COLS);
        PennDraw.setYscale(ROWS, 0);
        boolean playingMode = true;
        boolean wasNotPressedLast = true;
        
        g.initializeRound();

        PennDraw.enableAnimation(30);
        while (true) {
            g.draw();

            // if the mouse is pressed and was not pressed last, update the grid.
            if (PennDraw.mousePressed() && wasNotPressedLast) {
                wasNotPressedLast = false;
                g.update(PennDraw.mouseX(), PennDraw.mouseY());
            }

            // mark that the mouse is not currently pressed
            if (!PennDraw.mousePressed()) {
                wasNotPressedLast = true;
            }

            // draw victory or lost
            if (g.isWon()) {
                g.drawVictory();
            } else if (g.getIsLost()) {
                g.drawLost();
            }

            PennDraw.advance();

            // reset the game
            if (PennDraw.hasNextKeyTyped()) {
                char keyPressed = PennDraw.nextKeyTyped();
                if (keyPressed == 'r') {
                    PennDraw.setFontSize(16);
                    g.initializeRound();
                }
            }

            PennDraw.advance();
        }
    }
}