package ChessGame.Piece;

import ChessGame.ChessBoard;
import ChessGame.ColorEnum;
import ChessGame.Position.AbstractPosition;

import java.util.ArrayList;

public class Pawn extends AbstractPiece{
    private int offset;
    private int displacementFromStartPosition;
    private boolean enPassant = false;

    public Pawn(ColorEnum color){
        super(color);
        if (color.getID() == 1){
            offset = -1;
        } else if (color.getID() == 2){
            offset = 1;
        }
    }
    @Override
    public ArrayList<Move> getMoveSet(ChessBoard board, AbstractPosition position) {
        ArrayList<Move> moveSet = new ArrayList<>();
        AbstractPosition[][] grid = board.getBoard();
        int row = position.getX();
        int col = position.getY();
        Coord from = new Coord(row, col);
        int left = -1;
        int right = 1;
        if (hasMoved == false){
            Coord oneForward = new Coord(row + offset, col);
            Move newMove = new Move(from, oneForward);
            moveSet.add(newMove);
            Coord twoForward = new Coord(row + offset*2, col);
            Move newMove2 = new Move(from, twoForward);
            moveSet.add(newMove);
            moveSet.add(newMove2);
        }
        if (board.inBound(row + offset, col + left)){
            AbstractPosition aheadAndLeft = grid[row + offset][col+left];
            if (!(aheadAndLeft.isEmpty()) && aheadAndLeft.getPiece().getColor().getID() != color.getID()){
                Coord to = new Coord(row + offset, col + left);
                Move newMove = new Move(from, to);
                moveSet.add(newMove);
            }
        }
        if(board.inBound(row + offset, col + right)){
            AbstractPosition aheadAndRight = grid[row + offset][col + right];
            if (!(aheadAndRight.isEmpty()) && aheadAndRight.getPiece().getColor().getID() != color.getID()){
                Coord to = new Coord(row + offset, col + right);
                Move newMove = new Move(from, to);
                moveSet.add(newMove);
            }
        }
        /* To Do
        * En Passant
        * */
        return moveSet;
    }

    @Override
    public ColorEnum getColor() {
        return color;
    }
}
