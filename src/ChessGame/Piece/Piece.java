package ChessGame.Piece;

import ChessGame.ChessBoard;
import ChessGame.Position.AbstractPosition;
import ChessGame.ColorEnum;
import java.util.ArrayList;

public abstract class Piece {
    protected ColorEnum color;
    protected String id;
    protected String name;
    protected AbstractPosition position;
    protected boolean hasMoved = false;

    public Piece(ColorEnum color){
        this.color = color;
    }
    public void initPiece(AbstractPosition position){
        this.position = position;
    }
    public abstract ArrayList<Move> getMoveSet(ChessBoard board, AbstractPosition position);
    public abstract ColorEnum getColor();
    public void setHasMoved (boolean value){
        hasMoved = value;
    }
}
