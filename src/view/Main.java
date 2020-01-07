package view;

import controller.MapController;
import controller.PlayerController;
import middleware.Check;
import model.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        //Models
        Map map = new Map();
        Player user = new UserPlayer();
        Player ai = new AiPlayer(map, 10);
        Player ai2 = new AiPlayer(map, 10);

        //Controllers
        MapController mapController = new MapController(map);
        PlayerController userController = new PlayerController(ai, mapController);
        PlayerController aiController = new PlayerController(ai2, mapController);
        userController.choseX();

        ArrayList<Block> results = new ArrayList<>();

        while (true) {
            System.out.println("#########");
            System.out.println(map);
            System.out.println("#########");
//            Scanner scanner = new Scanner(System.in);
//            System.out.println("Enter a number between 0 and 8 (inclusive) to play");
//            int n = scanner.nextInt();
//            try {
//                userController.play(n / 3, n % 3);
            userController.play();
            System.out.println("#########");
            System.out.println(map);
            System.out.println("#########");
            Block result = Check.winner(map);
            if (result == Block.EMPTY) {
                aiController.play();
            }
//            } catch (Unplayable e) {
//                System.out.println("That position is already taken");
//            }
//            Block result = Check.winner(map);

            result = Check.winner(map);
            if (result != Block.EMPTY) {
                System.out.println(result == Block.DRAW ? result : (result + " won."));
                System.out.println("#########");
                System.out.println(map);
                results.add(result);
                break;
            }
        }
    }
}
