package ChessGame;

import ChessGame.Piece.Coord;
import ChessGame.Piece.Move;
import ChessGame.Piece.PieceFactory;
import ChessGame.Piece.PieceIDEnum;
import ChessGame.Position.Position;

public class ChessBoard {
    private PieceFactory pieceFactory = new PieceFactory();
    private final static int BOARD_ROWS = 8;
    private final static int BOARD_COLS = 8;
    private Position[][] grid;

    private Player blue;
    private Player black;
    public ChessBoard(){
        grid = new Position[BOARD_ROWS][BOARD_COLS];
        blue = new Player(ColorEnum.BLUE);
        black = new Player(ColorEnum.BLACK);
        initBoard();
        initBlack(ColorEnum.BLACK);
        initBlue(ColorEnum.BLUE);
    }
    public void initBoard(){
        for (int i = 0 ; i < BOARD_COLS; i++){
            for (int j = 0 ; j < BOARD_ROWS; j++){
                grid[i][j] = new Position(new Coord(i,j));
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
        grid[0][3].setPiece(pieceFactory.makeQueen(color));
        grid[0][4].setPiece(pieceFactory.makeKing(color));
    }
    public void initBlue(ColorEnum color){ //bottom of board
        int row = 6;
        for (int i = 0; i < 8 ; i++){
            grid[row][i].setPiece(pieceFactory.makePawn(color));
        }
        grid[7][0].setPiece(pieceFactory.makeRook(color));
        grid[7][7].setPiece(pieceFactory.makeRook(color));
        grid[7][1].setPiece(pieceFactory.makeKnight(color));
        grid[7][6].setPiece(pieceFactory.makeKnight(color));
        grid[7][2].setPiece(pieceFactory.makeBishop(color));
        grid[7][5].setPiece(pieceFactory.makeBishop(color));
        grid[7][3].setPiece(pieceFactory.makeQueen(color));
        grid[7][4].setPiece(pieceFactory.makeKing(color));
    }
    public Position[][] getBoard(){
        return grid;
    }

    public Position getPosition(int x, int y){
        return grid[x][y];
    }
    public boolean inBound(int x, int y){
        if (x >= 0 && x <= 7 && y>=0 && y<=7){
            return true;
        } else {
            return false;
        }
    }
    public void print(){
        for (int rows = 0; rows < BOARD_ROWS; rows ++){
            for (int cols = 0; cols < BOARD_COLS; cols ++){
                if (!(grid[rows][cols].isEmpty())) {
                    PieceIDEnum id = grid[rows][cols].getPiece().getId();
                    System.out.print(id.getID() + " ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }

    }
    public void makeMove(Move move){

    }
    public static void main(String[] args){
        ChessBoard board = new ChessBoard();
        board.print();
    }
}
