package ChessGame.Piece;

public class Move {
    private Coord from, to;
    public Move(Coord from, Coord to){
        this.from = from;
        this.to = to;
    }
    public Coord getFrom(){
        return from;
    }
    public Coord getTo(){
        return to;
    }
}
