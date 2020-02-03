package ChessGame.Piece;

import ChessGame.ChessBoard;
import ChessGame.ColorEnum;
import ChessGame.Position.AbstractPosition;

import java.util.ArrayList;

public class Pawn extends AbstractPiece{
    private int offset;

    public Pawn(ColorEnum color){
        super(color);
        if (color.getID() == 1){
            offset = -1;
        } else if (color.getID() == 2){
            offset = 1;
        }
    }
    @Override
    public void getMoveSet(ChessBoard board, AbstractPosition position) {
        ArrayList<Move> moveSet = new ArrayList<>();
        AbstractPosition[][] grid = board.getBoard();
        int row = position.getX();
        int col = position.getY();
        if (board[row + offset][col].)

    }

    @Override
    public ColorEnum getColor() {
        return null;
    }
}
