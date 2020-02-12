package ChessGame.Position;

import ChessGame.Piece.Coord;
import ChessGame.Piece.Piece;

public class AbstractPosition {
    Coord coord;
    Piece piece;
    boolean empty;
    public AbstractPosition(Coord coord){
        this.coord = coord;
    }
    public void setPiece(Piece piece){
        this.piece = piece;
    }
    public boolean isEmpty(){
        if (empty == true){
            return true;
        } else
            return false;
    }
    public int getX(){
        return coord.getX();
    }
    public int getY(){
        return coord.getY();
    }
    public Piece getPiece(){
        return piece;
    }
}
