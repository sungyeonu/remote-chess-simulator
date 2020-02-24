package UI;

import ChessGame.ChessBoard;
import ChessGame.ColorEnum;
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

    private Position sourcePosition;
    private Position destinationPosition;
    private Piece piece;

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
        final SquarePanel[][] squares;

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
                            if (sourcePosition == null){
                                sourcePosition = board.getPosition(row, col);
                                piece = sourcePosition.getPiece();
                                if (piece == null){
                                    sourcePosition = null;
                                }
                            } else {
                                destinationPosition = board.getPosition(row, col);
                                final Move move = null;
                            }

                        } else if (isLeftMouseButton(e)){

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

            private void drawTile(){

            }
    }
//    private void createBoard() {
//        JFrame frame = new JFrame();
//        JPanel panel = new JPanel(new GridLayout(8, 8, 0, 0));
//        Insets buttonMargin = new Insets(0, 0, 0, 0);
//    }
}
