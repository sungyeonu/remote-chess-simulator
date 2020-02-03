package ChessGame.Piece;

import ChessGame.ColorEnum;
import ChessGame.Piece.AbstractPiece;
import ChessGame.Piece.Pawn;

public class PieceFactory {
    public AbstractPiece makePawn(ColorEnum color){
        return new Pawn(color);
    }
    public AbstractPiece makeRook(ColorEnum color){
        return new Pawn(color);
    }
    public AbstractPiece makeKnight(ColorEnum color){
        return new Pawn(color);
    }
    public AbstractPiece makeQueen(ColorEnum color){
        return new Pawn(color);
    }
    public AbstractPiece makeKing(ColorEnum color){
        return new Pawn(color);
    }
    public AbstractPiece makeBishop(ColorEnum color){
        return new Pawn(color);
    }
}
