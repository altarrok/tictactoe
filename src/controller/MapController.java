package controller;

import middleware.Check;
import model.Block;
import model.Map;
import view.MapView;

import javax.swing.*;
import java.awt.event.*;

public class MapController implements ActionListener {
    private Map model;
    private MapView mapView;
    // x's turn if 0, o's turn if 1
    public int turn;

    public MapController(Map map, MapView mapView) {
        turn = 0;
        model = map;
        this.mapView = mapView;
        mapView.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int mouseX = e.getX() - 8;
                int mouseY = e.getY() - 32;
                int xPos = mouseX / 100;
                int yPos = mouseY / 100;
                if (checkPlayable(xPos, yPos)) {
                    play(Block.X, xPos, yPos);
                }
            }
        });
    }

    public void play(Block choice, int x, int y) {
        model.setBlock(choice, x, y);
        turn = (choice == Block.X ? 1 : 0);
        mapView.render(choice, x * 100 + 37, y * 100 + 63);
    }

    public boolean checkPlayable(int x, int y) {
        return Check.playable(model, x, y);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();

    }

    public void close(Block winner) {
        mapView.winner(winner);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            //Do nothing
        }
        mapView.dispatchEvent(new WindowEvent(mapView, WindowEvent.WINDOW_CLOSING));
    }
}
