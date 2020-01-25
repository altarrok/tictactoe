import controller.MapController;
import controller.PlayerController;
import middleware.Check;
import model.*;
import view.MapView;

import java.util.ArrayList;

public class Main {
    static Block winner;
    public static void main(String[] args) {
        //Views
        MapView mapView = new MapView();

        //Models
        Map map = new Map();
        Player user = new UserPlayer();
        MapController mapController = new MapController(map,mapView);
        Player ai = new AiPlayer(map, 10, mapController);


        //Controllers
        PlayerController userController = new PlayerController(ai, mapController);
        userController.choseX();

        ArrayList<Block> results = new ArrayList<>();

        // GAME LOOP ==================================
        while (true) {
            if (mapController.turn == 1) {
                userController.play();
            }

            if ((winner = Check.winner(map)) != Block.EMPTY) {
                mapController.close(winner);
                break;
            }
        }
    }
}
