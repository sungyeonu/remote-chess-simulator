package UI;

import ChessGame.ChessBoard;
import ChessGame.ColorEnum;
import ChessGame.Piece.*;
import ChessGame.Position.Position;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
    private int selectedPieceX = -1;
    private int selectedPieceY = -1;


    private ChessBoard board;
    private final JFrame gameFrame;
    private final BoardPanel boardPanel;
    private boolean gameOver = false;
    private ColorEnum winner = null;

    private boolean promotePanel = false;
    private Move movePromote = null;
    private ColorEnum colorPromote = null;

    private ColorEnum player;
    private ObjectInputStream socketIn;
    private ObjectOutputStream socketOut;
    private ColorEnum playerTurn;
    public Board(ColorEnum player, ObjectInputStream socketIn, ObjectOutputStream socketOut){
        this.player = player;
        this.socketIn = socketIn;
        this.socketOut = socketOut;
        playerTurn = ColorEnum.BLUE;

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
                for (int cols = 0; cols < COLS; cols++) {
                    final SquarePanel squarePanel = new SquarePanel(rows, cols);
                    squares[rows][cols] = squarePanel;
                    add(squarePanel);
                }
            }
            setPreferredSize(BOARD_PANEL_DIMENSON);
            validate();
        }

        public void drawBoard(final ChessBoard board) {
            if (gameOver == false) {
                if (promotePanel == true){
                    removeAll();
                    for (int num = 0; num < 4; num++){
                        final PromotionPanel promotionPanel;
                        try {
                            promotionPanel = new PromotionPanel(num);
                            add(promotionPanel);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    validate();
                    repaint();
                } else {
                    removeAll();
                    for (int rows = 0; rows < ROWS; rows++) {
                        for (int cols = 0; cols < COLS; cols++) {
                            final SquarePanel squarePanel = squares[rows][cols];
                            squarePanel.drawSquarePanel(board);
                            add(squarePanel);
                        }
                    }
                    validate();
                    repaint();
                }
            } else {
                removeAll();
            }
        }
        private void resetPromote(){
            movePromote = null;
            promotePanel = false;
            colorPromote = null;
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

                //if player turn
                addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (isRightMouseButton(e)){
                            resetClickedPositionAndPieces();
                        } else if (isLeftMouseButton(e)){
                            if (sourcePosition == null){
                                sourcePosition = board.getPosition(row, col);
                                selectedPiece = sourcePosition.getPiece();
                                if (selectedPiece == null){
                                    sourcePosition = null;
                                } else {
                                    if (selectedPiece.getColor().getID() == board.getPlayerTurn().getColor().getID()) {
                                        selectedPieceX = row;
                                        selectedPieceY = col;
                                    } else {
                                        selectedPiece = null;
                                        sourcePosition = null;
                                    }
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
                                        board.makeMove(x);
                                        if(selectedPiece.getId() == PieceIDEnum.PAWN){
                                            ColorEnum checkPromoteColor = selectedPiece.getColor();
                                            boolean proceed = false;
                                            int promoteXCoord = checkTo.getX();
                                            int promoteYCoord = checkTo.getY();

                                            if (checkPromoteColor.getID() == ColorEnum.BLUE.getID()){
                                                if (promoteXCoord == 0 ){
                                                    for (int i = 0; i < 8; i++){
                                                        if (promoteYCoord == i){
                                                            colorPromote = ColorEnum.BLUE;
                                                            proceed = true;
                                                        }
                                                    }
                                                }
                                            } else if (checkPromoteColor.getID() == ColorEnum.BLACK.getID()){
                                                if (promoteXCoord == 7 ){
                                                    for (int i = 0; i < 8; i++){
                                                        if (promoteYCoord == i){
                                                            colorPromote = ColorEnum.BLACK;
                                                            proceed = true;
                                                        }
                                                    }
                                                }
                                            }
                                            if (proceed){
                                                movePromote = x;
                                                promotePanel = true;
                                            }
                                        }
                                        board.switchTurn();
                                        break;
                                    }
                                    board = board.getChessBoard();
                                }
                                resetClickedPositionAndPieces();
                            }
                            SwingUtilities.invokeLater(new Runnable() {
                                @Override
                                public void run() {
                                    if (board.checkBlueLife() == false){
                                        winner = ColorEnum.BLACK;
                                        boardPanel.drawBoard(board);
                                        gameOver = true;
                                        System.out.println("Game Over, Winner = BLACK");
                                    } else if (board.checkBlackLife() == false){
                                        winner = ColorEnum.BLUE;
                                        boardPanel.drawBoard(board);
                                        gameOver = true;
                                        System.out.println("Game Over, Winner = BLUE");
                                    }else {
                                        boardPanel.drawBoard(board);
                                    }
                                }
                            });
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
            private void highlightLegalMoves(final ChessBoard board){
                if (selectedPiece != null) {
                    if (selectedPiece.getColor() == board.getPlayerTurn().getColor()) {
                        for (final Move move : selectedPiece.getMoveSet(board, sourcePosition)) {
                            Coord to = move.getTo();
                            int x = to.getX();
                            int y = to.getY();
                            if ((x == row) && (y == col)) {
                                try {
                                    final BufferedImage image = ImageIO.read(getClass().getResource("/icons/misc/green_dot.png"));
                                    add(new JLabel(new ImageIcon(image)));
                                } catch (final IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
            }

            private void highlightTile(final ChessBoard board) {
                if(selectedPiece != null && selectedPiece.getColor() == board.getPlayerTurn().getColor()){
                    if (row == selectedPieceX && col == selectedPieceY) {
                        if (board.getPlayerTurn().getColor() == ColorEnum.BLUE) {
                            setBorder(BorderFactory.createLineBorder(Color.cyan, 3));
                        } else
                            setBorder(BorderFactory.createLineBorder(Color.black, 3));
                    } else {
                        setBorder(null);
                    }
                }
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
            private void highLightLastMove(final ChessBoard board){
                if (board.begin() == true) {
                    Move move = board.getLastMove();
                    int lastX = move.getFrom().getX();
                    int lastY = move.getFrom().getY();
                    if (row == lastX && col == lastY) {
                        setBorder(BorderFactory.createLineBorder(Color.gray, 3));
                    } else {
                        setBorder(null);
                    }
                }
            }

            public void drawSquarePanel(ChessBoard board){
                assignTileColor();
                assignTilePieceIcon(board);
                highlightTile(board);
                highLightLastMove(board);
                highlightLegalMoves(board);
                validate();
                repaint();
            }
    }

    private class PromotionPanel extends JPanel{
        PromotionPanel(final int num) throws IOException {
            super(new GridBagLayout());
            int id = num;
            setPreferredSize(TILE_PANEL_DIMENSION);

            String colorFolder;
            if (colorPromote.getID() == 1)
                colorFolder = "blue";
            else
                colorFolder = "black";

            String path = "/icons/" + colorFolder + "/";
            switch (num){
                case 0:
                    path += "rook.gif";
                    break;
                case 1:
                    path += "bishop.gif";
                    break;
                case 2:
                    path += "queen.gif";
                    break;
                case 3:
                    path += "knight.gif";
                    break;
            }
            final BufferedImage image = ImageIO.read(getClass().getResource(path));
            add(new JLabel(new ImageIcon(image)));
            addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (isLeftMouseButton(e)) {
                        switch (id){
                            case 0:
                                board.promotePiece(movePromote, PieceIDEnum.ROOK, colorPromote);
                                break;
                            case 1:
                                board.promotePiece(movePromote, PieceIDEnum.BISHOP, colorPromote);
                                break;
                            case 2:
                                board.promotePiece(movePromote, PieceIDEnum.QUEEN, colorPromote);
                                break;
                            case 3:
                                board.promotePiece(movePromote, PieceIDEnum.KNIGHT, colorPromote);
                                break;
                        }
                    }
                    board = board.getChessBoard();
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            boardPanel.resetPromote();
                            boardPanel.drawBoard(board);
                        }
                    });
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
        }
    }

}
