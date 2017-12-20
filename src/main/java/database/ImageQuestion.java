package database;

/**
 * Image question
 */
public class ImageQuestion extends Question{
    /** Image url*/
    public String image;
    /** question text*/
    public String text;
    /** answer top left coordinates*/
    public Position topLeft = new Position();
    /** answer bottom right coordinates*/
    public Position bottomRight = new Position();

    public void setTopLeft(int x, int y) {
        topLeft.x = x;
        topLeft.y = x;
    }

    public void setTopLeft(Position p) {
        topLeft = p;
    }

    public void setBottomRight(int x, int y) {
        bottomRight.x = x;
        bottomRight.y = x;
    }

    public void setBottomRight(Position p) {
        bottomRight = p;
    }

    @Override
    public boolean isCorrect(int x, int y) {
        return x >= topLeft.x
                && x <= bottomRight.x
                && y >= topLeft.y
                && y <= bottomRight.y
                ;
    }
}
