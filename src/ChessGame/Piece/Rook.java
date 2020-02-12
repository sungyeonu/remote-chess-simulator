package ChessGame.Piece;

import ChessGame.ChessBoard;
import ChessGame.ColorEnum;
import ChessGame.Position.AbstractPosition;

import java.util.ArrayList;

public class Rook extends Piece {
    public Rook(ColorEnum color) {
        super(color);
    }

    @Override
    public ArrayList<Move> getMoveSet(ChessBoard board, AbstractPosition position) {
        ArrayList<Move> moveSet = new ArrayList<>();
        AbstractPosition[][] grid = board.getBoard();
        int row = position.getX();
        int col = position.getY();
        Coord from = new Coord(row, col);

        //checks left
        for (int colCount = col-1; colCount >= 0; colCount--) {
            if (grid[row][colCount].isEmpty())
                moveSet.add(new Move(from, new Coord(row, colCount)));
            else {
                if (grid[row][colCount].getPiece().getColor().getID() != color.getID())
                    moveSet.add(new Move(from, new Coord(row, colCount)));
                break; //encountered piece
            }
        }
        //checks right
        for (int colCount = col+1; colCount <= 7; colCount++) {
            if (grid[row][colCount].isEmpty())
                moveSet.add(new Move(from, new Coord(row, colCount)));
            else {
                if (grid[row][colCount].getPiece().getColor().getID() != color.getID())
                    moveSet.add(new Move(from, new Coord(row, colCount)));
                break; //encountered piece
            }
        }
        //checks up
        for (int rowCount = row-1; rowCount >= 0; rowCount--) {
            if (grid[rowCount][col].isEmpty())
                moveSet.add(new Move(from, new Coord(rowCount, col)));
            else {
                if (grid[rowCount][col].getPiece().getColor().getID() != color.getID())
                    moveSet.add(new Move(from, new Coord(rowCount, col)));
                break; //encountered piece
            }
        }
        //checks down
        for (int rowCount = row+1; rowCount <= 7; rowCount++) {
            if (grid[rowCount][col].isEmpty())
                moveSet.add(new Move(from, new Coord(rowCount, col)));
            else {
                if (grid[rowCount][col].getPiece().getColor().getID() != color.getID())
                    moveSet.add(new Move(from, new Coord(rowCount, col)));
                break; //encountered piece
            }
        }
        /*
        * To-Do Castling
        * */
        return moveSet;
    }

    @Override
    public ColorEnum getColor() {
        return color;
    }
}
