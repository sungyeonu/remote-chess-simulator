package ChessGame.Position;

public class PositionImpl extends AbstractPosition{
    public PositionImpl(int x, int y){
        super(x, y);
    }
    public void removePiece(){
        this.piece = null;
    }
}
