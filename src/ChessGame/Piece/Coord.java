package ChessGame.Piece;

public class Coord {
    private int x, y;
    public Coord(int x, int y){
        this.x = x;
        this.y = y;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public static boolean checkEquality(Coord one, Coord two){
        if ((one.getX() == two.getX()) && one.getY() == two.getY()){
            return true;
        } else{
            return false;
        }
    }
}
