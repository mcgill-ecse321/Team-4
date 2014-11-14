package GamePlay;

/**
 * Created by danielmacario on 14-11-12.
 */
public class Coordinate {

    private int row;
    private int col;

    public Coordinate(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int distanceTo(Coordinate coordinate) {
        return (int) Math.sqrt( (row - coordinate.getRow()) *  (row - coordinate.getRow()) + (col - coordinate.getCol()) *  (col - coordinate.getCol()));
    }

}