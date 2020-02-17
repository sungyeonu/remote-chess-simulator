package UI;

import ChessGame.Position.AbstractPosition;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

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

    private final JFrame gameFrame;
    private final BoardPanel boardPanel;

    private JButton[][] grid = new JButton[WIDTH][HEIGHT];


    public Board(){
        this.gameFrame = new JFrame("Chess");
        this.gameFrame.setSize(OUTER_FRAME_DIMENSION);

        this.boardPanel = new BoardPanel();
        this.gameFrame.add(this.boardPanel, BorderLayout.CENTER);

        this.gameFrame.setVisible(true);
    }

    private class BoardPanel extends JPanel {
        final SquarePanel[][] squares;

        BoardPanel() {
            super(new GridLayout(8, 8));
            this.squares = new SquarePanel[ROWS][COLS];
            for (int rows = 0; rows < ROWS; rows++) {
                for (int cols = 0; cols < COLS; cols ++) {
                    final SquarePanel squarePanel = new SquarePanel(this, rows, cols);
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

            SquarePanel(final BoardPanel boardPanel, final int row, final int col){
                super(new GridBagLayout());
                this.row = row;
                this.col = col;
                setPreferredSize(TILE_PANEL_DIMENSION);
                assignTileColor();
                validate();
            }
            private void assignTileColor(){

            }
    }

    public static void main(String[] args){
        Board temp = new Board();
    }
//    private void createBoard() {
//        JFrame frame = new JFrame();
//        JPanel panel = new JPanel(new GridLayout(8, 8, 0, 0));
//        Insets buttonMargin = new Insets(0, 0, 0, 0);
//    }
}
