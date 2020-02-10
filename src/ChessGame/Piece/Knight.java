package ChessGame.Piece;

import ChessGame.ChessBoard;
import ChessGame.ColorEnum;
import ChessGame.Position.AbstractPosition;

import java.util.ArrayList;

public class Knight extends Piece {
    public Knight(ColorEnum color) {
        super(color);
    }

    @Override
    public ArrayList<Move> getMoveSet(ChessBoard board, AbstractPosition position) {
        ArrayList<Move> moveSet = new ArrayList<>();
        AbstractPosition[][] grid = board.getBoard();
        int row = position.getX();
        int col = position.getY();
        Coord from = new Coord(row, col);
        if (board.inBound(row - 1, col - 2)){
            if (grid[row - 1][col - 2].isEmpty()){
                moveSet.add(new Move(from, new Coord(row - 1, col + 2)));
            } else if (grid[row - 1][col - 2].getPiece().getColor().getID() != color.getID()){
                moveSet.add(new Move(from, new Coord(row - 1, col + 2)));
            }
        }

        if (ChessBoard.validPosition(row-1, col-2) && board[row-1][col-2].isEmptyOrEnemy(player))
            moves.add(new Move(cord, new Coord(row-1, col-2), null));
        if (ChessBoard.validPosition(row-1, col+2) && board[row-1][col+2].isEmptyOrEnemy(player))
            moves.add(new Move(cord, new Coord(row-1, col+2), null));

        if (ChessBoard.validPosition(row+1, col-2) && board[row+1][col-2].isEmptyOrEnemy(player))
            moves.add(new Move(cord, new Coord(row+1, col-2), null));
        if (ChessBoard.validPosition(row+1, col+2) && board[row+1][col+2].isEmptyOrEnemy(player))
            moves.add(new Move(cord, new Coord(row+1, col+2), null));

        if (ChessBoard.validPosition(row-2, col+1) && board[row-2][col+1].isEmptyOrEnemy(player))
            moves.add(new Move(cord, new Coord(row-2, col+1), null));
        if (ChessBoard.validPosition(row-2, col-1) && board[row-2][col-1].isEmptyOrEnemy(player))
            moves.add(new Move(cord, new Coord(row-2, col-1), null));

        if (ChessBoard.validPosition(row+2, col+1) && board[row+2][col+1].isEmptyOrEnemy(player))
            moves.add(new Move(cord, new Coord(row+2, col+1), null));
        if (ChessBoard.validPosition(row+2, col-1) && board[row+2][col-1].isEmptyOrEnemy(player))
            moves.add(new Move(cord, new Coord(row+2, col-1), null));
        //

        if(grid[row][])

        return moveSet;
    }

    @Override
    public ColorEnum getColor() {
        return null;
    }
}
