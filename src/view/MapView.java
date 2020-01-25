package view;

import model.Block;

import javax.swing.*;
import java.awt.*;

public class MapView extends JFrame {
    JButton[] buttons = new JButton[9];
    JPanel canvas;

    public MapView() {
        add(canvas = new JPanel() {
            @Override
            public void paint(Graphics g) {
                // DRAWING LINES ================================
                g.drawLine(100,0,100,300);
                g.drawLine(200,0,200,300);
                g.drawLine(0,100,300,100);
                g.drawLine(0,200,300,200);
            }
        }, BorderLayout.CENTER);
        canvas.setPreferredSize(new Dimension(300,300));
        this.pack();
        setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void render(Block choice, int x, int y) {
        Graphics graphics = canvas.getGraphics();
        graphics.setFont(new Font("TimesRoman", Font.PLAIN, 50));
        graphics.drawString(choice.toString(), x, y);
        canvas.update(graphics);
    }

    public void winner(Block winner) {
        Graphics graphics = canvas.getGraphics();
        graphics.setColor(Color.green);
        graphics.setFont(new Font("TimesRoman", Font.BOLD, 30));
        graphics.drawString((winner != Block.DRAW) ? (winner.toString() + " won") : ("DRAW"), 100, 150);
    }

}
