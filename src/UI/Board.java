package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Board extends JPanel {
    private final int WIDTH = 8;
    private final int HEIGHT = 8;
    private UI ui;
    private JButton[][] grid = new JButton[WIDTH][HEIGHT];
    private void createBoard(){
        JFrame frame = new JFrame();
        JPanel panel = new JPanel(new GridLayout(8, 8, 0, 0));
        Insets buttonMargin = new Insets(0,0,0,0);

}
