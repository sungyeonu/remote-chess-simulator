package ChessGame.Position;

import ChessGame.Piece.AbstractPiece;

public class AbstractPosition {
    protected int x;
    protected int y;
    AbstractPiece piece;
    boolean empty;
    public AbstractPosition(int x, int y){
        this.x = x;
        this.y = y;
    }
    public void setPiece(AbstractPiece piece){
        this.piece = piece;
    }
    public boolean isEmpty(){
        if (empty == true){
            return true;
        } else
            return false;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public AbstractPiece getPiece(){
        return piece;
    }
}
