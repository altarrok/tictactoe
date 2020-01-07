package controller;

import exceptions.Unplayable;
import model.AiPlayer;
import model.Block;
import model.Player;

public class PlayerController {
    private Player model;
    private MapController mapController;

    public PlayerController(Player player, MapController mapController) {
        this.model = player;
        this.mapController = mapController;
    }

    public void choseX() {
        model.setChoice(Block.X);
    }

    public void choseO() {
        model.setChoice(Block.O);
    }

    public void won() {
        model.incrementScore();
    }

    public void lost() {
        model.decrementScore();
    }

    public void play(int x, int y) throws Unplayable {
        if (mapController.checkPlayable(x,y)) {
            mapController.play(model.getChoice(), x, y);
        } else {
            throw new Unplayable();
        }
    }

    public void play() {
        if (model instanceof AiPlayer) {
            ((AiPlayer) model).play();
        }
    }
}
