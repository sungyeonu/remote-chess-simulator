package ChessGame.Piece;

import ChessGame.ChessBoard;
import ChessGame.ColorEnum;
import ChessGame.Position.AbstractPosition;

import java.util.ArrayList;

public class Bishop extends Piece {
    public Bishop(ColorEnum color) {
        super(color);
        this.id = PieceIDEnum.BISHOP;
    }

    @Override
    public ArrayList<Move> getMoveSet(ChessBoard board, AbstractPosition position) {
        AbstractPosition[][] grid = board.getBoard();
        ArrayList<Move> moveSet = new ArrayList<Move>();

        int row = position.getX();
        int col = position.getY();
        Coord from = new Coord(row, col);
        int colIndex, rowIndex;

        //North-West Diagonal
        for (rowIndex = row - 1, colIndex = col - 1; colIndex >= 0 && rowIndex >= 0; colIndex--, rowIndex--){
            if(grid[colIndex][rowIndex].isEmpty()){
                moveSet.add(new Move(from, new Coord(colIndex, rowIndex)));
            } else if (grid[colIndex][rowIndex].getPiece().getColor().getID() != color.getID()){
                moveSet.add(new Move(from, new Coord(colIndex, rowIndex)));
                break;
            }
        }

        //North-East Diagonal
        for (rowIndex = row - 1, colIndex = col + 1; colIndex <= 7 && rowIndex >= 0; colIndex ++, rowIndex--){
            if(grid[colIndex][rowIndex].isEmpty()){
                moveSet.add(new Move(from, new Coord(colIndex, rowIndex)));
            } else if (grid[colIndex][rowIndex].getPiece().getColor().getID() != color.getID()){
                moveSet.add(new Move(from, new Coord(colIndex, rowIndex)));
                break;
            }
        }

        //South-west Diagonal
        for (rowIndex = row + 1, colIndex = col - 1; colIndex >= 0 && rowIndex <= 7; colIndex --, rowIndex++){
            if(grid[colIndex][rowIndex].isEmpty()){
                moveSet.add(new Move(from, new Coord(colIndex, rowIndex)));
            } else if (grid[colIndex][rowIndex].getPiece().getColor().getID() != color.getID()){
                moveSet.add(new Move(from, new Coord(colIndex, rowIndex)));
                break;
            }
        }
        //South-east Diagonal
        for (rowIndex = row + 1, colIndex = col + 1; colIndex <= 7 && rowIndex <= 7; colIndex ++, rowIndex++){
            if(grid[colIndex][rowIndex].isEmpty()){
                moveSet.add(new Move(from, new Coord(colIndex, rowIndex)));
            } else if (grid[colIndex][rowIndex].getPiece().getColor().getID() != color.getID()){
                moveSet.add(new Move(from, new Coord(colIndex, rowIndex)));
                break;
            }
        }
        return moveSet;
    }
}
