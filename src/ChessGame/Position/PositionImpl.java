package ChessGame.Position;

import ChessGame.Piece.Coord;

public class PositionImpl extends AbstractPosition{
    public PositionImpl(Coord coord){
        super(coord);
    }
    public void removePiece(){
        this.piece = null;
    }
}
