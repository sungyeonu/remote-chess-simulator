package ChessGame.Piece;

import ChessGame.ColorEnum;

public class Bishop extends AbstractPiece{
    public Bishop(ColorEnum color) {
        super(color);
    }
    @Override
    public void getMoveSet() {
    }

    @Override
    public ColorEnum getColor() {
        return color;
    }
}
