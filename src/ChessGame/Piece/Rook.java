package ChessGame.Piece;

import ChessGame.ChessBoard;
import ChessGame.ColorEnum;
import ChessGame.Position.AbstractPosition;

import java.util.ArrayList;

public class Rook extends AbstractPiece{
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
        int dy = 1;
        int dx = 1;
        for (int c = col-1; c >= 0; c--) {
            if (grid[row][c].isEmpty())
                moveSet.add(new Move(cord, new Coord(row, c), null));
            else {
                if (board[row][c].isEnemy(player))
                    moves.add(new Move(cord, new Coord(row, c), null));
                break; //encountered own piece or enemy piece
            }
        }
        for (int rowIndex = row; rowIndex < 8; rowIndex ++){
            if(!(board.inBound(row, col + dy))){
                break;
            }
            AbstractPosition downPosition = board.getPosition(row, col + dy);
            if (downPosition.isEmpty()){
                Coord from = new Coord(row, col);
                Coord to = new Coord(row, col + dy);
                Move newMove = new Move(from, to);
                moveSet.add(newMove);
            } else if (downPosition.getPiece().getColor().getID() != color.getID()){
                Coord from = new Coord(row, col);
                Coord to = new Coord(row, col + dy);
                Move newMove = new Move(from, to);
                moveSet.add(newMove);
                break;
            } else if (downPosition.getPiece().getColor().getID() != color.getID()){
                Coord from = new Coord(row, col);
                Coord to = new Coord(row, col + dy);
                Move newMove = new Move(from, to);
                moveSet.add(newMove);
                break;
        }
    }

    @Override
    public void getMoveSet() {

    }

    @Override
    public ColorEnum getColor() {
        return null;
    }
}
