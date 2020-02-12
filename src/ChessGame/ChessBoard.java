package ChessGame;

import ChessGame.Piece.Coord;
import ChessGame.Piece.PieceFactory;
import ChessGame.Position.AbstractPosition;
import ChessGame.Position.PositionImpl;

public class ChessBoard {
    private PieceFactory pieceFactory = new PieceFactory();
    private AbstractPosition[][] grid;
    private final static int BOARD_ROWS = 8;
    private final static int BOARD_COLS = 8;
    public void initBoard(){
        for (int i = 0 ; i < BOARD_COLS; i++){
            for (int j = 0 ; j < BOARD_ROWS; j++){
                grid[i][j] = new PositionImpl(new Coord(i,j));
            }
        }
    }
    public void initBlack(ColorEnum color){ //top of board
        int row = 1;
        for (int i = 0; i < 8 ; i++){
            grid[row][i].setPiece(pieceFactory.makePawn(color));
        }
        grid[0][0].setPiece(pieceFactory.makeRook(color));
        grid[0][7].setPiece(pieceFactory.makeRook(color));
        grid[0][1].setPiece(pieceFactory.makeKnight(color));
        grid[0][6].setPiece(pieceFactory.makeKnight(color));
        grid[0][2].setPiece(pieceFactory.makeBishop(color));
        grid[0][5].setPiece(pieceFactory.makeBishop(color));
    }
    public void initBlue(ColorEnum color){ //bottom of board
        int row = 6;
        for (int i = 0; i < 8 ; i++){
            grid[row][i].setPiece(pieceFactory.makePawn(color));
        }
    }
    public AbstractPosition[][] getBoard(){
        return grid;
    }

    public AbstractPosition getPosition(int x, int y){
        return grid[x][y];
    }
    public boolean inBound(int x, int y){
        if (x >= 0 && x <= 7 && y>=0 && y<=7){
            return true;
        } else {
            return false;
        }
    }
}
