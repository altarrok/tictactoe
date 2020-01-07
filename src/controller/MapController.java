package controller;

import middleware.Check;
import model.Block;
import model.Map;

public class MapController {
    private Map model;

    public MapController(Map map) {
        model = map;
    }

    public void play(Block choice, int x, int y) {
        model.setBlock(choice, x, y);
    }

    public boolean checkPlayable(int x, int y) {
        return Check.playable(model, x, y);
    }
}
