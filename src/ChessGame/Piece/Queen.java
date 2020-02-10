package ChessGame.Piece;

import ChessGame.ChessBoard;
import ChessGame.ColorEnum;
import ChessGame.Position.AbstractPosition;

public class Queen extends Piece {
    public Queen(ColorEnum color) {
        super(color);
    }

    @Override
    public void getMoveSet(ChessBoard board, AbstractPosition position) {

    }

    @Override
    public ColorEnum getColor() {
        return null;
    }
}
