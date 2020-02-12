package UI;


import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class UI {
    private JFrame frame;
    private final int START_X = 150;
    private final int START_Y = 150;
    private final int WIDTH = 640;
    private final int HEIGHT = 640;
    private boolean visible = false;
    public UI(){
        frame = new JFrame();
        frame.setBounds(START_X,START_Y, WIDTH, HEIGHT);
        frame.setVisible(true);
        frame.setResizable(false);
        WindowListener exitListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (visible)
                    System.out.println("Closing");
                System.exit(0);
            }
        };
        //GridBagLayout
        GridLayout grid = new GridLayout();
        frame.addWindowListener(exitListener);
    }

    public static void main(String[] args){
        UI temp = new UI();
        System.out.println("Yes");
    }
}
