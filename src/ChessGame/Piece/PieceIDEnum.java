package ChessGame.Piece;

public enum PieceIDEnum {
        PAWN("P"), ROOK("R"), KNIGHT("K"), BISHOP("B"), QUEEN("Q"), KING("A");
    private String id;
    private PieceIDEnum(String id){
        this.id = id;
    }
    public String getID(){
        return id;
    }
}
