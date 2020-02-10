package ChessGame.Position;

import ChessGame.Piece.Piece;

public class AbstractPosition {
    protected int x;
    protected int y;
    Piece piece;
    boolean empty;
    public AbstractPosition(int x, int y){
        this.x = x;
        this.y = y;
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
        return x;
    }
    public int getY(){
        return y;
    }
    public Piece getPiece(){
        return piece;
    }
}
