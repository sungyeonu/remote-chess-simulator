package UI;

import ChessGame.ChessBoard;
import ChessGame.ColorEnum;
import ChessGame.Piece.Coord;
import ChessGame.Piece.Move;
import ChessGame.Piece.Piece;
import ChessGame.Piece.PieceIDEnum;
import ChessGame.Position.Position;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import static javax.swing.SwingUtilities.isLeftMouseButton;
import static javax.swing.SwingUtilities.isRightMouseButton;

public class Board extends JPanel {
    private final int WIDTH = 640;
    private final int HEIGHT = 640;
    private final int START_X = 150;
    private final int START_Y = 150;
    private final int ROWS = 8;
    private final int COLS = 8;
    private final int TILE_WIDTH = WIDTH/8;
    private final int TILE_HEIGHT = HEIGHT/ 8;
    private final Dimension OUTER_FRAME_DIMENSION = new Dimension(WIDTH, HEIGHT);
    private final Dimension BOARD_PANEL_DIMENSON = new Dimension(WIDTH, HEIGHT);
    private final Dimension TILE_PANEL_DIMENSION = new Dimension(TILE_WIDTH, TILE_HEIGHT);

    private final Color WHITE = new Color(255, 255,255);
    private final Color BLUE = new Color(203, 241, 245);
    private final Color GREEN = new Color(82,212,88);
    private final Color RED = new Color(221,65,65);
    private final Color DARK_BLUE = new Color(48,93,255);

    private Position sourcePosition = null;
    private Position destinationPosition = null;
    private Piece selectedPiece = null;

    private ChessBoard board;
    private final JFrame gameFrame;
    private final BoardPanel boardPanel;

    public Board(){
        this.gameFrame = new JFrame("Chess");
        this.gameFrame.setSize(OUTER_FRAME_DIMENSION);
        this.board = new ChessBoard();
        this.boardPanel = new BoardPanel();
        this.gameFrame.add(this.boardPanel, BorderLayout.CENTER);

        repaint();

        this.gameFrame.setVisible(true);
    }

    public void updateBoard(ChessBoard board){
            this.board = board;
    }

    private class BoardPanel extends JPanel {
        SquarePanel[][] squares;

        BoardPanel() {
            super(new GridLayout(8, 8));
            this.squares = new SquarePanel[ROWS][COLS];
            for (int rows = 0; rows < ROWS; rows++) {
                for (int cols = 0; cols < COLS; cols ++) {
                    final SquarePanel squarePanel = new SquarePanel(rows, cols);
                    squares[rows][cols] = squarePanel;
                    add(squarePanel);
                }
            }
            setPreferredSize(BOARD_PANEL_DIMENSON);
            validate();
        }
        public void drawBoard(final ChessBoard board){
            removeAll();
            for (int rows = 0; rows < ROWS; rows++) {
                for (int cols = 0; cols < COLS; cols ++) {
                    final SquarePanel squarePanel = squares[rows][cols];
                    squarePanel.drawSquarePanel(board);
                    add(squarePanel);
                }
            }
            validate();
            repaint();
        }
    }
    private class SquarePanel extends JPanel{
            private final int row;
            private final int col;

            SquarePanel(final int row, final int col){
                super(new GridBagLayout());
                this.row = row;
                this.col = col;
                setPreferredSize(TILE_PANEL_DIMENSION);
                assignTileColor();
                assignTilePieceIcon(board);

                addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (isRightMouseButton(e)){
                            resetClickedPositionAndPieces();
                        } else if (isLeftMouseButton(e)){
                            System.out.println("Left");
                            if (sourcePosition == null){
                                sourcePosition = board.getPosition(row, col);
                                selectedPiece = sourcePosition.getPiece();
                                System.out.println(selectedPiece.getId());
                                if (selectedPiece == null){
                                    sourcePosition = null;
                                }
                            } else {
                                destinationPosition = board.getPosition(row, col);
                                Coord from = sourcePosition.getCoord();
                                Coord to = destinationPosition.getCoord();
                                ArrayList<Move> moveSet = selectedPiece.getMoveSet(board, sourcePosition);

                                for (Move x : moveSet) {
                                    Coord checkFrom = x.getFrom();
                                    Coord checkTo = x.getTo();
                                    if (Coord.checkEquality(from, checkFrom) && Coord.checkEquality(to, checkTo)) {
                                        System.out.println("fromX: " + from.getX() + "fromY: " + from.getY() +
                                                "\ntoX: " + to.getX() + "toY: " + to.getY());
                                        board.makeMove(x);
                                        System.out.println("True");
                                        break;
                                    }
                                    board = board.getChessBoard();
                                }
                                resetClickedPositionAndPieces();
                                SwingUtilities.invokeLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        boardPanel.drawBoard(board);
                                    }
                                });
                            }
                        }
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                    }
                });


                validate();
            }

            private void resetClickedPositionAndPieces(){
                sourcePosition = null;
                destinationPosition = null;
                selectedPiece = null;
            }
            private void assignTileColor(){
                setBackground(row%2 == col%2 ? WHITE : BLUE);
            }

            private void assignTilePieceIcon(ChessBoard board){
                this.removeAll();
                try{
                    Position[][] grid = board.getBoard();
                    if(!(grid[row][col].isEmpty())){
                        Piece piece = board.getBoard()[row][col].getPiece();
                        ColorEnum color = piece.getColor();
                        String colorFolder;
                        if (color.getID() == 1)
                            colorFolder = "blue";
                        else
                            colorFolder = "black";

                        PieceIDEnum name = board.getBoard()[row][col].getPiece().getId();

                        String path = "/icons/" + colorFolder + "/" + name.getID() + ".gif";
                        final BufferedImage image = ImageIO.read(getClass().getResource(path));
                        add(new JLabel(new ImageIcon(image)));
                    }
                } catch (IOException e){
                    e.printStackTrace();
                }
            }

            public void drawSquarePanel(ChessBoard board){
                assignTileColor();
                assignTilePieceIcon(board);
                validate();
                repaint();
            }
    }
//    private void createBoard() {
//        JFrame frame = new JFrame();
//        JPanel panel = new JPanel(new GridLayout(8, 8, 0, 0));
//        Insets buttonMargin = new Insets(0, 0, 0, 0);
//    }
}
