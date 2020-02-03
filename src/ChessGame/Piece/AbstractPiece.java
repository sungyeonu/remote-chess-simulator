package ChessGame.Piece;

import ChessGame.ChessBoard;
import ChessGame.Position.AbstractPosition;
import ChessGame.ColorEnum;

public abstract class AbstractPiece {
    protected ColorEnum color;
    protected String id;
    protected String name;
    protected AbstractPosition position;
    protected boolean firstMove = true;

    public AbstractPiece(ColorEnum color){
        this.color = color;
    }
    public void initPiece(AbstractPosition position){
        this.position = position;
    }
    public abstract void getMoveSet(ChessBoard board, AbstractPosition position);
    public abstract ColorEnum getColor();
    public void setFirstMove (boolean value){
        firstMove = value;
    }
}
