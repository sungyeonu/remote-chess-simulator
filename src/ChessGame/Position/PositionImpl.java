package ChessGame.Position;

import ChessGame.Piece.AbstractPiece;

public class PositionImpl extends AbstractPosition{
    public PositionImpl(int x, int y){
        super(x, y);
    }
    public void removePiece(){
        this.piece = null;
    }
    public void setPiece(AbstractPiece piece){
        this.piece = piece;
    }
}
