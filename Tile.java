public class Tile {
    private int num;
    private int isMine;
    private boolean clicked;
    private boolean checked;
    private double width;
    private double height;
    private double x;
    private double y;
    
    public Tile(int num, int isMine, double width, double height, double x, 
    double y) {
        this.num = num;
        this.isMine = isMine;
        clicked = false;
        checked = false;
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
    }

    /*
    * Description: draw the tile.
    * Input: void.
    * Output: void.
    */ 
    public void draw() {
        // draw the gray square
        PennDraw.setPenColor(97, 105, 117);
        PennDraw.filledRectangle(x, y, width / 2, height / 2);

        // draw the edge of the tile (darker gray)
        PennDraw.setPenColor(82, 89, 99);
        PennDraw.setPenRadius(0.005 * width);
        PennDraw.rectangle(x, y, width / 2, height / 2);

        if (clicked) {
            // draw the green square
            PennDraw.setPenColor(167, 201, 115);
            PennDraw.filledRectangle(x, y, width / 2, height / 2);

            // draw the edge of the tile (darker green)
            PennDraw.setPenColor(135, 168, 84);
            PennDraw.setPenRadius(0.005 * width);
            PennDraw.rectangle(x, y, width / 2, height / 2);

            if (isMine == 1) {
                // draw the mine
                PennDraw.setPenColor(PennDraw.BLACK);
                PennDraw.filledCircle(x, y, 0.3 * width);
            } else if (num > 0) {
                // draw the number in red color
                PennDraw.setPenColor(161, 27, 27);
                PennDraw.text(x, y, "" + num);
            }
        }
    }

    /*
    * Description: getter of adjacent mines number.
    * Input: void.
    * Output: int value of num.
    */ 
    public int getNum() {
        return num;
    }

    /*
    * Description: setter of clicked attribute.
    * Input: clicked.
    * Output: void.
    */ 
    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    /*
    * Description: getter of clicked attribute.
    * Input: void.
    * Output: clicked.
    */ 
    public boolean getClicked() {
        return clicked;
    }

    /*
    * Description: setter of checked attribute.
    * Input: checked.
    * Output: void.
    */ 
    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    /*
    * Description: getter of checked attribute.
    * Input: void.
    * Output: checked.
    */ 
    public boolean getChecked() {
        return checked;
    }
}