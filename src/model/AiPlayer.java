package model;

import middleware.Check;

public class AiPlayer extends Player {
    Map map;
    int depth = 1;

    public AiPlayer(Map map, int depth) {
        super();
        this.choice = Block.O;
        this.map = map;
        this.depth = depth;
    }

    public void play() {
        int bestScore = (this.choice == Block.O) ? 10000 : -10000;
        int cursor = 0;
        int move = 0;
        for (Block b : map) {
            if (b == Block.EMPTY) {
                map.setBlock(this.choice, cursor / 3, cursor % 3);
                int score = minimax(map, depth, -10000, 10000, (this.choice == Block.O) ? true : false);
                map.setBlock(Block.EMPTY, cursor / 3, cursor % 3);
                if (this.choice == Block.O) {
                    if (score < bestScore) {
                        bestScore = score;
                        move = cursor;
                    }
                } else {
                    if (score > bestScore) {
                        bestScore = score;
                        move = cursor;
                    }
                }
            }
            cursor++;
        }
        map.setBlock(this.choice, move / 3, move % 3);
    }

    private int minimax(Map map, int depth, int alpha, int beta, boolean isMaximizing) {
//        System.out.println("depth" + depth);
        switch (Check.winner(map)) {
            case X:
                return 1000 + depth;
            case O:
                return -1000 - depth;
            case DRAW:
                return 0;
        }

        if (depth <= 0) {
            return 0;
        }

        int bestScore = (isMaximizing ? -10000 : 10000);
        int cursor = 0;
        for (Block b : map) {
            if (b == Block.EMPTY) {
                map.setBlock((isMaximizing ? Block.X : Block.O), cursor / 3, cursor % 3);
                int score = minimax(map, depth--, alpha, beta, !isMaximizing);
                map.setBlock(Block.EMPTY, cursor / 3, cursor % 3);
                bestScore = (isMaximizing ? Integer.max(bestScore,score) : Integer.min(bestScore, score));
                if (isMaximizing) {
                    alpha = Integer.max(bestScore, alpha);
                } else {
                    beta = Integer.min(bestScore, beta);
                }
                if (alpha >= beta) {
                    break;
                }
            }
            cursor++;
        }
        return bestScore;
    }
}
